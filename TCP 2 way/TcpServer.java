import java.net.*;
import java.io.*;
import java.util.*;

class TcpServer {
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket ss = new ServerSocket(5656);
            Socket s = ss.accept();
            Scanner scan = new Scanner(System.in);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            while (true) {
                String str = dis.readUTF();
                System.out.println(str);
                if (str.equals("bye")) {
                    System.out.println("Client Messaged .... BYE..Exiting");
                    dis.close();
                    s.close();
                    ss.close();
                    break;
                }
                String str1 = scan.nextLine();
                dout.writeUTF(str1);
                dout.flush();
            }
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}