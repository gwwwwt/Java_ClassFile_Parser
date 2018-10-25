package com.gwwwwt.Contants;

public enum ConstantPoolTag {

    CONSTANT_CLASS_TAG(7),
    CONSTANT_FIELDREF_TAG(9),
    CONSTANT_METHODREF_TAG(10),
    CONSTANT_INTERFACEMETHODREF_TAG(11),
    CONSTANT_STRING_TAG(8),
    CONSTANT_INTEGER_TAG(3),
    CONSTANT_LONG_TAG(5),
    CONSTANT_DOUBLE_TAG(6),
    CONSTANT_NAMEANDTYPE_TAG(12),
    CONSTANT_UTF8_TAG(1),
    CONSTANT_METHODHANDLE_TAG(15),
    CONSTANT_METHODTYPE_TAG(16),
    CONSTANT_INVOKEDYNAMIC_TAG(18);


    private int tag;
    ConstantPoolTag(int value) {
        this.tag = value;
    }

    public int getTag() {
        return this.tag;
    }

    public static ConstantPoolTag getConstantPoolTag(int value) {
        for (ConstantPoolTag tag : ConstantPoolTag.values()) {
            if (tag.getTag() == value) {
                return tag;
            }
        }
        return null;
    }
}
