package com.gwwwwt.elements.impl.constantpool.elements;

import com.gwwwwt.Contants.ConstantPoolTag;
import com.gwwwwt.elements.util.ByteUtil;
import com.gwwwwt.parser.DataGenerator;

public class ConstantMethodtype extends ConstantPoolElement {

    private int descriptor_index;
    private ConstantPoolElement desciptor_element;

    public ConstantMethodtype(ConstantPoolTag tag, int index) {
        super(tag, index);
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append( index + " : " + ConstantPoolTag.CONSTANT_METHODTYPE_TAG.toString());
        builder.append( " #" + descriptor_index);
        builder.append( " -> " + desciptor_element.getSimpleInfo());
        return builder.toString();
    }

    @Override
    protected String getSimpleInfo() {
        return "#" + index + " " + ConstantPoolTag.CONSTANT_METHODTYPE_TAG.toString();
    }

    @Override
    public int[] getLinkTargetIndex() {
        return new int[descriptor_index];
    }

    @Override
    public void link2other(ConstantPoolElement obj) {
        this.desciptor_element = obj;
    }

    @Override
    public void link2others(ConstantPoolElement[] objs) {

    }

    @Override
    protected void processContent(DataGenerator generator) {
        /*
        CONSTANT_MethodType_info {
            u1 tag;
            u2 descriptor_index;
        }
         */

        byte[] data = generator.getData(2);
        setContent(data);
        this.descriptor_index = ByteUtil.byteArray2Int(data);
    }
}
