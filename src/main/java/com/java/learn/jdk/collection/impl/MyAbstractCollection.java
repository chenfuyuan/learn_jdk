package com.java.learn.jdk.collection.impl;

import com.java.learn.jdk.collection.MyCollection;
import com.java.learn.jdk.collection.MyIterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * 抽象集合类，定义一些通用的实现
 * @author chenfuyuan
 * @date 2021/11/3 21:38
 */
public abstract class MyAbstractCollection<T> implements MyCollection<T> {

    protected MyAbstractCollection() {

    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(T obj) {
        MyIterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            if (Objects.equals(obj, iterator.next())) {
                return true;
            }
        }
        return false;
    }


}
