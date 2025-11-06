import java.util.*;

public class Test {
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        List<Integer> l = new ArrayList<Integer>();
        Set<Integer> set = new LinkedHashSet<Integer>();
        int n;n = cin.nextInt();
        while(n-- != 0){
            int t;t = cin.nextInt();
            set.add(t);
        }
        for(int i:set) l.add(i);
        for(int i:l) System.out.print(i+" ");
        cin.close();
    }
}