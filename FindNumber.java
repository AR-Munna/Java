package package1;

import java.io.InputStream;
import java.util.Random;
import java.util.*;

public class FindNumber extends Thread {



    private int lo, hi;
    private int[] arr;
    private int find;
    private boolean flag;

    public FindNumber(int[] arr, int lo, int hi, int find) {
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
        this.find = find;
        flag = false;
    }

    @Override
    public void run() {
        for (int i = lo; i < hi; i++) {
            if(arr[i]==find) 
            {
               flag = true;
               break;
            }
            else flag = false;
        }
    }

    public static boolean findInt(int[] arr, int f) throws InterruptedException {
        int len = arr.length;
        boolean check = false;
        boolean check1 = false;

       
        FindNumber[] ts = new FindNumber[5];
        for (int i=0; i<5; i++) {
            ts[i] = new FindNumber(arr, (i * len) / 5, ((i + 1) * len / 5), f);
            ts[i].start();
            ts[i].join();
            check = ts[i].flag;            
            if(check==true)
            {
                check1 = true;
                break;
            }
        }
        return check1;
    }

    public static void main(String[] args) throws InterruptedException
    {
        
        int[] arr = new int[50];
        int f;

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < arr.length; i++) {
            Random rand = new Random();
            arr[i] = rand.nextInt(100) ;
            System.out.print(arr[i]+" ");
        }
        
         System.out.println();

        f = sc.nextInt();
        try{
               boolean find = findInt(arr, f);
               if(find == true)System.out.println(f+" is found");
               else System.out.println(f+" Not found");
        }
        catch(InterruptedException e)
        {
            System.out.println("Munna");
        }       
    }
}