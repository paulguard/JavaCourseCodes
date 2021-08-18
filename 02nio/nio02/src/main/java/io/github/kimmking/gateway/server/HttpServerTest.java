package io.github.kimmking.gateway.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerTest {

    static int port = 8801;

    public static void main(String[] args) throws IOException {

        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

        ServerSocket serverSocket = new ServerSocket(port);

        while (true){
            try {
                Socket socket = serverSocket.accept();
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void service(Socket socket) {

        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio" + port;
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();

            printWriter.write(body);
            printWriter.close();

            //这里主要是为了让httpClient可以在server的socket关闭前，拿到返回的结果
            Thread.sleep(10);

            socket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
