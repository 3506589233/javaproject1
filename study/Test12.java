public class Test{
    public static void main(String[] args){
        new Thread(() -> {
            for(int i = 1;i<=10;i++){
                System.out.println(i);
                try{
                    Thread.sleep(10);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for(char c = 'A';c<='J';c++){
                System.out.println(c);
                try{
                    Thread.sleep(10);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}