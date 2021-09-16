package cn.ean.lambda;

/**
 * FileName:MyPredicate
 * Author:ean
 * Date:2021/9/8 9:48 下午
 **/
@FunctionalInterface
public interface MyPredicate<T> {
    public boolean test(T t);
}
