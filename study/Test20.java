import java.io.*;

public class Test{
    public static void main(String[] args){
        int count = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader("src\\data.txt"))){
            String s;
            System.out.println("文件内容：");
            while((s = reader.readLine()) != null){
                System.out.println(s);
                String[] ss = (s.trim()).split("\s+");
                count += ss.length;
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        System.out.println("单词数量：");
        System.out.println(count);
    }
}