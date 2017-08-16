import java.net.*;
import java.io.*;

public class DeSpreadingServer {


    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(3456);
        Socket clientSocket = ss.accept();
        System.out.println("Server Connected to: " + clientSocket.getInetAddress());

        DataInputStream din = new DataInputStream(clientSocket.getInputStream());

        FileWriter fw[] = new FileWriter[4];

        String spread[] = new String[4];

        spread[1] = "0101";
        spread[2] = "0011";
        spread[3] = "0000";

        for (int i = 1; i <= 3; i++) {
            fw[i] = new FileWriter("out" + i + ".txt", false);
        }

        BufferedWriter bw[] = new BufferedWriter[6];

        for (int i = 1; i <= 3; i++) {
            bw[i] = new BufferedWriter(fw[i]);
        }

        String data[] = new String[5];

        System.out.println("This is Server: ");

        while (true) {


            data[1] = din.readUTF();
            if(data[1].equals("STOP")) break;
            else if(data[1].equals("-1"))
            {
                for(int i = 1; i<=3; i++)   bw[i].newLine();
                continue;
            }
            data[2] = din.readUTF();
            data[3] = din.readUTF();
            String voltageSum = din.readUTF();

            System.out.println(data[1]);
            System.out.println(data[2]);
            System.out.println(data[3]);
            System.out.println(voltageSum);

            int sum[] = {0, 0, 0, 0};

            int i = 0, s = 0, sp = 0;

            while (true) {
                if (i >= 16) {
                    break;
                }

                s = voltageSum.charAt(i + 1) - '0';
                if (voltageSum.charAt(i) == '-') {
                    s *= -1;
                }

                for (int j = 1; j <= 3; j++) {

                    if(spread[j].charAt(sp)=='0')  sum[j] += s;
                    else sum[j] -= s;
                }
                sp++;

                i += 2;

                if (i == 8 || i == 16) {
                    for (int j = 1; j <= 3; j++) {

                        sum[j] /= 4;

                        if(sum[j]==1)
                        {
                            bw[j].write("0");
                            System.out.println("User " + j + ": " + 0);
                        }
                        else {
                            bw[j].write("1");
                            System.out.println("User " + j + ": " + 1);
                        }

                        sum[j] = 0;
                        sp = 0;
                    }

                }

            }

        }

        for(int i = 1; i<=3; i++)   bw[i].close();

    }
}
