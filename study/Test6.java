import java.util.*;

public class Test{
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        int a = cin.nextInt();
        char op = cin.next().charAt(0);
        int b = cin.nextInt();
        cin.next();
        int c = 0;
        switch(op){
            case '+':
                c = a+b;
                break;
            case '-':
                c = a-b;
                break;
            case '*':
                c = a*b;
                break;
            case '/':
                try{
                    System.out.print(a*1.0/b);
                }catch(Exception e){
                    System.out.print(e);
                    return ;
                }
                break;
            default : c = 0;
        }
        if(op != '/') System.out.print(c);
        cin.close();
    }
}