package cn.ean.jvm.memory;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * FileName:SOF_DeptAndEmpDemo
 * Author:ean
 * Date:2021/10/11 2:47 下午
 **/
public class SOF_DeptAndEmpDemo {
    public static void main(String[] args){
        Dept dept = new Dept();
        dept.setName("Market");

        Emp zhang = new Emp();
        zhang.setName("zhang");
        zhang.setDept(dept);

        Emp li = new Emp();
        li.setName("li");
        li.setDept(dept);

//        List<Emp> emps = new ArrayList<>();
//        emps.add(zhang);
//        emps.add(li);
//        dept.setEmp(emps);
        dept.setEmp(Arrays.asList(zhang, li));

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(dept));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}

class Emp {

    private String name;

    @JsonIgnore
    private Dept dept;

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public Dept getDept() {
        return dept;
    }
}

class Dept {
     private String name;

     private List<Emp> emps;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmp(List<Emp> emps) {
        this.emps = emps;
    }

    public String getName() {
        return name;
    }

    public List<Emp> getEmps() {
        return emps;
    }
}
