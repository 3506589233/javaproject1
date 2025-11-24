import java.lang.reflect.*;
import java.lang.reflect.Modifier;

class Person {
    private String name;
    private int age;

    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

public class Test {

public static void getClassInfo(Class<?> clazz) {
    System.out.println("1. 类的基本信息:");
    System.out.println("   类名: " + clazz.getSimpleName());
    System.out.println("   完整类名: " + clazz.getName());
    System.out.println("   包名: " + clazz.getPackage().getName());
    System.out.println("   是否为接口: " + clazz.isInterface());
    System.out.println("   是否为枚举: " + clazz.isEnum());
    System.out.println("   是否为数组: " + clazz.isArray());
    System.out.println("   父类: " + (clazz.getSuperclass() != null ? clazz.getSuperclass().getSimpleName() : "无"));
    System.out.println();
}

public static void getConstructorsInfo(Class<?> clazz) {
    System.out.println("2. 构造方法信息:");
    Constructor<?>[] constructors = clazz.getDeclaredConstructors();
    for (int i = 0; i < constructors.length; i++) {
        Constructor<?> constructor = constructors[i];
        System.out.println("   构造方法" + (i + 1) + ":");
        System.out.println("     名称: " + constructor.getName());
        System.out.println("     修饰符: " + Modifier.toString(constructor.getModifiers()));

        Class<?>[] paramTypes = constructor.getParameterTypes();
        if (paramTypes.length > 0) {
            System.out.print("     参数类型: ");
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getSimpleName());
            }
            System.out.println();
        } else {
            System.out.println("     参数类型: 无");
        }
        System.out.println();
    }
}

public static void getMethodsInfo(Class<?> clazz) {
    System.out.println("3. 方法信息:");
    Method[] methods = clazz.getDeclaredMethods();
    for (int i = 0; i < methods.length; i++) {
        Method method = methods[i];
        System.out.println("   方法" + (i + 1) + ":");
        System.out.println("     名称: " + method.getName());
        System.out.println("     返回类型: " + method.getReturnType().getSimpleName());
        System.out.println("     修饰符: " + Modifier.toString(method.getModifiers()));

        Class<?>[] paramTypes = method.getParameterTypes();
        if (paramTypes.length > 0) {
            System.out.print("     参数类型: ");
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getSimpleName());
            }
            System.out.println();
        } else {
            System.out.println("     参数类型: 无");
        }

        Class<?>[] exceptionTypes = method.getExceptionTypes();
        if (exceptionTypes.length > 0) {
            System.out.print("     异常类型: ");
            for (int j = 0; j < exceptionTypes.length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(exceptionTypes[j].getSimpleName());
            }
            System.out.println();
        }
        System.out.println();
    }
}

public static void getFieldsInfo(Class<?> clazz) {
    System.out.println("4. 字段信息:");
    Field[] fields = clazz.getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
        Field field = fields[i];
        System.out.println("   字段" + (i + 1) + ":");
        System.out.println("     名称: " + field.getName());
        System.out.println("     类型: " + field.getType().getSimpleName());
        System.out.println("     修饰符: " + Modifier.toString(field.getModifiers()));
        System.out.println();
    }
}

public static void main(String[] args) {
    try {
        System.out.println("=== 反射获取类信息示例 ===\n");

        // 测试自定义Person类
        System.out.println("# 分析Person类:");
        Class<?> personClass = Person.class;
        getClassInfo(personClass);
        getConstructorsInfo(personClass);
        getMethodsInfo(personClass);
        getFieldsInfo(personClass);

        System.out.println("\n# 分析String类:");
        Class<?> stringClass = String.class;
        getClassInfo(stringClass);
        getConstructorsInfo(stringClass);
        getMethodsInfo(stringClass);
        getFieldsInfo(stringClass);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}