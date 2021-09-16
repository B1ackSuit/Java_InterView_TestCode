package cn.ean.lambda.impl;

import cn.ean.lambda.Employee;
import cn.ean.lambda.MyPredicate;

/**
 * FileName:FilterEmpByAge
 * Author:ean
 * Date:2021/9/8 9:49 下午
 **/
public class FilterEmpByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        if (employee.getAge() > 18){
            return true;
        }
        return false;
    }
}
