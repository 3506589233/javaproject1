import java.util.*;

public class Test {
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int [] a = new int[n];
        for(int i = 0;i<n;i++){
            int t = cin.nextInt();
            a[i] = t;
        }
        int end = n-1;
        while(end>0){
            for(int i = 0;i<end;i++){
                if(a[i] > a[i+1]){
                    int t = a[i+1];
                    a[i+1] = a[i];
                    a[i] = t;
                }
            }
            end--;
        }
        for(int i:a) System.out.print(i+" ");
        cin.close();
    }
}