package day04workshop;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) throws NumberFormatException, IOException {

        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println("Server start at port: " + args[0]);
        Socket socket = serverSocket.accept();
        System.out.println("connection establised......");
        String fileName=args[1];
        String dirpath="cookieFloder";

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Cookie cookie = new Cookie(dirpath,fileName);
        List<String> cookies = new ArrayList<String>();
        boolean isStopped=false;

        cookie.readCookie(cookies);
        cookie.printCookie(cookies);

        while (!isStopped) {
            String readLine = dis.readUTF();
            System.out.println("messageReceived: "+readLine);
            if (readLine.equalsIgnoreCase("get-cookie")) {
                String randCookie = cookie.randCookie(cookies);
                System.out.println("randcokie is: "+randCookie);
                dos.writeUTF("cookie-text: " + randCookie);
                dos.flush();
            }else if(readLine.equalsIgnoreCase("close")) {
                isStopped=true;
            }else {
                System.out.println("Invalid command");
                continue;
            }
        }

        dis.close();
        dos.close();
        socket.close();
        serverSocket.close();
        System.out.println("Server closed");

    }

}
