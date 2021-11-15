package com.java.learn.jdk.util.function;

import com.java.learn.jdk.util.MyObjects;

import java.util.function.Function;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2021/11/9 22:29
 */
@FunctionalInterface
public interface MyFunction<T,R> {

    /**
     * 将此函数应用于指定参数t中
     * @param t 参数
     * @return 作用后的结果
     */
    R apply(T t);


    default <V> Function<V,R> compose(Function<? super V,? extends T> before) {
        MyObjects.requireNonNull(before);
        return (V v) -> apply( before.apply(v));
    }
}
