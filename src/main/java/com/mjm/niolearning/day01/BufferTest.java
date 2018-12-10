package com.mjm.niolearning.day01;

import java.nio.Buffer;
import java.nio.CharBuffer;

import static com.mjm.niolearning.utils.Utils.printBufferInfo;

/**
 * Created by majunmin on 2018/12/1.
 */
public class BufferTest {

    public static void main(String[] args) {

        testBuffer();

        testBufferCopy();
    }

    private static void testBufferCopy() {
        /**
         * 调用duplicate方法实际上会创建原缓存区的一个拷贝，不是深拷贝，是浅拷贝，什么意思呢，就是这两个缓存区会共享数据元素，但每个缓存区的上界、容量、位置等属性是各自独立的；
         */
        CharBuffer charBuffer1 = CharBuffer.allocate(10);
        CharBuffer charBuffer2 = charBuffer1.duplicate();
        System.out.println("--------charBuffer1 ---------");
        printBufferInfo(charBuffer1);
        System.out.println("--------charBuffer2 ---------");
        printBufferInfo(charBuffer2);
        charBuffer1.put('H');
        charBuffer1.put('e');
        charBuffer1.put('l');
        charBuffer1.put('l');

        System.out.println("--------charBuffer1 ---------");
        printBufferInfo(charBuffer1);
        System.out.println("--------charBuffer2 ---------");
        printBufferInfo(charBuffer2);


        System.out.println();


        /**
         * slice方法复制缓冲区
         */
        charBuffer1.position(2).limit(5);
        CharBuffer charBufferSi = charBuffer1.slice();
        printBufferInfo(charBufferSi);



        /**
         * asReadOnlyBuffer方法复制缓冲区
         * java.nio.ReadOnlyBufferException
         */
        CharBuffer charBuffer3 = charBuffer1.asReadOnlyBuffer();
        charBuffer3.put('r');
        charBuffer3.put('w');
        charBuffer3.put('x');


    }

    private static void testBuffer() {
        CharBuffer charBuffer = CharBuffer.allocate(10);
        printBufferInfo(charBuffer);
        charBuffer.put('H');
        charBuffer.put('e');
        charBuffer.put('l');
        charBuffer.put('l');
        charBuffer.put('o');

        printBufferInfo(charBuffer);

        //缓冲区转呗就就绪
        charBuffer.flip();

        printBufferInfo(charBuffer);
    }

}
