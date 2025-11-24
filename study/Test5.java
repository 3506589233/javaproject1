import java.util.*;

public class Test5{
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        int t = cin.nextInt();
        String s;
        if(t%4==0 && t%100 != 0 || t % 400 == 0){
            s = "年是闰年";
        }else s = "年不是闰年";
        System.out.print(t + s);
    }
}