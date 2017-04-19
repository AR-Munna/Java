package thread;

import java.io.InputStream;
import java.util.Random;
import java.util.*;

public class Thread3 extends Thread {



    private int lo, hi;
    private int[] arr;
    private int find;
    private boolean flag;
    int[] index;
    private int cnt;

    public Thread3(int[] arr, int lo, int hi, int find) {
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
        this.find = find;
        flag = false;
        cnt=0;
        index = new int[500];
    }

    @Override
    public void run() {
        for (int i = lo; i < hi; i++) {
            if(arr[i]==find)
            {
               flag = true;
               index[cnt++] = i;
            }
            else flag = false;
        }

    }

    public static void findInt(int[] arr, int f) throws InterruptedException, NullPointerException {
        int len = arr.length;
        boolean check = false;
        boolean check1 = false;
        int[] ind = new int[500];
        int sz=0;


        Thread3[] ts = new Thread3[5];
        for (int i=0; i<5; i++) {
            ts[i] = new Thread3(arr, (i * len) / 5, ((i + 1) * len / 5), f);
            ts[i].start();
            ts[i].join();
          
            for(int j=0; j<ts[i].cnt; j++) System.out.print(ts[i].index[j]+" ");            
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException
    {


        int[] arr = new int[50];
        int f;

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < arr.length; i++) {
            Random rand = new Random();
            arr[i] = rand.nextInt(20) ;
            System.out.print(arr[i]+" ");
        }

         System.out.println();

        f = sc.nextInt();
        try{
             findInt(arr, f);        
        }
        catch(InterruptedException e)
        {
            System.out.println("Munna");
        }
    }
}