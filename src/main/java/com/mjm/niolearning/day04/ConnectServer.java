package com.mjm.niolearning.day04;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by majunmin on 2018-12-16.
 */
public class ConnectServer {

    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8088));
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
