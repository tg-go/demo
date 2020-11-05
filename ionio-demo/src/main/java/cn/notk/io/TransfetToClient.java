package cn.notk.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author bo.luo
 * @date 2020/11/2 9:56
 */
public class TransfetToClient {

    public static void main(String[] args) throws IOException {
        TransfetToClient client = new TransfetToClient();
        client.testSendFile();
    }

    public void testSendFile() throws IOException {
        String host = "localhost";
        int port = 9026;
        SocketAddress sad = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.connect(sad);
        sc.configureBlocking(true);
        String fname = "123";
        long fsize = 1111, sendsize = 4096;
        FileChannel fc = new FileInputStream(fname).getChannel();
        long start = System.currentTimeMillis();
        long nsent = 0, current = 0;
        current = fc.transferTo(0, fsize, sc);

    }
}
