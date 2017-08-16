import java.io.*;
import java.net.*;
import java.util.*;

public class SpreadingServer { // client

    static String convertToBitStream(String code, char bit) {

        String s = "";

        for (int i = 0; i < 4; i++)
            s = ((code.charAt(i) == bit)?s + '0': s+'1');

        return s;
    }

    public static void main(String[] args) throws IOException {

        Socket echoSocket = new Socket("localhost", 3456);

        System.out.println("Server connected");

        DataInputStream din = new DataInputStream(echoSocket.getInputStream());
        DataOutputStream dout = new DataOutputStream(echoSocket.getOutputStream());

        FileReader fr[] = new FileReader[4];

        Scanner sc[] = new Scanner[4];

        for (int i = 1; i <= 3; i++) {
            fr[i] = new FileReader("input" + i + ".txt");

            sc[i] = new Scanner(fr[i]);
        }

        String data[] = new String[4];

        String spread1 = "0101";
        String spread2 = "0011";
        String spread3 = "0000";

        while(true) {

            boolean flag = false;

            for (int i = 1; i <= 3; i++) {
                if (sc[i].hasNext()) {
                    data[i] = sc[i].nextLine();
                    flag = true;
                    System.out.println("Data " + i + ": " + data[i]);
                }
            }

            if(!flag)
            {
                dout.writeUTF("STOP");
                break;
            }

            for (int i = 0; i < data[1].length(); i += 2) {
                String tmp1 = "", tmp2 = "", tmp3 = "";

                tmp1 += convertToBitStream(spread1, data[1].charAt(i));
                tmp1 += convertToBitStream(spread1, data[1].charAt(i + 1));

                tmp2 += convertToBitStream(spread2, data[2].charAt(i));
                tmp2 += convertToBitStream(spread2, data[2].charAt(i + 1));

                tmp3 += convertToBitStream(spread3, data[3].charAt(i));
                tmp3 += convertToBitStream(spread3, data[3].charAt(i + 1));

                String voltage[] = new String[4];

                voltage[1] = "";
                voltage[2] = "";
                voltage[3] = "";

                String voltageSum = "";

                for (int j = 0; j < 8; j++) {

                    if(tmp1.charAt(j)=='0')   voltage[1] += "+1";
                    else voltage[1] += "-1";

                    if(tmp2.charAt(j)=='0')   voltage[2] += "+1";
                    else voltage[2] += "-1";

                    if(tmp3.charAt(j)=='0')   voltage[3] += "+1";
                    else voltage[3] += "-1";

                }

                for(int j = 0; j<voltage[1].length(); j+=2)
                {
                    int x = 0;

                    x = (voltage[1].charAt(j)=='+'? x+1: x-1);
                    x = (voltage[2].charAt(j)=='+'? x+1: x-1);
                    x = (voltage[3].charAt(j)=='+'? x+1: x-1);

                    String val = Integer.toString(x);

                    if(x>0) val = "+" + val;

                    voltageSum += val;
                }

                System.out.println(voltage[1]);
                System.out.println(voltage[2]);
                System.out.println(voltage[3]);
                System.out.println(voltageSum);

                dout.writeUTF(voltage[1]);
                dout.flush();
                dout.writeUTF(voltage[2]);
                dout.flush();
                dout.writeUTF(voltage[3]);
                dout.flush();
                dout.writeUTF(voltageSum);
                dout.flush();

            }

            dout.writeUTF("-1");
        }

        for(int i = 1; i<=3; i++)   fr[i].close();
        din.close();
        dout.close();

    }

}
