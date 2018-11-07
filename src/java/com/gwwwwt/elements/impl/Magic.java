package com.gwwwwt.elements.impl;

import com.gwwwwt.elements.util.ByteUtil;
import com.gwwwwt.elements.DefaultElementImpl;

/**
 * u4
 */
public class Magic extends DefaultElementImpl {

    private final int SIZE = 4;

    @Override
    public boolean isFixedSize() {
        return true;
    }

    @Override
    public int getFixSize() {
        return SIZE;
    }

    @Override
    public String print() {
        return ByteUtil.byteArray2HexString(this.content[0]);
    }
}
