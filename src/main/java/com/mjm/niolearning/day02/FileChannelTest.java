package com.mjm.niolearning.day02;

import com.mjm.niolearning.utils.Utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by majunmin on 2018/12/9.
 */
public class FileChannelTest {

    public static void main(String[] args) {

//        testAsync();

//        testOpenFile();
        testSparse();
    }


    /**
     * FileChannel read write 具有同步特性
     */

    public static void testAsync(){
        FileInputStream fis = null;
        FileChannel fileChannel;
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            fis = new FileInputStream(new File("src/main/resources/data/test.file"));
            fileChannel = fis.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(5);

            Runnable task = () -> {
                int readLength = 0;
                try{
                    while(-1 != (readLength = fileChannel.read(byteBuffer))){
                        System.out.println(new String(byteBuffer.array(), 0 , readLength));
                        byteBuffer.clear();
                    }
                } catch(Exception ex){}

            };


            executorService.submit(task);
            executorService.submit(task);
            executorService.submit(task);
            executorService.submit(task);
            executorService.submit(task);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
//                fis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
////            executorService.shutdown();
        }

    }

    public static void testOpenFile(){
        File file = new File("src/main/resources/newFile.txt");
        Path path = file.toPath();
        try {
            FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
            channel.write(ByteBuffer.wrap(new byte[]{'1','x','a','b','c'}));
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testSparse(){
        File file = new File("src/main/resources/sparse.txt");
        Path path = file.toPath();

        try {
            FileChannel fileChannel = FileChannel.open(path,
                    StandardOpenOption.CREATE_NEW,
//                    StandardOpenOption.SPARSE,
                    StandardOpenOption.WRITE);
            long fileSize = Integer.MAX_VALUE;
            fileSize = fileSize + fileSize + fileSize;
            fileSize = fileSize + fileSize;
            fileChannel.position(fileSize);
            fileChannel.write(ByteBuffer.wrap("a".getBytes()));
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
