import java.util.*;

abstract class Employee implements Comparable<Employee> {
    String id,name;
    double baseSalary;

    Employee(String id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }
    abstract double calculateSalary();
    void getEmployeeInfo() {
        System.out.println("员工ID: " + id);
        System.out.println("姓名: " + name);
        System.out.println("基础工资: " + baseSalary);
        System.out.println("实际工资: " + String.format("%.2f", calculateSalary()));
    }

    @Override
    public boolean equals(Object obj) {
        Employee employee = (Employee) obj;
        if(this == employee) return true;
        return id.equals(employee.id);
    }
    public int hashCode() {
        return id.hashCode();
    }
    public int compareTo(Employee other) {
        return Double.compare(this.calculateSalary(), other.calculateSalary());
    }
}

class FullTimeEmployee extends Employee {
    private double bonus;

    FullTimeEmployee(String id, String name,double baseSalary,double bonus) {
        super(id,name,baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + bonus;
    }
    public void getEmployeeInfo() {
        System.out.println("员工类型: 全职员工");
        super.getEmployeeInfo();
        System.out.println("绩效奖金: " + bonus);
    }
}

class PartTimeEmployee extends Employee {
    private double hoursalary;
    private double workhours;

    public PartTimeEmployee(String id, String name, double hoursalary, double workhours) {
        super(id, name, 0);
        this.hoursalary = hoursalary;
        this.workhours = workhours;
    }

    @Override
    public double calculateSalary() {
        return hoursalary * workhours;
    }
    public void getEmployeeInfo() {
        System.out.println("员工类型: 兼职员工");
        super.getEmployeeInfo();
        System.out.println("时薪: " + hoursalary);
        System.out.println("工作小时数: " + workhours);
    }
}

class SalesEmployee extends Employee {
    private double salesAmount;

    public SalesEmployee(String id, String name, double baseSalary, double salesAmount) {
        super(id, name, baseSalary);
        this.salesAmount = salesAmount;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + (salesAmount * 0.05);
    }
    public void getEmployeeInfo() {
        System.out.println("员工类型: 销售员");
        super.getEmployeeInfo();
        System.out.println("销售额: " + salesAmount);
    }
}

class Company {
    private List<Employee> employees;

    Company() {
        employees = new ArrayList<>();
    }
    void add(Employee employee) {
        employees.add(employee);
    }
    public boolean removeEmployee(String id) {
        return employees.removeIf((employee) -> employee.id.equals(id));
    }
    public double calculateTotalSalary() {
        double total = 0;
        for (Employee employee : employees) {
            total += employee.calculateSalary();
        }
        return total;
    }

    public void displayAllEmployees() {
        System.out.println("所有员工信息:");
        for (Employee employee : employees) {
            employee.getEmployeeInfo();
        }
    }

    void sort(){
        Collections.sort(employees);
    }
    public void displayEmployees() {
        sort();
        System.out.println("---所有员工信息---");
        for (int i = 0; i < employees.size(); i++) {
            employees.get(i).getEmployeeInfo();
            System.out.println();
        }
    }
    public Employee maxEmployee() {
        if(employees.isEmpty()) return null;
        return Collections.max(employees);
    }
    public Employee minEmployee() {
        if (employees.isEmpty()) return null;
        return Collections.min(employees);
    }
}

public class Test30 {
    public static void main(String[] args){
        Company company = new Company();
        company.add(new FullTimeEmployee("FT01", "张三", 8000, 2000));
        company.add(new PartTimeEmployee("PT01", "李四", 50, 80));
        company.add(new SalesEmployee("SA01", "王五", 6000, 50000));
        company.displayEmployees();
        System.out.println("----------------");
        System.out.println("工资最低员工：");
        company.minEmployee().getEmployeeInfo();
        System.out.println();
        System.out.println("工资最高员工：");
        company.maxEmployee().getEmployeeInfo();
    }
}