public class Test22 {
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        Map<Character,Integer> mp = new HashMap<>();
        String s = cin.next();
        for(char c:s.toCharArray()){
            if(mp.containsKey(c)){
                mp.put(c,mp.get(c) + 1);
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