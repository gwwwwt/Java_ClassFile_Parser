package com.gwwwwt.elements.impl.constantpool.elements;

import com.gwwwwt.Contants.ConstantPoolTag;
import com.gwwwwt.elements.util.ByteUtil;
import com.gwwwwt.parser.DataGenerator;

public class ConstantInteger extends ConstantPoolElement{

    private int value;

    ConstantInteger(ConstantPoolTag tag, int index) {
        super(tag, index);
    }

    @Override
    public String print() {
        return getSimpleInfo();
    }

    @Override
    protected String getSimpleInfo() {
        return "#" + index + " " + ConstantPoolTag.CONSTANT_INTEGER_TAG.toString() + " " + value;
    }

    @Override
    public int[] getLinkTargetIndex() {
        return new int[0];
    }

    @Override
    public void link2other(ConstantPoolElement obj) {

    }

    @Override
    public void link2others(ConstantPoolElement[] objs) {

    }

    @Override
    protected void processContent(DataGenerator generator) {
        /*
        CONSTANT_Integer_info{
            u1 tag;
            u4 bytes;
        }
         */

        byte[] data = generator.getData(4);
        value = ByteUtil.byteArray2Int(data);
    }
}
