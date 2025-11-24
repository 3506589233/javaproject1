import java.io.*;
import java.util.Scanner;

public class Test11{
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String fileName = "data.txt";
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            System.out.println("请输入要写入的内容(单独一行end结束):");
            String s;
            while(!(s = cin.nextLine()).equals("end")){
                fileWriter.write(s);
                fileWriter.write('\n');
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        cin.close();
        System.out.println("从文件读取的内容：");
        try (FileReader fileReader = new FileReader(fileName)) {
            int ch;
            while((ch = fileReader.read()) != -1){
                System.out.print((char)ch);
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}