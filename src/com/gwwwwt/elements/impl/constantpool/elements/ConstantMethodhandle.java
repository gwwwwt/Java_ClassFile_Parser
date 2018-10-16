package com.gwwwwt.elements.impl.constantpool.elements;

import com.gwwwwt.Contants.ConstantPoolTag;
import com.gwwwwt.elements.util.ByteUtil;
import com.gwwwwt.parser.DataGenerator;

public class ConstantMethodhandle extends ConstantPoolElement {

    private int reference_kind;
    private int reference_index;
    private ConstantPoolElement reference_element;

    public ConstantMethodhandle(ConstantPoolTag tag, int index) {
        super(tag, index);
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append( index + " : " + ConstantPoolTag.CONSTANT_METHODHANDLE_TAG.toString());
        builder.append( " kind: " + reference_kind);
        builder.append(" #" + reference_index);
        builder.append( " -> " + reference_element.getSimpleInfo());
        return builder.toString();
    }

    @Override
    protected String getSimpleInfo() {
        return "#" + index + " " + ConstantPoolTag.CONSTANT_METHODHANDLE_TAG.toString();
    }

    @Override
    public int[] getLinkTargetIndex() {
        return new int[]{reference_index};
    }

    @Override
    public void link2other(ConstantPoolElement obj) {
        this.reference_element = obj;
    }

    @Override
    public void link2others(ConstantPoolElement[] objs) {

    }

    @Override
    protected void processContent(DataGenerator generator) {
        /*
        CONSTANT_MethodHandle_info {
            u1 tag;
            u1 reference_kind;
            u2 reference_index;
        }
         */
        byte[] data = generator.getData(3);

        this.reference_kind = ByteUtil.byteArray2Int(new byte[]{data[0]});
        this.reference_index = ByteUtil.byteArray2Int(new byte[]{data[1], data[2]});
    }
}
