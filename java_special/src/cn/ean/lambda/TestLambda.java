package cn.ean.lambda;

import cn.ean.lambda.impl.FilterEmpByAge;
import org.junit.jupiter.api.Test;


import java.util.*;

/**
 * FileName:TestLambda
 * Author:ean
 * Date:2021/9/8 9:21 下午
 **/
public class TestLambda {

    @Test
   public void test(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);

    }

    //lambda
    @Test
    public void test2(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }


    List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 99),
                new Employee("张四", 19, 101),
                new Employee("张五", 18, 105),
                new Employee("张六", 19, 104),
                new Employee("张七", 19, 93)
    );


    // 模拟需求：获取员工大于18岁的信息
    public List<Employee> filterEmployees(List<Employee> emps){
        List<Employee> list = new ArrayList<>();
        for (Employee employee : emps){
            if (employee.getAge() > 18){
                list.add(employee);
            }
        }

        return list;
    }

    @Test
    public void test3(){
        List<Employee> list = filterEmployees(employees);
        for (Employee employee : list){
            System.out.println(employee);
        }
    }

    // 新需求：工资大于100的
    public List<Employee> filterEmployee2(List<Employee> emps){
        List<Employee> list = new ArrayList<>();
        for (Employee employee : emps){
            if (employee.getSalary() > 100){
                list.add(employee);
            }
        }

        return list;
    }

    // 优化策略模式：
    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> emp) {
        List<Employee> employees = new ArrayList<>();
        for (Employee e :
                list) {
            if (emp.test(e)) {
                employees.add(e);
            }
        }
        return employees;
    }

    //匿名内部类
    @Test
    public void test5(){
        List<Employee> emp = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return false;
            }
        });

    }

    //lambda
    @Test
    public void test6(){
        List<Employee> employees = filterEmployee(this.employees,
                (e) -> e.getSalary() <= 100);
        employees.forEach(System.out::println);

    }

    // Stream API（无需策略方式）
    @Test
    public void test7(){

        employees.stream()
                 .filter((e) -> e.getSalary() >= 100)
                 .limit(2)
                 .forEach(System.out::println);
    }

    @Test
    public void test4(){
        List<Employee> list = filterEmployee(employees, new FilterEmpByAge());

        for (Employee e :
                list) {
            System.out.println(e);
        }
    }
}
