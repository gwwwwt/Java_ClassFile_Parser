package com.gwwwwt.elements.util;

/**
 * Convert byte array to int or hex format string
 */
public class ByteUtil {
    public static int byteArray2Int(byte[] bytes) {

        if(bytes.length == 1) {
            return (int)bytes[0];
        }

        if(bytes.length == 2) {
            return (bytes[0] & 0xff) << 8 | (bytes[1] & 0xff);
        }

        if(bytes.length == 4) {
            return  bytes[3] & 0xFF |
                    (bytes[2] & 0xFF) << 8 |
                    (bytes[1] & 0xFF) << 16 |
                    (bytes[0] & 0xFF) << 24;
        }

        return 0;
    }

    public static String byteArray2HexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String temp;
        for(byte v : bytes) {
            temp = Integer.toHexString(v);
            builder.append(temp.substring(temp.length() - 2) + " ");
        }

        return builder.toString();
    }
}
