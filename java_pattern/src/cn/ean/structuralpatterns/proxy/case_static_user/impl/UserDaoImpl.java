package cn.ean.structuralpatterns.proxy.case_static_user.impl;

import cn.ean.structuralpatterns.proxy.case_static_user.IUserDao;

/**
 * @author ean
 * @FileName UserDaoImpl
 * @Date 2022/1/26 3:50 PM
 **/
public class UserDaoImpl implements IUserDao {
    /**
     * 代理模式临时方法
     */
    @Override
    public void save() {
        System.out.println("临时方法：已经保存数据");
    }
}
