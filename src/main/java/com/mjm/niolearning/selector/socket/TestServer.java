package com.mjm.niolearning.selector.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-21 10:54
 * @since
 */
public class TestServer {

    public static void main(String[] args) {
        testServer1();

//        testServer2();

//        testServer3();

        // 获取通道支持的 SocketOption
//        supportSocketOption();

//        getOrSetSocketOption();

//        testSelectorProvider();



//        testRegisterChannel();


    }

    /**
     * 注册通道与选择器
     *
     * 1. 相同的通道可以注册到不同的选择器，返回的 SelectionKey 不是同一个对象
     * 2. 不同的通道注册到相同的选择器，返回的 SelectionKey 不是同一个对象
     * 3. 不同的通道注册到不同的选择器，返回的 Selectionkey 不是同一个对象
     * 4. 相同的通道重复注册相同的选择器，返回的 SelectionKey 是同一个对象
     */
    private static void testRegisterChannel() {

        try (
                ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
                ServerSocketChannel serverSocketChannel2 = ServerSocketChannel.open();
        ) {
            serverSocketChannel1.configureBlocking(false);
            serverSocketChannel2.configureBlocking(false);
            Selector selector1 = Selector.open();
            Selector selector2 = Selector.open();

            SelectionKey selectionKey1 = serverSocketChannel1.register(selector1, SelectionKey.OP_ACCEPT);
            SelectionKey selectionKey2 = serverSocketChannel1.register(selector1, SelectionKey.OP_ACCEPT);
            System.out.println(selectionKey1 == selectionKey2);
            //...


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * provider1 = provider2
     * <p>
     * SelectorProvider
     * 多个并发线程可安全地使用此类中的所有方法
     */
    private static void testSelectorProvider() {

        SelectorProvider provider1 = SelectorProvider.provider();
        System.out.println(provider1);

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            SelectorProvider provider2 = serverSocketChannel.provider();
            System.out.println(provider2);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void getOrSetSocketOption() {

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            //通道支持什么， Socket Option 就只能设置什么，设置其他的 Socket Option 就会出现异常
            System.out.println("A SO_RCVBUF= " + serverSocketChannel.getOption(StandardSocketOptions.SO_RCVBUF));
            serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 5678);
            System.out.println("B SO_RCVBUF= " + serverSocketChannel.getOption(StandardSocketOptions.SO_RCVBUF));
            serverSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void supportSocketOption() {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                Socket socket = new Socket("localhost", 8989);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8989));
//            serverSocketChannel.configureBlocking(false);
            SocketChannel socketChannel = serverSocketChannel.accept();

            Set<SocketOption<?>> set1 = serverSocketChannel.supportedOptions();
            Set<SocketOption<?>> set2 = socketChannel.supportedOptions();

            System.out.println("ServerSocketChannel support Options:");
            for (SocketOption<?> socketOption : set1) {
                System.out.println(socketOption.name() + " : " + socketOption.type() + " : " + socketOption.getClass());
            }

            System.out.println("SocketChannel support Options:");
            for (SocketOption<?> socketOption : set2) {
                System.out.println(socketOption.name() + " : " + socketOption.type() + " : " + socketOption.getClass());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testServer3() {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {

            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8989));
            // 设置非阻塞模式
            // 如果不设置 则会抛出异常 IllegalBlockingModeException
            System.out.println("isBlocking : " + serverSocketChannel.isBlocking());
            serverSocketChannel.configureBlocking(false);
            System.out.println("isBlocking : " + serverSocketChannel.isBlocking());

            Selector selector = Selector.open();
            System.out.println("isRegistered : " + serverSocketChannel.isRegistered());
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("isRegistered : " + serverSocketChannel.isRegistered());

            System.out.println("selectionKey : " + selectionKey);
            System.out.println("selector : " + selector);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void testServer2() {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {

            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8989));
            // 设置非阻塞模式
            serverSocketChannel.configureBlocking(false);
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(2);
            int readLength = 0;
            while ((readLength = socketChannel.read(byteBuffer)) != -1) {
                String newStr = new String(byteBuffer.array());
                System.out.println(newStr);
                byteBuffer.flip();
            }
            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testServer1() {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {

            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8989));
            // 设置非阻塞模式
//            serverSocketChannel.configureBlocking(false);

            Instant start = Instant.now();
            SocketChannel socketChannel = serverSocketChannel.accept();
            Instant end = Instant.now();
            System.out.println(Duration.between(start, end).toMillis() + "ms");

            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
