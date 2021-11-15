package com.java.learn.jdk.collection;

import java.util.Collection;

/**
 * Collection - 列表接口
 * <p>有序集合(也称为序列) 这个序列 用户可以精确地控制每个元素在列表中的插入位置。用户可以通过元素的整数索引(列表中的位置)访问元素，并搜索列表中的元素。
 *
 * <p>与集合不同的是，序列支持添加重复元素。
 *
 * <p>List接口继承Collection接口的方法，但对 add、remove、equals和hashCode方法进行了附加的设定。
 *
 * <p>此接口是Java集合框架的一个成员
 * @author chenfuyuan
 * @date 2021/11/9 21:56
 */
public interface MyList<E> extends MyCollection<E>{

    /**
     * <p>将指定集合c中的所有元素按指定位置index插入到当前列表中。</p>
     * <p>新元素将按照集合c的迭代器返回的顺序添加到当前列表中</p>
     *
     * <p>如果指定集合在操作的时候发生改变，将抛出错误</p>
     *
     * @param index 从指定集合插入列表的第一个元素， 在列表中的索引
     * @param c 包含要添加所有元素的集合
     * @return 如果当前列表发生变化则返回{@code true}
     * @throws UnsupportedOperationException 如果不支持addAll操作
     * @throws ClassCastException 如果指定集合中有某个元素 阻止 被添加到列表中
     * @throws NullPointerException 如果指定集合为空 或 该列表不支持添加null对象，但指定集合包含一个或多个null对象。
     * @throws IllegalArgumentException 如果指定集合某些元素 因为 某些对象 被阻止添加到列表中
     * @throws IndexOutOfBoundsException 如果index的取值不合法。
     */
    boolean addAll(int index, Collection<? extends E> c);
}
