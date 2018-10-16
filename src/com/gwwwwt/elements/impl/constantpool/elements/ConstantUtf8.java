package com.gwwwwt.elements.impl.constantpool.elements;

import com.gwwwwt.Contants.ConstantPoolTag;
import com.gwwwwt.elements.util.ByteUtil;
import com.gwwwwt.parser.DataGenerator;

public class ConstantUtf8 extends ConstantPoolElement {

    private int length;
    private byte[] origin;
    private String result;

    public ConstantUtf8(ConstantPoolTag tag, int index) {
        super(tag, index);
    }

    @Override
    public String print() {
        return getSimpleInfo();
    }

    @Override
    protected String getSimpleInfo() {
        return "#" + index + " " + ConstantPoolTag.CONSTANT_UTF8_TAG.toString() + " " + result;
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
        CONSTANT_Utf8_info {
            u1 tag;
            u2 length;
            u1 bytes[length]
        }
         */

        byte[] length_data = generator.getData(2);
        this.length = ByteUtil.byteArray2Int(length_data);

        byte[] result_data = generator.getData(length);
        byte[] data = new byte[length + 2];
        System.arraycopy(length_data, 0, data, 0, 2);
        System.arraycopy(result_data, 0, data, 2, length);
        setContent(data);

        this.origin = result_data;
        this.result = new String(origin);
    }
}
