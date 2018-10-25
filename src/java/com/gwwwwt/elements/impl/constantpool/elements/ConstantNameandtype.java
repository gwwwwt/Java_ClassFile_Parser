package com.gwwwwt.elements.impl.constantpool.elements;

import com.gwwwwt.Contants.ConstantPoolTag;
import com.gwwwwt.elements.util.ByteUtil;
import com.gwwwwt.parser.DataGenerator;

public class ConstantNameandtype extends ConstantPoolElement {

    private int name_index;
    private ConstantPoolElement constant_utf8_info_name;

    private int descriptor_index;
    private ConstantPoolElement constant_utf8_info_descriptor;

    public ConstantNameandtype(ConstantPoolTag tag, int index) {
        super(tag, index);
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append( index + " : " +  ConstantPoolTag.CONSTANT_NAMEANDTYPE_TAG.toString());
        builder.append( " #" + this.name_index + " ");
        builder.append( " -> " + this.constant_utf8_info_name.getSimpleInfo());
        builder.append( "\n\t#" + descriptor_index);
        builder.append(" -> " + this.constant_utf8_info_descriptor.getSimpleInfo());
        return builder.toString();
    }

    @Override
    protected String getSimpleInfo() {
        return "#" + index + " " + ConstantPoolTag.CONSTANT_NAMEANDTYPE_TAG.toString();
    }

    @Override
    public int[] getLinkTargetIndex() {
        return new int[]{name_index, descriptor_index};
    }

    @Override
    public void link2other(ConstantPoolElement obj) {

    }

    @Override
    public void link2others(ConstantPoolElement[] objs) {
        this.constant_utf8_info_name = objs[0];
        this.constant_utf8_info_descriptor = objs[1];
    }

    @Override
    protected void processContent(DataGenerator generator) {
        /*
        CONSTANT_NameAndType_info {
            u1 tag;
            u2 name_index;
            u2 descriptor_index;
        }
         */
        byte[] data = generator.getData(4);
        setContent(data);

        this.name_index = ByteUtil.byteArray2Int(new byte[]{data[0], data[1]});
        this.descriptor_index = ByteUtil.byteArray2Int(new byte[]{data[2], data[3]});
    }
}
