package day06workshop;
import java.io.IOException;
import java.net.*;

public class CookieServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println("Server start at port: " + args[0]);
        int numberOfConnection=0;

        while (true) {
            Socket socket = serverSocket.accept();
            numberOfConnection++;
            System.out.println(numberOfConnection+"client(s) connection establised......");

            Thread thread = new Thread(new CookieClientHandler(socket));
            thread.start();
        }
    }
}
