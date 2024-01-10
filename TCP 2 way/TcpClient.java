import java.net.*;
import java.io.*;
import java.util.*;

class TcpClient {
    public static void main(String[] args) throws Exception {
        try {
            Scanner scan = new Scanner(System.in);
            Socket s = new Socket("localhost", 5656);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            while (true) {
                String str = scan.nextLine();
                dout.writeUTF(str);
                dout.flush();
                if (str.equals("bye")) {
                    dout.close();
                    s.close();
                    break;
                }
                String str1 = (String) dis.readUTF();
                System.out.println(str1);
            }
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
