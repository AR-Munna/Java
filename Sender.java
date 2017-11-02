/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Sender {
   
    public static void main(String args[]) throws Exception {

        ServerSocket ss = new ServerSocket(5555);
        Socket s = ss.accept();
        System.out.println("Decoder Connected.");
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        FileReader fin = new FileReader("in.txt");
        Scanner sc = new Scanner(fin);
       
        String input = "";
        
        int cnt = 0;
        while (true) {
            int ch = fin.read();
            if (ch == -1) {
                break;
            }
           
            System.out.println(ch);
        }
        //System.out.println(cnt);
//        while(inputTxt.length()>0)
//        {
//            
//            dout.writeUTF(codeWord);
//        }
//        dout.writeUTF("0000");

    }
    
}
