package com.mjm.niolearning.selector.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.time.Duration;
import java.time.Instant;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-21 11:09
 * @since
 */
public class TestClient {

    public static void main(String[] args) {

//        testCient1();

        testClient2();

    }

    private static void testClient2() {

        Instant start = null;
        Instant end = null;

        try (SocketChannel socketChannel = SocketChannel.open()){

            start = Instant.now();
            // SocketChannel 是阻塞模式
            // 在发生错误或连接到目标之前， connect ()方法一直是阻塞的
            // 如果连接是 阻塞模式， connect() 是立即返回的， 返回 true
            // 如果非阻塞模式, 连接不是立即建立的, 则此方法返回false，并且以后必须通过调用fishConnect()方法来验证连接是否完成 。
            boolean connectResult = socketChannel.connect(new InetSocketAddress("localhost", 8989));

            end = Instant.now();
            System.out.println("正常连接耗时: " + Duration.between(start, end).toMillis() + "ms");

        } catch (IOException e) {
            end = Instant.now();
            e.printStackTrace();
            System.out.println("异常链接耗时: " + Duration.between(start, end).toMillis() + "ms");
        }
    }

    private static void testCient1() {
        try (Socket socket = new Socket("localhost", 8989)){
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("i am chinese!".getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
