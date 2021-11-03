package com.java.learn.jdk.util.function;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 函数式接口，接收一个参数。将accept方法作用于参数中。
 * @author chenfuyuan
 * @date 2021/11/3 18:57
 */
@FunctionalInterface
public interface MyConsumer<T> {

    /**
     * 执行函数
     * @param t 作用对象
     */
    void accept(T t);

}
