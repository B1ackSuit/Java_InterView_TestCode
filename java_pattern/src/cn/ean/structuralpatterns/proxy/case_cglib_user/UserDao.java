package cn.ean.structuralpatterns.proxy.case_cglib_user;

/**
 * @author ean
 * @FileName UserDao
 * @Date 2022/1/26 4:28 PM
 **/
public class UserDao {
    public void save() {
        System.out.println("cglib代理：已经保存数据");
    }


}
