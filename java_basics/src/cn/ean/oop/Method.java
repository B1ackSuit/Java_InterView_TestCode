package cn.ean.oop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ean
 * @FileName Method
 * @Date 2022/6/29 21:37
 **/
public class Method {






    @Test
    public void testMethod() {

        String x = "100";
        String y = "100";
        System.out.println(x == y);
        System.out.println (x.equals(y));

    }


    public void testA(User user) {
        user.setUsername("bb");

    }

    public void testB(int a) {
        a = 10;
    }

    public void testC(User user) {
        User user2 = new User();

        user2.setUsername("abc");
        user = user2;

    }
}


class User{

    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}


class User2 extends User {

}
