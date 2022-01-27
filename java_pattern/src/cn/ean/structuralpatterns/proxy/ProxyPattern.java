package cn.ean.structuralpatterns.proxy;

import cn.ean.structuralpatterns.proxy.case_cglib_user.UserDao;
import cn.ean.structuralpatterns.proxy.case_dynamic_user.IUserDao;
import cn.ean.structuralpatterns.proxy.case_dynamic_user.ProxyFactory;
import cn.ean.structuralpatterns.proxy.case_static_user.UserDaoProxy;
import cn.ean.structuralpatterns.proxy.case_static_user.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;

/**
 * @author ean
 * @FileName ProxyPattern
 * @Date 2022/1/26 3:46 PM
 **/
public class ProxyPattern {



    /**
     * case_static_user测试，静态代理
     */
    @Test
    public void testCaseStaticUser() {
        UserDaoImpl target = new UserDaoImpl();

        UserDaoProxy proxy = new UserDaoProxy(target);

        proxy.save();
    }

    /**
     * case_dynamic_user测试，动态代理
     */
    @Test
    public void testCaseDynamicUser() {
        /**
         * 目标对象
         */
        IUserDao target =  new cn.ean.structuralpatterns.proxy.case_dynamic_user.impl.UserDaoImpl();
        System.out.println(target.getClass());

        /**
         * 给目标对象，创建代理对象
         */
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());

        /**
         * 代理对象执行方法
         */
        proxy.save();

    }


    @Test
    public void testCGLIBUser() {
        /**
         * 目标对象
         */
        UserDao target = new UserDao();

        /**
         * 代理对象
         */
        UserDao proxy = (UserDao)
                new cn.ean.structuralpatterns.proxy.case_cglib_user.ProxyFactory(target).getProxyInstance();

        /**
         * 执行对象的代理方法
         */
        proxy.save();
    }

}
