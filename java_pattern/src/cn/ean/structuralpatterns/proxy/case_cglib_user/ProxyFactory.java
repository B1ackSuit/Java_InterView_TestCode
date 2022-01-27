package cn.ean.structuralpatterns.proxy.case_cglib_user;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * 代理的类不能为final
 * 目标对象的方法如果为final/static，则不会被拦截，即不会执行目标对象额外的业务方法
 *
 * @author ean
 * @FileName ProxyFactory
 * @Date 2022/1/26 4:33 PM
 **/
public class ProxyFactory implements MethodInterceptor {

    /**
     * 维护目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象创建一个代理对象
     */
    public Object getProxyInstance() {
        // 工具类
        Enhancer en = new Enhancer();
        // 设置父类
        en.setSuperclass(target.getClass());
        // 设置回调函数
        en.setCallback(this);
        // 创建子类
        return en.create();

    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("CGLIB模拟事务开始");

        Object returnValue = method.invoke(target, args);

        System.out.println("CGLIB模拟事务结束");

        return returnValue;
    }
}
