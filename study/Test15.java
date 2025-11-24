import java.util.*;

public class Test15 {
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        List<Integer> a = new ArrayList<>();
        System.out.println("请输入元素个数:");
        int n;n = cin.nextInt();
        System.out.println("请输入元素:");
        while(n-- > 0){
            int t;t = cin.nextInt();
            a.add(t);
        }
        a.sort(null);
        int i = 0,j = 0;
        while(j<a.size()){
            a.set(i,a.get(j));
            while(j<a.size() && a.get(j)==a.get(i)) j++;
            ++i;
        }
        while(a.size()>i) a.removeLast();
        for(int t:a) System.out.print(t+" ");
        cin.close();
    }
}