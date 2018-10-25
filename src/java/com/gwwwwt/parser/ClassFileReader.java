package com.gwwwwt.parser;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassFileReader implements DataGenerator {

    private Path path;
    private ByteBuffer content;

    public ClassFileReader(String path) throws IOException {
        this.path = Paths.get(path);

        if(Files.exists(this.path) && Files.isRegularFile(this.path)) {
            int fileSize = (int)Files.size(this.path);

            content = ByteBuffer.allocate(fileSize + 1);

            content.put(Files.readAllBytes(this.path));

            content.flip();
        }
        else {
            System.out.println("The file " + this.path.getFileName() + " doesn't exist!!!");
            System.exit(-1);
        }
    }
    @Override
    public byte[] getData(int length) {
        byte[] result = new byte[length];
        content.get(result);
        return result;
    }


    public static void main(String[] args) throws IOException {
        new ClassFileReader("/Users/wgao/Documents/Java_ClassFile_Parser/out/production/" +
                "Java_ClassFile_Parser/com/gwwwwt/elements/impl/Version.class");
    }
}
