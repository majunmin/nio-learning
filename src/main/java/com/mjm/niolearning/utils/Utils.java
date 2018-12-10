package com.mjm.niolearning.utils;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * Created by majunmin on 2018/12/9.
 */
public class Utils {

    public static void printBufferInfo(Buffer buffer) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("capacity : " + buffer.capacity());
        stringBuilder.append("\n");
        stringBuilder.append("position : " + buffer.position());
        stringBuilder.append("\n");
        stringBuilder.append("limit : " + buffer.limit());
        stringBuilder.append("\n");
        stringBuilder.append("mark : " + buffer.mark());

        System.out.println(stringBuilder);
    }

    public static void printChannelInfo(FileChannel channel) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("position : " + channel.position());
        stringBuilder.append("\n");
        stringBuilder.append("size : " + channel.size());


        System.out.println(stringBuilder);
    }

}
