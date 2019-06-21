package com.mjm.niolearning.day0618.filechannel;

import com.mjm.niolearning.utils.Utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-19 11:50
 * @since
 */
public class Test {

    public static void main(String[] args) {
//        test1();
//
//        testAsync();

        testRead();
    }

    /**
     * read()
     *
     * 正数:代表从通道的当前位置向 ByteBuffer缓冲区中读的字节个数。
     * 0  : 代表从通道中没有读取任何的数据，也就是 0字节，有可能发生的情况就是缓 冲区中没有 remainging剩余空间了。(缓冲区没有剩余空间了)
     * -1 : 代表到达流的末端。(流没有数据可以读了)
     */
    private static void testRead()  {

        int readLength;
        FileInputStream fis = null;
        FileChannel fileChannel = null;

        try {
            fis = new FileInputStream("a.txt");
            fileChannel = fis.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(5);
            readLength = fileChannel.read(byteBuffer);
            System.out.println("readLength: " + readLength);
//            byteBuffer.clear();

            readLength = fileChannel.read(byteBuffer);
            System.out.println("readLength: " + readLength);
            byteBuffer.clear();

            readLength = fileChannel.read(byteBuffer);
            System.out.println("readLength: " + readLength);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void testAsync() {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("./a.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileChannel channel = fos.getChannel();

        new Thread(()->{
            try {
                while (true) {
                    channel.write(ByteBuffer.wrap("abcde\n".getBytes()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                while (true) {
                    channel.write(ByteBuffer.wrap("我是中国人\n".getBytes()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * write(Bufffer buffer) 是从通道当前位置开始写入的
     */
    public static void test1(){

        ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());
        try {
            FileOutputStream fos = new FileOutputStream(new File("./a.txt"));
            FileChannel fosChannel = fos.getChannel();
            int write1 = fosChannel.write(byteBuffer);
            System.out.println("write(): " + write1);

            Utils.printChannelInfo(fosChannel);

            fosChannel.position(2);
            byteBuffer.rewind();
            int write2 = fosChannel.write(byteBuffer);
            System.out.println("write(): " + write2);

            Utils.printChannelInfo(fosChannel);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
