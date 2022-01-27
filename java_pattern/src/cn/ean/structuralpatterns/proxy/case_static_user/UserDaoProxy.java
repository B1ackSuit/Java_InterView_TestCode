package cn.ean.structuralpatterns.proxy.case_static_user;

/**
 *
 * 可以做到在不修改目标对象的功能的前提下，对目标功能扩展
 * 缺点：
 * 代理对象需要与目标对象实现一样的接口，所以会导致有很多代理类
 * 一旦接口方法增加，目标对象和代理对象都需要维护
 *
 * @author ean
 * @FileName UserDaoProxy
 * @Date 2022/1/26 3:51 PM
 **/
public class UserDaoProxy implements IUserDao {

    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    /**
     * 代理模式临时方法
     * 调用target.save()调用目标的方法
     */
    @Override
    public void save() {
        System.out.println("模拟开始事务");
        target.save();
        System.out.println("模拟提交事务");
    }
}
