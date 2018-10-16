package com.gwwwwt.elements.impl.constantpool.elements;

import com.gwwwwt.Contants.ConstantPoolTag;
import com.gwwwwt.elements.util.ByteUtil;
import com.gwwwwt.parser.DataGenerator;

public class ConstantString extends ConstantPoolElement {

    private int string_index;  // point to the index of an utf sequence in constant pool

    private ConstantPoolElement constant_utf8_info; //this is the element of constant pool at name_index

    protected ConstantString(ConstantPoolTag tag, int index) {
        super(tag, index);
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append( index + " : " + ConstantPoolTag.CONSTANT_STRING_TAG.toString());
        builder.append( " #" + string_index);
        builder.append( " -> " + constant_utf8_info.getSimpleInfo());
        return builder.toString();
    }

    @Override
    protected String getSimpleInfo() {
        return "#" + index + " " + ConstantPoolTag.CONSTANT_STRING_TAG.toString();
    }

    @Override
    public int[] getLinkTargetIndex() {
        return new int[]{string_index};
    }

    @Override
    public void link2other(ConstantPoolElement obj) {
        this.constant_utf8_info = obj;
    }

    @Override
    public void link2others(ConstantPoolElement[] objs) {
        return;
    }

    @Override
    protected void processContent(DataGenerator generator) {
        /*
         CONSTANT_Class_info {
             u1 tag;
             u2 name_index;
         }
         */
        byte[] data = generator.getData(2);
        this.setContent(data);
        this.string_index = ByteUtil.byteArray2Int(data);
    }
}
