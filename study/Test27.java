import java.util.*;

public class Test{
    public static void main(String[] args){
        Set<Integer> set = new TreeSet<>();
        try(Scanner cin = new Scanner(System.in)){
            System.out.println("确认要输入的元素个数：");
            int n = cin.nextInt();
            System.out.println("请输入元素：");
            while((n--)>0){
                int x = cin.nextInt();
                set.add(x);
            }
            for(Integer i:set) System.out.print(i+" ");
        }
    }
}