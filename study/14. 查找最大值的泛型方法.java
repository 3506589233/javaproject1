
public class Test {
    static <T extends Comparable<T>> T max(T[] a){
        T t = a[0];
        for(T i:a) if(i.compareTo(t) > 0) t = i;
        return t;
    }
    static int max(int[] a) {
        int t = a[0];
        for(int i:a) if(i>t) t = i;
        return t;
    }
    static double max(double[] a) {
        double t = a[0];
        for(double i:a) if(i>t) t = i;
        return t;
    }
    static char max(char[] a) {
        char t = a[0];
        for(char i:a) if(i>t) t = i;
        return t;
    }
    public static void main(String[] args){
        int [] a = {1,2,3,4};
        char [] c = {'a','b','b','d'};
        String [] s = {"12","ab","adk"};
        System.out.println(max(a));
        System.out.println(max(c));
        System.out.println(max(s));
    }
}