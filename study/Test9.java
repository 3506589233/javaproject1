class NegativeNumberException extends Exception{
    NegativeNumberException(){
        super("输入了负数");
    }
    NegativeNumberException(String message){
        super(message);
    }
}
public class Test{
    static void checkNegative(int x) throws NegativeNumberException{
        if(x<0) throw new NegativeNumberException();
    }
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        int x = cin.nextInt();
        try{
            checkNegative(x);
        }catch(NegativeNumberException e){
            System.out.print(e.getMessage());
        }
    }
}