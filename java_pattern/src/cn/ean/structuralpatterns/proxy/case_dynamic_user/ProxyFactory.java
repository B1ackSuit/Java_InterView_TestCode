package cn.ean.structuralpatterns.proxy.case_dynamic_user;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建动态代理对象
 * 动态代理不需要实现接口，但是需要指定接口类型
 *
 *
 *
 * @author ean
 * @FileName ProxyFactory
 * @Date 2022/1/26 4:05 PM
 **/
public class ProxyFactory {

    /**
     * 维护一个目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象生成代理对象
     * @return
     */
    public Object getProxyInstance() {


        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("动态代理：模拟事务开始");

                        /**
                         * 执行目标方法
                         */
                        Object returnValue = method.invoke(target, args);

                        System.out.println("动态代理：模拟事务提交");
                        return returnValue;
                    }
                }
        );
    }
}
