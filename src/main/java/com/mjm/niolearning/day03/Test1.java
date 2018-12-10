package com.mjm.niolearning.day03;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by majunmin on 2018/12/10.
 */
public class Test1 {

    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while(networkInterfaces.hasMoreElements()){
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                System.out.println("网络设备名称： " + networkInterface.getName());
                System.out.println("网络设备显示名称： " + networkInterface.getDisplayName());
                System.out.println("网络接口的索引： " + networkInterface.getIndex());
                System.out.println("isUp是否开启并运行： " + networkInterface.isUp());
                System.out.println("isLookUp是否是回调地址： " + networkInterface.isLoopback());
                System.out.println("最大传输单元Max Transmission Unit: ： " + networkInterface.getMTU());
                System.out.println("\t--------------------------------");
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while(inetAddresses.hasMoreElements()){
                    InetAddress inetAddress = inetAddresses.nextElement();
                    System.out.println("\tHOST_NAME: "+ inetAddress.getHostName());
                    System.out.println("\tHOST_ADDRESS: "+ inetAddress.getHostAddress());
                    System.out.println("\t--------------------------------");
                }
                System.out.println("==========================");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
