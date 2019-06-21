package com.mjm.niolearning.day0618.lock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-20 14:09
 * @since
 *
 * FileLock lock(long position, long size, boolean shared)
 * 作用是获取此通道的文件给定区域上的锁定。
 * 在可以锁定该区域之前,己关闭此通道之前 或者 已中断调用线程之前(以先到者为准),将阻塞此方法的调用 。
 *
 * 在此方法调用期间，如果另一个线程关闭了此通道， 则抛出 AsynchronousCloseException 异常。
 */
public class FileLockTest2 {

    private static FileChannel fileChannel;

    private static RandomAccessFile raf;

    public static void main(String[] args) throws IOException, InterruptedException {
        raf = new RandomAccessFile("a.txt", "rw");
        fileChannel = raf.getChannel();

//        testSync();

        testSharedFileNotWrite();

    }

    private static void testSync() throws IOException, InterruptedException {
        System.out.println("B lock start");
        fileChannel.lock(2, 2, false);
        System.out.println("B lock end");

        fileChannel.close();
        raf.close();
    }

    private static void testSharedFileNotWrite() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("a.txt", "rw");
        FileChannel fileChannel = raf.getChannel();
        fileChannel.write(ByteBuffer.wrap("cccc".getBytes()));
    }


}
