package com.gwwwwt.elements.util;

public class ElementPrinter {
    public static ElementPrinter INSTANCE = new ElementPrinter();

    private void checkLength(byte[] bytes, int length, String info) {
        if (bytes.length != length) {
            System.out.println("[ERROR]: while printing "
                    + info
                    + ", expected "
                    + length
                    + " bytes"
                    + ", but got "
                    + bytes.length
                    + " bytes!!!\n");
            System.exit(-1);
        }
    }

    private void checkIntByte(byte[] bytes, String info) {
        if(bytes.length != 2 && bytes.length != 4) {
            System.out.println("[ERROR]: While parsing "
                    + info
                    +",The byte array's length is not 2 or 4, please check!!!");
            System.exit(-1);
        }
    }

    private int bytes2int(byte[] bytes, String info) {
        checkIntByte(bytes, info);

        return ByteUtil.byteArray2Int(bytes);
    }

    public void printFixNumberHex(byte[] bytes, int length, String info) {
        checkLength(bytes, length, info);

        System.out.println("[" + info + "] "
                        + ByteUtil.byteArray2HexString(bytes));
    }

    public void printFixNumber(byte[] bytes, int length, String info)
    {
        checkLength(bytes, length, info);

        System.out.println("[" + info + "] " + bytes2int(bytes, info));
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[]{(byte)0xca,(byte)0xfe,(byte)0xba,(byte)0xbe };

        System.out.println(ElementPrinter.INSTANCE.bytes2int(bytes, "TEST"));
        ElementPrinter.INSTANCE.printFixNumberHex(bytes, 4, "TEST");
        ElementPrinter.INSTANCE.printFixNumber(bytes, 4, "TEST");
    }
}
