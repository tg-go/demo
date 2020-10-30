package cn.notk.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author bo.luo
 * @date 2020/10/30 14:37
 */
public class TraditionalServer {

    public static void main(String[] args) {
        int port = 2000;
        ServerSocket serverSocket;
        DataInputStream input;
        try {
            // 开启ServerSocket
            serverSocket = new ServerSocket(port);
            System.out.println("Server Waiting for client on port" + serverSocket.getLocalPort());

            //开始循环监听
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
                input = new DataInputStream(socket.getInputStream());

                // 输出数据
                try {
                    byte[] byteArray = new byte[4096];
                    while (true) {
                        int nread = input.read(byteArray, 0, 4096);
                        System.out.println("nread="+nread);
                        if (0 == nread) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    socket.close();
                    System.out.println("Connection close by client");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
