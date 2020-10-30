package cn.notk.io;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author bo.luo
 * @date 2020/10/30 14:42
 */
public class TraditionalClient {

    public static void main(String[] args) {

        int port = 2000;

        String server = "localhost";
        Socket socket = null;
        String lineToBeSent;

        DataOutputStream output = null;
        FileInputStream inputStream = null;

        int ERROR = 1;

        //连接到Server
        try {
            socket = new Socket(server, port);
            System.out.println("Connected with server " + socket.getInetAddress() + ":" + socket.getPort());
        } catch (Exception e) {
            System.out.println(e);
            System.exit(ERROR);
        }

        try {
            String fileName = "iotest.txt";
            String filePath = TraditionalClient.class.getClassLoader().getResource(fileName).getPath();
            System.out.println(filePath);
            inputStream = new FileInputStream(filePath);

            output = new DataOutputStream(socket.getOutputStream());
            long start = System.currentTimeMillis();
            byte[] b = new byte[4096];
            long read = 0, total = 0;
            while ((read = inputStream.read(b)) >= 0) {
                total = total + read;
                output.write(b);
            }
            System.out.println("bytes send--" + total + " and time cost:" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
