package com.mjm.niolearning.day0618.lock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 一句话功能简述 </br>
 *
 * FileChannel.lock(position, size, shared)
 * position 通道锁定的 位置
 * size 通道从位置开始锁定的大小
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-20 14:22
 * @since
 */
public class FileLockTest1 {
    private static RandomAccessFile raf;
    private static FileChannel fileChannel;

    public static void main(String[] args) throws IOException, InterruptedException {

        raf = new RandomAccessFile("a.txt", "rw");;
        fileChannel = raf.getChannel();
//        testSync();

//        testInterruptException();
        testSharedFileNotWrite();
    }


    private static void testSync() throws IOException, InterruptedException {
        RandomAccessFile raf = new RandomAccessFile("a.txt", "rw");
        FileChannel fileChannel = raf.getChannel();
        fileChannel.lock(1, 2, false);
        TimeUnit.SECONDS.sleep(240);
        fileChannel.close();
        raf.close();
    }

    /**
     * 在 lock() 调用期间，如果另一个线程关闭了此通道，
     * 则抛出 AsynchronousCloseException 异常。
     * @throws IOException
     */
    public static void testAsynchonousCloseException() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("a.txt", "rw");
        FileChannel fileChannel = raf.getChannel();

        new Thread(()->{
            System.out.println("A lock start");
            try {
                fileChannel.lock(1, 2, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("A lock end");
        }).start();


        new Thread(()->{
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        raf.close();
    }

    public static void testInterruptException() throws IOException, InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                for (int i = 0; i < 1000; i++) {
                    System.out.println(i);
                }
                fileChannel.lock(1, 2, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t1.interrupt();

        TimeUnit.SECONDS.sleep(60);

        fileChannel.close();
        raf.close();
    }

    /**
     * 共享锁 自己不能写
     * 共享锁 别人不能写
     * 共享锁 自己能读
     * 共享锁 别人能读
     *
     * 独占锁 自己能读 写
     * 独占锁 别人不能读 写
     *
     * 使用java.nio包的文件锁定适用于Windows操作系统，但相同的程序在 MacOS 上无法正常工作。
     *
     * 共享锁与独占锁之间的互斥关系
     *
     * 1. 共享锁与共享锁之间是非互斥关系;
     * 2. 共享锁与独占锁之间是互斥关系;
     * 3. 独占锁与共享锁之间是互斥关系;
     * 4. 独占锁与独占锁之间是互斥关系。
     *
     * @throws IOException
     */
    public static void testSharedFileNotWrite() throws IOException, InterruptedException {
        fileChannel.lock(1,10, false);
        TimeUnit.SECONDS.sleep(60);
//        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
//        fileChannel.read(byteBuffer, 4);
//        ByteBuffer buffer = ByteBuffer.wrap("abcd".getBytes());
//        fileChannel.write(buffer);

    }





}
