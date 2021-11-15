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

    /**
     * <p>返回一个组合的Consumer函数，它依次执行此操作和after操作。</p>
     * <p>如果执行任何一个操作都会抛出异常，则将其转发给组合操作的调用者。如果执行此操作引发异常，则不会执行after操作。</p>
     * @param after 当前操作后 需要执行的操作
     * @return 一个组合的Consumer函数，它依次执行此操作和after操作
     */
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }
}
