package com.mjm.niolearning.day0618;

import java.nio.ByteBuffer;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-18 15:41
 * @since
 */
public class Test1 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 4);
        byteBuffer.putChar('a'); // 占 2 byte 0-1
        byteBuffer.putChar('b'); // 2-3
        System.out.println(byteBuffer.position());
        byteBuffer.putDouble(1.1); // 8byte
        byteBuffer.putDouble(2.2);
        System.out.println(byteBuffer.position());
        byteBuffer.putShort((short)1);
        byteBuffer.putShort((short)2);
        System.out.println("byteBuffer position: " + byteBuffer.position());
        System.out.println("byteBuffer limit: " + byteBuffer.limit());

        byteBuffer.flip();
        System.out.println("byteBuffer position: " + byteBuffer.position());
        System.out.println("byteBuffer limit: " + byteBuffer.limit());

        byte[] array = byteBuffer.array();
        for (byte b : array) {
            // System.out.println(b);
        }
//        System.out.println(new String(array));
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getShort());

    }
}
