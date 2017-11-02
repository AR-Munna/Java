/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.net.Socket;

/**
 *
 * @author Student
 */
public class Reciever {
  

    public static void main (String args[]) throws Exception
    {
        FileWriter fout = new FileWriter("out.txt");
        Socket s = new Socket ("localhost",5555);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        String msg,outputTxt = "";

        String Divisor = "10001000000100001";
        while(true) {
            msg = din.readUTF();
            if (msg.equals("0000"))
                break;
            if (msg.length() < 26)
                outputTxt += msg;
            else {
                System.out.print("Code: " + msg );
               

            }
        }
        while(outputTxt.length()>0)
        {
            String temp;
            if(outputTxt.length()>=8) {
                temp= outputTxt.substring(0,8);
                outputTxt = outputTxt.substring(8);
            }
            else temp = outputTxt;
            char ch = (char)Integer.parseInt(temp,2);
            //System.out.println(ch);
            fout.write(ch);
        }
        fout.close();
    }
}
