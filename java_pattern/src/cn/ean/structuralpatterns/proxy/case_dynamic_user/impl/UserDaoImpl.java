package cn.ean.structuralpatterns.proxy.case_dynamic_user.impl;


import cn.ean.structuralpatterns.proxy.case_dynamic_user.IUserDao;

/**
 * @author ean
 * @FileName UserDaoImpl
 * @Date 2022/1/26 4:04 PM
 **/
public class UserDaoImpl implements IUserDao {
    /**
     * 代理模式临时方法
     */
    @Override
    public void save() {
        System.out.println("动态代理的实现方法：已经保存数据");
    }
}
