package com.mjm.niolearning.socket.one;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.io.*;
import java.net.Socket;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-20 17:39
 * @since
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9654);

            /**
             * write
             */
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            UserInfo userInfo = new UserInfo();
            userInfo.setName("majm");
            userInfo.setAge(25);
            userInfo.setSex("female");
            objectOutputStream.writeObject(userInfo);

            /**
             * read
             */
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            UserInfo userInfo1 = (UserInfo)objectInputStream.readObject();
            System.out.println(userInfo1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
