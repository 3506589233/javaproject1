import java.util.*;

public class Test {
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        Map<Character,Integer> mp = new HashMap<Character,Integer>();
        String s = cin.next();
        for(char c:s.toCharArray()){
            if(mp.containsKey(c)){
                mp.put(c,mp.remove(c)+1);
            }else mp.put(c,1);
        }
        Set<Character> keyset = mp.keySet();
        for(Character c:keyset){
            System.out.print(c);
            System.out.println(": "+mp.get(c));
        }
        cin.close();
    }
}