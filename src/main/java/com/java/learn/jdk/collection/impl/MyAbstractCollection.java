package com.java.learn.jdk.collection.impl;

import com.java.learn.jdk.collection.MyCollection;
import com.java.learn.jdk.collection.MyIterator;
import com.java.learn.jdk.util.MyArrays;
import com.java.learn.jdk.util.MyObjects;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * 抽象集合类，定义一些通用的实现
 * @author chenfuyuan
 * @date 2021/11/3 21:38
 */
public abstract class MyAbstractCollection<T> implements MyCollection<T> {

    /**
     * 要分配的数组的最大大小。有些虚拟机在数组中保留一些字头。尝试分配更大的数组可能会导致OutOfMemoryError: Requested array size exceeds VM limit
     */
    public static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    protected MyAbstractCollection() {

    }

    /**
     * 如果size为0时，则返回{@code true}
     * @return 集合是否为空
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 集合是否包含元素obj，如果包含则返回{@code true}
     * @param obj 特定元素
     * @return 集合是否包含元素obj
     */
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

    /**
     * 将集合转换成数组
     * @return 转换后的数组
     */
    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[size()];
        MyIterator<T> iterator = iterator();
        for (int i = 0; i < result.length; i++) {
            if (!iterator.hasNext()) {    //如果已经迭代到了最后一个，生成一个新的数组,长度为i(迭代的次数即数组的真实长度)
                return Arrays.copyOf(result, i);
            }
            result[i] = iterator.next();
        }
        //如果还有下一个元素，则表示还没有迭代完成
        return iterator.hasNext()?finishToArray(result,iterator):result;
    }

    /**
     * 将该集合转化为数组对象，数据类型为 array的数据类型
     * 返回包含该集合中所有元素的数组; 返回数组的运行时类型为指定数组的运行时类型。数组的大小为 Max(当前集合实际大小，array数组大小)
     * @param array 指定数据类型的数组
     * @param <E> 指定数据类型
     * @return 转化后的数组对象
     */
    @Override
    public <E> E[] toArray(E[] array) {
        int size = size();
        //初始化数组，数组大小为 集合大小和指定数组大小的较大值
        E[] result = array.length > size ? array :
                (E[]) Array.newInstance(array.getClass().getComponentType(), size);
        //迭代填充数组
        MyIterator iterator = iterator();
        int forLength = result.length;
        for (int i = 0; i < forLength; i++) {
            //如果迭代次数小于循环大小时，需要确定返回数组的长度
            if (!iterator.hasNext()) {

                if (result == array) {
                    //将余下的位置 置为空
                    for (; i < forLength; i++) {
                        result[i] = null;
                    }
                    return result;
                } else if (array.length < i) {
                    //当i>array.length，则返回数组长度为迭代次数
                    return MyArrays.copyOf(result, i);
                } else {
                    //当 i 介于 array.length 和 size 之间时， 返回的数组长度为array.length
                    if (array.length > i) {
                        System.arraycopy(result,0, array,0 ,i);
                        for (; i < forLength; i++) {
                            array[i] = null;
                        }
                        return array;
                    }
                }
            }
            //填充数据
            result[i] = (E) iterator.next();
        }
        //如果迭代器中还有数据，则扩大数组，将剩下的数据填充到数组中
        return iterator.hasNext() ? finishToArray(result, iterator) : result;
    }

    /**
     * 当迭代器返回比预期更多的元素时，重新分配toArray中使用的数组，并从迭代器中完成填充。
     * @param array 数组充满了先前存储的元素
     * @param iterator 在此集合上进行中的迭代器
     * @param <E> 与result数组匹配的数据类型
     * @return 包含给定数组中的元素以及迭代器返回的其他元素的数组，并修剪为大小
     */
    private<E> E[] finishToArray(E[] array, MyIterator<?> iterator){
        int size = array.length;
        //循环:迭代器进行迭代，迭代至 没有下一个元素
        while (iterator.hasNext()) {
            int cap = array.length;
            //判断数组是否已满
            if (size == cap) {
                //扩充容积
                //将数据复制到更大的数组
                array = MyArrays.copyOf(array,  expansionCap(cap));
            }
            //迭代元素，填充数组
            array[size++] = (E) iterator.next();
        }
        return array;
    }

    /**
     * 扩充容积，接收旧的容积，根据规则 oldCap + (oldCap>>1) + 1。如果得到的新的容积大于 MAX_ARRAY_LENGTH，则返回MAX_ARRAY_LENGTH
     * @param oldCap 旧的容积
     * @return 新的容积
     */
    private static int expansionCap(int oldCap) {
        int newCap = oldCap + (oldCap >> 1) + 1;
        //如果newCap 大于 虚拟机可接收的数组最大大小
        if (newCap > MAX_ARRAY_LENGTH || newCap < 0) {    //扩容后的值可能溢出变为负数
            if (oldCap >= MAX_ARRAY_LENGTH) {
                //超出虚拟机 数组可表示的最大大小，抛出异常
                throw new OutOfMemoryError("Required array size too large");
            }
            return MAX_ARRAY_LENGTH;
        }
        return newCap;
    }

    /**
     *
     * {@inheritDoc}
     *
     * <p>这个方法固定抛出<tt>UnsupportedOperationException</tt>异常
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     * @throws ClassCastException {@inheritDoc}
     */
    public boolean add(T obj) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     *
     * <p>此实现将遍历集合 以 查找指定的元素。如果找到该元素，则使用迭代器的<tt>remove</tt>方法从集合中删除该元素。
     *
     * 如果集合中包含多个指定元素，只会删除第一个匹配到的元素，并返回{@code true}
     *
     * <p>如果该集合的迭代器 没有实现<tt>remove</tt>方法，则应该抛出<tt>UnsupportedOperationException</tt>异常
     *
     * @throws UnsupportedOperationException
     * @throws ClassCastException
     * @throws NullPointerException
     */
    public boolean remove(T element){
        MyIterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            //判断是否包含指定元素
            if (MyObjects.equals(element,iterator.next())) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
