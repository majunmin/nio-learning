package com.mjm.niolearning.day04;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by majunmin on 2018-12-16.
 */
public class ConnectClient {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;

        try {
            socketChannel = SocketChannel.open();
            System.out.println(socketChannel.isConnectionPending());
            socketChannel.connect(new InetSocketAddress("alima1", 8088));
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
