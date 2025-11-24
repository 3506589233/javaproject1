public class Test10 {
    public static void main(String[] args) {
        try {
            BankAccount account = new BankAccount();
            Class<?> clazz = account.getClass();
            Method depositMethod = clazz.getDeclaredMethod("deposit", int.class);
            System.out.println("调用deposit方法前：");
            Method checkBalanceMethod = clazz.getDeclaredMethod("checkbalance");
            checkBalanceMethod.invoke(account);
            System.out.println("\n使用反射调用deposit方法存入500：");
            depositMethod.invoke(account, 500);
            System.out.println("\n调用deposit方法后：");
            checkBalanceMethod.invoke(account);
            System.out.println("\n再次使用反射调用deposit方法存入200：");
            depositMethod.invoke(account, 200);
            System.out.println("\n最终账户信息：");
            checkBalanceMethod.invoke(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}