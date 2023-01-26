package day06workshop;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class CookieClientHandler implements Runnable {
    private Socket socket;

    public CookieClientHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {

      try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Cookie cookie = new Cookie("cookieFolder","cookie_file.txt");
        List<String> cookies = new ArrayList<String>();
        boolean isStopped=false;

        cookie.readCookie(cookies);
        //cookie.printCookie(cookies);

        while (!isStopped) {
            String readLine = dis.readUTF();
            //System.out.println("messageReceived: "+readLine);
            if (readLine.equalsIgnoreCase("get-cookie")) {
                String randCookie = cookie.randCookie(cookies);
                //System.out.println("randcokie is: "+randCookie);
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
      } catch (IOException e) {
        e.printStackTrace();
      }finally{
          try {
            socket.close();
          } catch (IOException e) {
            e.printStackTrace();
          try {
            socket.close();
          } catch (IOException ex) {
            ex.printStackTrace();
          }
          System.out.println("Server closed");
    
          }
      } 
    }
}
