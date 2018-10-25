package jvm.demo2;

import net.sf.ehcache.pool.sizeof.AgentSizeOf;
import net.sf.ehcache.pool.sizeof.SizeOf;

public class Father {
    int i;
    short s;
}

class Son extends Father {
    byte b;
    long l;

    public static void main(String[] args) {
        SizeOf sizeOf = new AgentSizeOf();
        Father father = new Son();

        System.out.println("father's size: " + sizeOf.sizeOf(father));
    }
}
