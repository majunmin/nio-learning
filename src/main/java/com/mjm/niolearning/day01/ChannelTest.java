package com.mjm.niolearning.day01;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Created by majunmin on 2018/12/1.
 *
 * 既可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的。
 * channel 可以异步的读写
 * 通道中的数据总是要先读到一个Buffer，或者总是要从一个Buffer中写入
 *
 * channel --->  Buffer
 * Buffer ---> channel
 *
 * FileChannel          从文件中读写数据。
 * DatagramChannel      能通过UDP读写网络中的数据。
 * SocketChannel        能通过TCP读写网络中的数据
 * ServerSocketChannel  可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel
 */
public class ChannelTest {

    public static void main(String[] args) throws InterruptedException {
//        readChannel();
//
//        writeChannel();

        Thread thred = new Thread(){
            @Override
            public void run() {
                testDataGrameChannelReceive();
            }
        };
        thred.start();

        TimeUnit.SECONDS.sleep(4);
        testDataGrameChannelSend();
    }

    private static void testDataGrameChannelSend() {
        String newData = "sdfa jsdf send at bb" + LocalDateTime.now();
        try {
            DatagramChannel channel = DatagramChannel.open();
            ByteBuffer byteBuffer = ByteBuffer.allocate(60);
            byteBuffer.put(newData.getBytes());
            int localhost = channel.send(byteBuffer, new InetSocketAddress("localhost", 9090));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testDataGrameChannelReceive() {

        DatagramChannel datagramChannel = null;
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.bind(new InetSocketAddress("localhost",9090));

            ByteBuffer byteBuffer = ByteBuffer.allocate(80);
            byteBuffer.clear();
            /**
             * receive()方法会将接收到的数据包内容复制到指定的Buffer. 如果Buffer容不下收到的数据，多出的数据将被丢弃
             */
            datagramChannel.receive(byteBuffer);
            byteBuffer.flip();

            while(byteBuffer.hasRemaining()){
                System.out.println((char)byteBuffer.get());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeChannel() {
        String newData = "i am String \n" +
                " i love Builder\n" +
                " but Buffer ...\n";

        ByteBuffer byteBuffer = ByteBuffer.allocate(80);
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(new File("src/main/resources/data/newFile.o"), "rw");
            FileChannel channel = raf.getChannel();

            byteBuffer.put(newData.getBytes());
            while(byteBuffer.hasRemaining()){
                channel.write(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readChannel() {
        RandomAccessFile raf = null;
        try {

            raf = new RandomAccessFile(new File("src/main/resources/data/nio") , "rw");
            FileChannel fileChannel = raf.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(40);

            int bytesRead = fileChannel.read(byteBuffer);
            while(bytesRead != -1){
                System.out.println("Read : " + bytesRead);
                byteBuffer.flip();

                /**
                 *     public final boolean hasRemaining() {
                 *         return position < limit;
                 *     }
                 */
                while(byteBuffer.hasRemaining()){
                    System.out.println((char) byteBuffer.get());
                }
                byteBuffer.clear();
                bytesRead = fileChannel.read(byteBuffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
