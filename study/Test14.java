public class Test14 {
    static <T extends Comparable<T>> T max(T[] a){
        T t = a[0];
        for(T i:a) if(i.compareTo(t) > 0) t = i;
        return t;
    }
    public static void main(String[] args){
        Integer [] a = {1,2,3,4};
        Character [] c = {'a','b','b','d'};
        String [] s = {"12","ab","adk"};
        System.out.println(max(a));
        System.out.println(max(c));
        System.out.println(max(s));
    }
}