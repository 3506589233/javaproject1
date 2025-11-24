import java.util.*;

public class Test {
    public static void main(String[] args){
        int [] a = {1,3,4,5,6,8,11,24,37};
        int [] b = {2,5,7,9,23,35,46,47};
        List<Integer> l = new ArrayList<Integer>();
        int i = 0,j = 0;
        while(i<a.length&&j<b.length){
            if(a[i]<=b[j]) l.add(a[i++]);
            else l.add(b[j++]);
        }
        while(i<a.length){
            l.add(a[i++]);
        }
        while(j<b.length){
            l.add(b[j++]);
        }
        for(int k:l) System.out.print(k+" ");
    }
}