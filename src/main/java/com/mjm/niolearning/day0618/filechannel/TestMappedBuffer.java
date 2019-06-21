package com.mjm.niolearning.day0618.filechannel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 将通道文件区域 直接映射到内存 </br>
 *
 * MappedByteBuffer map(FileChannel.MapMode mode, long position, long size)方法的作 用是将此通道的文件区域直接映射到内存中 。
 * 可以通过如下三种方式
 * 1. 只读(MapMode.READ_ONLY): 试图修改得到的缓冲区将导致抛出 ReadOnlyBufferException异常
 * 2. 读取/写人(MapMode.READ_WRITE): 对得到的缓冲区的更改最终将传播到文件;该更改对映射到同一文件 的其他程序不一定是可见的
 * 3. 专用(MapMode.PRIVATE): 对得到的缓冲区的更改不会传播到文件，并且该更改对映射到同一文件的其他程序也不是可见的;
 *                           相反，会创建缓冲区已修改部分的专用副本
 *
 * 映射关系一经创建，就不再依赖于创建它时所用的文件通道. 特别是关闭该通道对映射关系的有效性没有任何影响
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-20 15:53
 * @since
 */
public class TestMappedBuffer {

    private static RandomAccessFile raf;
    private static FileChannel fileChannel;

    public static void main(String[] args) throws IOException, InterruptedException {

        raf = new RandomAccessFile("a.txt", "rw");
        fileChannel = raf.getChannel();
        MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.position(0);
        map.put((byte)'1');
        map.put((byte)'2');
        map.put((byte)'3');
        map.put((byte)'4');
        map.force();
        System.out.println(map.isLoaded());
        System.out.println(map.load());
        System.out.println(map.isLoaded());
        fileChannel.close();
        raf.close();

    }
}
