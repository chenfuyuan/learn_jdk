package com.java.learn.jdk.collection;

import com.java.learn.jdk.util.MyObjects;

import java.util.function.Consumer;

/**
 * 可进行迭代。
 * 返回一个迭代器用于迭代。
 *
 * 实现了Iterable接口才能for-each
 * @author chenfuyuan
 * @date 2021/10/31 21:02
 */
public interface MyIterable<T> extends Iterable<T>{
    /*
     *//**
     * 返回一个迭代器。用于元素的迭代
     * @return 迭代器对象
     *//*
    MyIterator<T> iterator();

    *//**
     * 进行迭代,将action函数作用到每个元素中
     * @param action 函数
     * @throws NullPointerException 如果action为null
     *//*
    default void forEach(Consumer<T> action) {
        MyObjects.requireNonNull(action);
        *//*
        因为时自己写的Iterable,所以无法使用for-each
        for (T t : this) {
            action.accept(t);
        }
        *//*
        //自己写的for-each
        //iterator().forEachRemaining(action);
        MyIterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            action.accept(iterator().next());
        }
    }*/




}
