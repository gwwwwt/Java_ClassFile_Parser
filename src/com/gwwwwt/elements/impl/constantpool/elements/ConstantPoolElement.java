package com.gwwwwt.elements.impl.constantpool.elements;

import com.gwwwwt.Contants.ConstantPoolTag;
import com.gwwwwt.elements.util.ByteUtil;
import com.gwwwwt.parser.DataGenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class ConstantPoolElement {

    protected ConstantPoolTag tag;
    protected byte[] content;
    protected int index; // this index indicate the position of this element in constant pool

    protected ConstantPoolElement(ConstantPoolTag tag, int index) {
        this.tag = tag;
        this.index = index;
    }

    protected void setContent(byte[] content) {
        this.content = content;
    }

    public ConstantPoolTag getTag() {
        return tag;
    }

    public byte[] getContent() {
        return content;
    }

    public abstract String print();
    protected abstract String getSimpleInfo();
    public abstract int[] getLinkTargetIndex();
    public abstract void link2other(ConstantPoolElement obj);
    public abstract void link2others(ConstantPoolElement[] objs);
    protected abstract void processContent(DataGenerator generator);

    public static <T extends ConstantPoolElement> T getNextElementInstance(DataGenerator generator, int index)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //Read the first byte, indicate the constant pool element type
        int value = ByteUtil.byteArray2Int(generator.getData(1));
        ConstantPoolTag tag = int2ConstantPoolTag(value, index);

        String instanceClassName =  getInstanceClassName(tag);

        Class clzss = Class.forName(instanceClassName);

        Constructor constructor = clzss.getDeclaredConstructor(ConstantPoolTag.class, int.class);
        T obj = (T)constructor.newInstance(tag, index);

        obj.processContent(generator);

        return obj;
    }

    private static String getInstanceClassName(ConstantPoolTag tag) {
        String prefix = "com.gwwwwt.elements.impl.constantpool.elements.";

        if(tag == ConstantPoolTag.CONSTANT_FIELDREF_TAG
                || tag == ConstantPoolTag.CONSTANT_METHODREF_TAG
                || tag == ConstantPoolTag.CONSTANT_INTERFACEMETHODREF_TAG)
        {
            //these three kinds have same bytes content, use tag to distinct
            return prefix + "Constant_Field_Method_InterfaceMethod";
        }

        String fullName = tag.toString().replace("_TAG", "").toLowerCase().trim();

        StringBuilder builder = new StringBuilder(prefix);

        String[] fields = fullName.split("_");

        for(String field : fields) {
            String str = field.substring(0, 1).toUpperCase() + field.substring(1);

            builder.append(str);
        }

        return builder.toString();
    };

    private static ConstantPoolTag int2ConstantPoolTag(int v, int index) {
        ConstantPoolTag result = ConstantPoolTag.getConstantPoolTag(v);

        if(result == null) {
            System.out.println("[ERROR] : Cannot resolve constant pool tag, index : "
                    + index
                    + ", value "
                    + v);
            System.exit(-1);
        }

        return result;
    }
}
