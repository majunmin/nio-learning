package com.mjm.niolearning.day0618;

import com.mjm.niolearning.utils.Utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-18 18:28
 * @since
 */
public class Test4 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Utils.printBufferInfo(byteBuffer);

        System.out.println("byteBuffer.get() " + byteBuffer.get());
        Utils.printBufferInfo(byteBuffer);

        byteBuffer.get();

        byteBuffer.compact();
        Utils.printBufferInfo(byteBuffer);


        byte[] array = byteBuffer.array();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");

        }

        CharBuffer charBuffer = CharBuffer.allocate(20);
        charBuffer.append("abcde");
        CharBuffer charBuffer1 = charBuffer.subSequence(3, 6);
        Utils.printBufferInfo(charBuffer);
        Utils.printBufferInfo(charBuffer1);


    }
}
