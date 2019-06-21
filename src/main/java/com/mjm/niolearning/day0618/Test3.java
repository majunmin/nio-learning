package com.mjm.niolearning.day0618;

import com.mjm.niolearning.utils.Utils;
import org.apache.commons.io.Charsets;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-18 17:09
 * @since
 */
public class Test3 {

    public static void main(String[] args) {
        byte[] byteArray1 = "我是中国人".getBytes(Charsets.UTF_16BE);
        System.out.println(Charset.defaultCharset().name());

        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteArray1);
        System.out.println(byteBuffer1.getClass().getName());

        byteBuffer1.position(9 );

        CharBuffer charBuffer = byteBuffer1.asCharBuffer();
        System.out.println(charBuffer.getClass().getName());

        Utils.printBufferInfo(byteBuffer1);
        Utils.printBufferInfo(charBuffer);

        charBuffer.position(0);
        for (int i = 0; i < charBuffer.capacity(); i++) {
            // 由于 get() 使用的编码方式为 UTF-16BE
            // 编码不一致导致乱码
            System.out.print(charBuffer.get() + " ");
        }
    }
}
