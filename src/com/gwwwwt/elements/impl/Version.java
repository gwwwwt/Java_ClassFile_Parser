package com.gwwwwt.elements.impl;

import com.gwwwwt.elements.util.ByteUtil;
import com.gwwwwt.elements.util.DefaultElementImpl;

/**
 * u2: minor_version
 * u2: major_version
 *   major_version is not 1-8,
 *   but from java 1.1, the major_version value is 45
 *   so other version of java's major_version value is: 44 + k.
 *
 *   For example: java 1.8's major_version is 52
 */
public class Version extends DefaultElementImpl {

    @Override
    public boolean isFixedSize() {
        return true;
    }

    @Override
    public int getFixSize() {
        return 4;
    }

    @Override
    public String print() {
        byte[] minor = new byte[]{this.content[0][0], this.content[0][1]};
        byte[] major = new byte[]{this.content[0][2], this.content[0][3]};

        StringBuilder stringBuilder = new StringBuilder("Minor version : ");
        stringBuilder.append(ByteUtil.byteArray2Int(minor) + "\n");
        stringBuilder.append("Major version : ");
        stringBuilder.append(ByteUtil.byteArray2Int(major) + "\n");
        return stringBuilder.toString();
    }
}
