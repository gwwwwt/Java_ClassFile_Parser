package com.gwwwwt.elements.impl.constantpool.elements;

import com.gwwwwt.Contants.ConstantPoolTag;
import com.gwwwwt.elements.util.ByteUtil;
import com.gwwwwt.parser.DataGenerator;

public class Constant_Field_Method_InterfaceMethod extends ConstantPoolElement {

    private int class_index;
    private ConstantPoolElement constant_class_info;

    private int name_and_type_index;
    private ConstantPoolElement constant_nameandtype_info;

    protected Constant_Field_Method_InterfaceMethod(ConstantPoolTag tag, int index) {
        super(tag, index);
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append( index + " : " +  ConstantPoolTag.CONSTANT_FIELDREF_TAG.toString());
        builder.append( " #" + class_index + " ");
        builder.append( " -> " + this.constant_class_info.getSimpleInfo());
        builder.append( "\n\t#" + name_and_type_index);
        builder.append(" -> " + constant_nameandtype_info.getSimpleInfo());
        return builder.toString();
    }

    @Override
    protected String getSimpleInfo() {
        return "#" + index + " " + ConstantPoolTag.CONSTANT_FIELDREF_TAG.toString();
    }

    @Override
    public int[] getLinkTargetIndex() {
        return new int[]{class_index, name_and_type_index};
    }

    @Override
    public void link2other(ConstantPoolElement obj) {
        return;
    }

    @Override
    public void link2others(ConstantPoolElement[] objs) {
        this.constant_class_info = objs[0];
        this.constant_nameandtype_info = objs[1];
    }

    @Override
    protected void processContent(DataGenerator generator) {
        /*
        CONSTANT_Fieldref_info {
           u1 tag;
           u2 class_index;
           u2 name_and_type_index;
        }
        and the same are CONSTANT_Methodref_info and CONSTANT_interfaceMethodref_info
         */
        byte[] data = generator.getData(4);
        setContent(data);
        this.class_index = ByteUtil.byteArray2Int(new byte[]{data[0], data[1]});
        this.name_and_type_index = ByteUtil.byteArray2Int(new byte[]{data[2], data[3]});
    }
}
