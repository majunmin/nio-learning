package com.mjm.niolearning.day04;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * Created by majunmin on 2018-12-16.
 */
public class ConnectClient1 {

    public static void main(String[] args) {
        SocketChannel socketChannel;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            boolean connectResult = socketChannel.connect(new InetSocketAddress("localhost", 8088));
            if (!connectResult){
                System.out.println("connectResult == false");
                while(!socketChannel.finishConnect()){
                    System.out.println("尝试连接！！");
                }

            }

            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
