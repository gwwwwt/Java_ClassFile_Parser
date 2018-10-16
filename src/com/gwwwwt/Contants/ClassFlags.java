package com.gwwwwt.Contants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类访问和属性修饰符标标志
 */
public enum ClassFlags {
    ACC_PUBLIC(0x0001),
    ACC_FINAL(0X0010),
    ACC_SUPER(0X0020),
    ACC_INTERFACE(0X0200),
    ACC_ABSTRACT(0X0400),
    ACC_SYNTHETIC(0X1000),
    ACC_ANNOTATION(0X2000),
    ACC_ENUM(0X4000);

    private int flag;
    ClassFlags(int flag) {
        this.flag = flag;
    }

    public static List<ClassFlags> parse_class_flags(int flags) {
        return Arrays.stream(ClassFlags.values())
                .filter(e -> (flags & e.flag) != 0)
                .collect(Collectors.toList());
    }
}
