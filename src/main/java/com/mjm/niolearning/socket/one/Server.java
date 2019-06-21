package com.mjm.niolearning.socket.one;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传输的的对象需要 impelments Serializable </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-20 17:46
 * @since
 */
public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(9654);
            Socket socket = serverSocket.accept();

            /**
             * read
             */
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            UserInfo userInfo = (UserInfo)objectInputStream.readObject();
            System.out.println(userInfo);


            /**
             * write
             */
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(userInfo);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
