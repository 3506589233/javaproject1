import java.util.Scanner;

public class Test3{
    private static final float t = 3.1415926f;
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        int r;
        r = cin.nextInt();
        System.out.print(r*r*t);
        cin.close();  
    }
}