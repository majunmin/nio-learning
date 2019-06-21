package com.mjm.niolearning.day0618.filechannel;

import com.mjm.niolearning.utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-19 16:55
 * @since
 */
public class Test1 {

    public static void main(String[] args) throws IOException {

//        testWriteBucket();
//
//        testReadBucket();

        testTruncate();


    }

    private static void testReadBucket() {

    }

    /**
     *
     * 批量写操作  Gather
     * 1. 将 1 个 ByteBuffer 缓冲区中的 remaining 字节序列写人通道 的当前位置中;
     * 2. write(ByteBuffer)方法是同步的。
     * 3. 将多个 ByteBuffer缓冲区中的 remaining剩余字节序列写入通道的当前位置中 。
     *
     */
    private static void testWriteBucket() {
        FileOutputStream fos = null;
        FileChannel fileChannel = null;

        try {
            fos = new FileOutputStream("b.txt");
            fileChannel = fos.getChannel();
            ByteBuffer[] byteBuffers = {ByteBuffer.wrap("abcde".getBytes()), ByteBuffer.wrap("fghijklmn".getBytes())};
            fileChannel.write(byteBuffers);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * truncate(long size)方法的作用是将此通道的文件截取为给定大小 。
     *   如果给定大小 < 该文件的当前大小，则截取该文件，丢弃文件新末尾后面的所有字节。
     *   如果给定大小 >= 该文件的当前大小，则不修改文件。
     * 无论是哪种情况，如果此通道的文件位置大于给定大小，则将位置设置为该大小 。
     */
    public static void testTruncate() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());
        FileOutputStream fos = new FileOutputStream("b.txt");
        FileChannel channel = fos.getChannel();

        channel.write(byteBuffer);
        Utils.printChannelInfo(channel);
        channel.truncate(3);
        Utils.printChannelInfo(channel);

        channel.close();
        fos.close();
    }
}
