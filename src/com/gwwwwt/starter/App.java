package com.gwwwwt.starter;

import com.gwwwwt.elements.impl.Magic;
import com.gwwwwt.elements.impl.Version;
import com.gwwwwt.elements.impl.constantpool.ConstantPool;
import com.gwwwwt.parser.ClassFileReader;
import com.gwwwwt.parser.DataGenerator;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        DataGenerator generator = new ClassFileReader("/Users/wgao/Documents/Java_ClassFile_Parser/out/production/" +
                "Java_ClassFile_Parser/com/gwwwwt/test/Iphone6s.class");

        Magic magic = new Magic();
        magic.readFromDataGenerator(generator);

        Version version = new Version();
        version.readFromDataGenerator(generator);

        ConstantPool cp = new ConstantPool();
        cp.readFromDataGenerator(generator);

        StringBuilder stringBuilder = new StringBuilder("Magic : " + magic.print() + "\n");
        stringBuilder.append("Version : " + version.print() + "\n");

        System.out.println(stringBuilder.toString());

        System.out.println("Constant Pool:\n" + cp.print() + "\n");
    }
}
