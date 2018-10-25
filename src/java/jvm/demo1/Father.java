package jvm.demo1;

import net.sf.ehcache.pool.sizeof.AgentSizeOf;
import net.sf.ehcache.pool.sizeof.SizeOf;

public class Father {
    private long l;
    private byte b1;
    private byte b2;


    public static void main(String[] args) {
        SizeOf sizeof = new AgentSizeOf();
        Father father = new Father();

        System.out.println("father's size: " + sizeof.sizeOf(father));
    }
}
