package com.gwwwwt.elements.impl.constantpool.elements;

import com.gwwwwt.Contants.ConstantPoolTag;
import com.gwwwwt.elements.util.ByteUtil;
import com.gwwwwt.parser.DataGenerator;

public class ConstantInvokedynamic extends ConstantPoolElement {

    private int bootstrap_method_attr_index;
    private int name_and_type_index;
    private ConstantPoolElement name_and_type_element;

    public ConstantInvokedynamic(ConstantPoolTag tag, int index) {
        super(tag, index);
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append( index + " : " + ConstantPoolTag.CONSTANT_INVOKEDYNAMIC_TAG.toString());
        builder.append( " bootstrap_method_attr_index: " + bootstrap_method_attr_index);
        builder.append(" #" + name_and_type_index);
        builder.append( " -> " + name_and_type_element.getSimpleInfo());
        return builder.toString();
    }

    @Override
    protected String getSimpleInfo() {
        return "#" + index + " " + ConstantPoolTag.CONSTANT_INVOKEDYNAMIC_TAG.toString();
    }

    @Override
    public int[] getLinkTargetIndex() {
        return new int[name_and_type_index];
    }

    @Override
    public void link2other(ConstantPoolElement obj) {
        this.name_and_type_element = obj;
    }

    @Override
    public void link2others(ConstantPoolElement[] objs) {

    }

    @Override
    protected void processContent(DataGenerator generator) {
        /*
        CONSTANT_InvokeDynamic_info {
            u1 tag;
            u2 bootstrap_method_attr_index;
            u2 name_and_type_index;
        }
         */

        byte[] data = generator.getData(4);
        setContent(data);
        this.bootstrap_method_attr_index = ByteUtil.byteArray2Int(new byte[]{data[0], data[1]});
        this.name_and_type_index = ByteUtil.byteArray2Int(new byte[]{data[2], data[3]});
    }
}
