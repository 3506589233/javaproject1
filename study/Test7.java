class BankAcount{
    private int balance;
    BankAcount(){
        balance = 0;
    }
    void deposit(int d){
        if(d<=0) System.out.println("存款不可小于或等于0");
        else{
            balance += d;
            System.out.println("存款成功");
        }
    }
    void withdraw(int d){
        if(balance < d) {System.out.println("余额不足");return;}
        if(d<=0) System.out.println("取款不可小于或等于0");
        else{
            balance -= d;
            System.out.println("取款成功");
        }
    }
    void checkbalance(){
        System.out.println("您当前余额为：" + balance);
    }
}

public class Test7{
    public static void main(String[] args){
        BankAcount count = new BankAcount();
        count.deposit(10);
        count.checkbalance();
        count.withdraw(5);
        count.checkbalance();
    }
}