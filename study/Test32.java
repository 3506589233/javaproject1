import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount implements Serializable {
    private String accountId,userName;
    private double balance;

    BankAccount(String accountId, String userName, double balance) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = balance;
    }
    synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() +
                    " 存款成功: " + amount + "元，账户 " + accountId + " 当前余额: " + balance);
        }
    }
    synchronized void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() +
                    " 取款成功: " + amount + "元，账户 " + accountId + " 当前余额: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " 取款失败，账户 " + accountId + " 余额不足，当前余额: " + balance);
        }
    }
    double getBalance() {
        return balance;
    }
    String getAccountId() {
        return accountId;
    }
    public String getUserName() {
        return userName;
    }
    public String toString() {
        return ("AccountId：" + accountId + '\n' +
                "UserName：" + userName + '\n' +
                "Balance：" + balance
        );
    }
}

class BankManager {
    private static final String dataFile = "accounts.dat";
    private Map<String, BankAccount> accounts;

    BankManager(){
        loadAccounts();
    }
    public synchronized void addAccount(String accountId, String userName, double balance) {
        if (accounts.containsKey(accountId)) {
            System.out.println("创建失败,账户 " + accountId + " 已存在！");
            return;
        }
        accounts.put(accountId, new BankAccount(accountId, userName, balance));
        System.out.println("账户 "+ accountId + " 创建成功!" );
    }
    public void checkBalance(String accountId) {
        BankAccount account = accounts.get(accountId);
        if (account != null) {
            System.out.println(Thread.currentThread().getName() +
                    " 查询账户 " + accountId + " 余额: " + account.getBalance());
        } else {
            System.out.println("查询账户 " + accountId + " 不存在！");
        }
    }

    public synchronized void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(accounts);
            System.out.println("账户信息已保存，共 " + accounts.size() + " 个账户");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            accounts = (Map<String, BankAccount>) ois.readObject();
        } catch (FileNotFoundException e) {
            accounts = new HashMap<>();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public synchronized void showAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("暂无账户信息");
            return;
        }
        System.out.print("------所有账户信息------");
        for (BankAccount account : accounts.values()) {
            System.out.println("\n" + account);
        }
        System.out.println("-----------------------");
    }

    public BankAccount getAccount(String accountId) {
        return accounts.get(accountId);
    }
}

class Mythread implements Runnable {
    private BankManager bank;
    private String accountId;
    private String operationType;
    private double amount = 0;

    Mythread(BankManager bank, String accountId, String operationType, double amount) {
        this.bank = bank;
        this.accountId = accountId;
        this.operationType = operationType;
        this.amount = amount;
    }
    Mythread(BankManager bank, String accountId, String operationType) {
        this.bank = bank;
        this.accountId = accountId;
        this.operationType = operationType;
    }
    @Override
    public void run() {
        BankAccount account = bank.getAccount(accountId);
        if (account == null) {
            System.out.println("账户 " + accountId + " 不存在！");
            return;
        }
        switch (operationType) {
            case "deposit":
                account.deposit(amount);
                break;
            case "withdraw":
                account.withdraw(amount);
                break;
            case "check":
                bank.checkBalance(accountId);
                break;
            default:
                System.err.println("未知操作: " + operationType);
        }
    }
}

public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 银行账户管理系统 ===");

        BankManager bankSystem = new BankManager();
        bankSystem.addAccount("001", "张三", 1000.0);
        bankSystem.addAccount("002", "李四", 2000.0);
        bankSystem.addAccount("003", "王五", 1500.0);
        bankSystem.showAccounts();

        System.out.println("多线程操作测试:");
        Thread t1 = new Thread(new Mythread(bankSystem, "001", "deposit", 500), "线程1");
        Thread t2 = new Thread(new Mythread(bankSystem, "001", "withdraw", 200), "线程2");
        Thread t3 = new Thread(new Mythread(bankSystem, "001", "check"), "线程3");
        Thread t4 = new Thread(new Mythread(bankSystem, "002", "deposit", 1000), "线程4");
        Thread t5 = new Thread(new Mythread(bankSystem, "002", "withdraw", 500), "线程5");
        t1.start();t2.start();t3.start();t4.start();t5.start();
        t1.join();t2.join();t3.join();t4.join();t5.join();

        System.out.println("\n操作后账户状态:");
        bankSystem.showAccounts();
        bankSystem.saveAccounts();
    }
}