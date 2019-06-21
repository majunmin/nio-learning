package com.mjm.niolearning.day0618;

import com.mjm.niolearning.utils.Utils;

import java.nio.ByteBuffer;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-18 16:39
 * @since
 */
public class Test2 {

    public static void main(String[] args) {
        byte[] byteArray1 = {1,2,3,4,5,6,6,8};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray1);
        byteBuffer.position(5);
        ByteBuffer slice = byteBuffer.slice();

        Utils.printBufferInfo(byteBuffer);
        Utils.printBufferInfo(slice);

        slice.put((byte) 111);

        byte[] array1 = byteBuffer.array();
        byte[] array2 = slice.array();

        System.out.println(array1 == array2);

        System.out.println(slice.arrayOffset());


    }
}
