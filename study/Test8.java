import java.util.*;

class Product{
    private int id,price;
    private String name;
    Product(){};
    Product(int id,String name,int price){
        this.id = id;
        this.price = price;
        this.name = name;
    }
    void show(){
        System.out.println("id:"+ id + " ,name:" + name + " ,price:" + price);
    }
}


abstract class Manager{
    ArrayList<Product> products = new ArrayList<Product>();
    static int cnt = 0;
    abstract void initiate();
    static int showMnue(){
        System.out.println("----菜单----");
        System.out.println("1. 添加商品");
        System.out.println("2. 查询所有商品");
        System.out.println("3. 退出");
        int op;
        try{
            op = Test.cin.nextInt();
        }catch(Exception e){
            System.out.println("非法输入");
            op = 3;
        }
        return op;
    }
    void add(String name,int price){
        products.addLast(new Product(cnt++,name,price));
    }
    void getProducts(){
        for(Product product:products){
            product.show();
        }
    }
}

class Fruts extends Manager{
    void initiate(){
        products.addLast(new Product(cnt++,"苹果",10));
        products.addLast(new Product(cnt++,"梨",8));
        products.addLast(new Product(cnt++,"葡萄",5));
    }
}

public class Test8{
    public static Scanner cin = new Scanner(System.in);
    public static void main(String[] args){
        Fruts fruts = new Fruts();
        fruts.initiate();
        int op;
        do{
            op = fruts.showMnue();
            switch(op){
                case 1:
                    System.out.print("请输入商品名称：");
                    String name = cin.next();
                    System.out.print("请输入商品价格：");
                    int price = cin.nextInt();
                    System.out.println("添加成功，按任意键返回...");
                    fruts.add(name,price);
                    break;
                case 2:
                    fruts.getProducts();
                    System.out.println("按任意键继续...");
                    System.out.flush();
                    break;
                default: op = 3;
            }
            if(op!=3) cin.next();
        }while(op!=3);
        System.out.println("--系统已退出--");
        cin.close();
    }
}

