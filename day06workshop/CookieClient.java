package day06workshop;
import java.io.*;
import java.net.*;

public class CookieClient {
    public static void main(String[] args) throws IOException {
        String[] split=args[0].split(":");
        Socket socket=new Socket(split[0],Integer.parseInt(split[1]));


        Console con= System.console();
        String readLine="";
        String messageReceived="";
        DataInputStream dis=new DataInputStream(socket.getInputStream());
        DataOutputStream dos =new DataOutputStream(socket.getOutputStream());
        boolean isStopped=false;

        while(!isStopped){
           readLine = con.readLine("Please enter command: ");
           if(readLine.equalsIgnoreCase("get-cookie")){
                dos.writeUTF(readLine);
                dos.flush();
           }else if(readLine.equalsIgnoreCase("close")){
            dos.writeUTF(readLine);
            dos.flush();
            isStopped=true;
            break;
            }else{
                System.out.println("Please enter correct command (get_cookie or close).");
                continue;
            }
           messageReceived=dis.readUTF();
           System.out.println(messageReceived);
           
        }

        dis.close();
        dos.close();
        socket.close();
        System.out.println("Server closed");

    }
}
