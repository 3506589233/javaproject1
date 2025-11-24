import java.io.*;
import java.util.*;

class Book implements Serializable {
    private String isbn, name, author;
    private int num;

    Book(String isbn, String name, String author, int num) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.num = num;
    }

    void show() {
        System.out.println("ISBN："+isbn);
        System.out.println("Name："+name);
        System.out.println("Author："+author);
        System.out.println("Num："+num);
    }

    void setNum(int num){
        this.num = num;
    }
    int getNum(){
        return num;
    }
    String getIsbn(){
        return isbn;
    }
    String getName(){
        return name;
    }
}

class BookManager {
    private static final String fileName = "books.dat";
    private ArrayList<Book> books;

    BookManager() {
        loadBooks();
    }
    void addBook(Book book) {
        if(Collections.binarySearch(books,book,(a,b) -> a.getIsbn().compareTo(b.getIsbn())) >= 0){
            System.out.println("存入失败! ISBN为 "+book.getIsbn()+"的图书已存在");
            return;
        }
        if(book.getNum() < 0) {
            System.out.println("存入失败! 库存数量不能为负");
            return;
        }
        books.add(book);
        books.sort((a,b) -> a.getIsbn().compareTo(b.getIsbn()));
        System.out.println("存入成功！");
    }
    void showBooks() {
        if (books.isEmpty()) {
            System.out.println("暂无图书信息");
            return;
        }
        System.out.print("------所有图书信息------");
        for(Book book : books) {
            System.out.println();
            book.show();
        }
        System.out.println("-----------------------");
    }
    void borrowBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.getNum() > 0) {
                    book.setNum(book.getNum() - 1);
                    System.out.println("借阅成功！《" + book.getName() + "》剩余库存：" + book.getNum());
                } else {
                    System.out.println("借阅失败！《" + book.getName() + "》库存不足");
                }
                return;
            }
        }
        System.out.println("不存在ISBN为 " + isbn + " 的图书!");
    }
    void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.setNum(book.getNum() + 1);
                System.out.println("还书成功!《" + book.getName() + "》剩余库存：" + book.getNum());
                return;
            }
        }
        System.out.println("不存在ISBN为 " + isbn + " 的图书!");
    }

    void savedata() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(books);
            System.out.println("图书信息已保存");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            this.books = (ArrayList<Book>) ois.readObject();
        }catch(FileNotFoundException e){
            this.books = new ArrayList<Book>();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}

public class Test {
    public static void main(String[] args) {
        BookManager manager = new BookManager();
        Scanner cin = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== 图书管理系统 ==========");
            System.out.println("1. 添加图书");
            System.out.println("2. 查看所有图书");
            System.out.println("3. 借书");
            System.out.println("4. 还书");
            System.out.println("0. 退出系统");
            System.out.print("请选择操作：");
            int choice = cin.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("请输入ISBN编号：");
                    String isbn = cin.next();
                    System.out.print("请输入书名：");
                    String name = cin.next();
                    System.out.print("请输入作者：");
                    String author = cin.next();
                    System.out.print("请输入库存数量：");
                    int num = cin.nextInt();
                    manager.addBook(new Book(isbn, name, author, num));
                    break;
                case 2:
                    manager.showBooks();
                    break;
                case 3:
                    System.out.print("请输入要借阅的图书ISBN编号：");
                    manager.borrowBook(cin.next());
                    break;
                case 4:
                    System.out.print("请输入要归还的图书ISBN编号：");
                    manager.returnBook(cin.next());
                    break;
                case 0:
                    manager.savedata();
                    return;
                default:
                    System.out.println("无效选择，请重新输入");
            }
        }
    }
}