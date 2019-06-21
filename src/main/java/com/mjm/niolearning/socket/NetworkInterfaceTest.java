package com.mjm.niolearning.socket;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Objects;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-20 16:43
 * @since
 */
public class NetworkInterfaceTest {

    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while(networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println("networkInterface.getIndex() : " + networkInterface.getIndex());
            System.out.println("networkInterface.getName() : " + networkInterface.getName());
            System.out.println("networkInterface.getDisplayName() : " + networkInterface.getDisplayName());

            if (Objects.nonNull(networkInterface.getInetAddresses())){
                System.out.println("networkInterface.getInetAddresses() : " );
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while(inetAddresses.hasMoreElements()){
                    InetAddress inetAddress = inetAddresses.nextElement();
                    System.out.println("-- " + inetAddress.getHostAddress());
                }
            }

            if (Objects.nonNull(networkInterface.getHardwareAddress())) {
                System.out.print("networkInterface.getHardwareAddress() : ");
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                for (int i = 0; i < hardwareAddress.length; i++) {
                    /**
                     * 将得到的十进制数转化为 16 进制 就可以得到 硬件地址了
                     * example:
                     * 28 111 101 -66 8  73
                     * 1C 6F 65 BE 8 49
                     */
                    System.out.print(hardwareAddress[i] + " ");
                }
                System.out.println();
            }

            /**
             * 返回 MTU大小。
             * 在网络传输中是以数据包为基本传输单位，可以使用 MTU(Mimum Transmission Unit，最大传输单元)
             * 来规定网络传输最大数据包的大小，单位为字节。
             */
            System.out.println("networkInterface.getMTU() : " + networkInterface.getMTU());
            System.out.println("networkInterface.getSubInterfaces()  ... " );


            /**
             * 取得子接口. 什么是子接口?
             * 子接口的作用是在不添加新的物理网卡的基础上，基于原有的网络接口设备再创建出一个虚拟的网络接口设备进行通信，
             * 这个虚拟的网络接口可以理解成是一个由软件模拟的网卡。
             * Windows 操作系统不支持子接口，而 Linux 支持 。
             */
            Enumeration<NetworkInterface> subInterfaces = networkInterface.getSubInterfaces();
            while(subInterfaces.hasMoreElements()){
                NetworkInterface subNetworkInterface = subInterfaces.nextElement();
                System.out.println("has sub-----");
            }
        }
    }
}
