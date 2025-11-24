import java.util.*;

class Person{
    private String name;
    private int age;
    Person(){}
    Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    public int getAge(){
        return age;
    }
    @Override
    public String toString(){
        return ("Name："+name+"\n"+
                "Age："+age);
    }
}

public class Test{
    public static void main(String[] args){
        Person[] person = {new Person("张三",17), new Person("李四", 12),new Person("王五",10)};
        Arrays.sort(person,(a,b) -> a.getAge()-b.getAge());
        for(Person p : person){
            System.out.println(p+"\n");
        }
    }
}