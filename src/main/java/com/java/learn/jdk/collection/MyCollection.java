package com.java.learn.jdk.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterators;
import java.util.function.Predicate;

/**
 * @Description: 集合接口
 * @Author: chenfuyuan
 * @Date: 2021/10/30 22:32
 */
public interface MyCollection<T> extends MyIterable<T> {

    /**
     * 返回集合中的元素数量,如果数量大于int类型可表示的最大的值，则返回int的最大值( Integer.MAX_VALUE)
     * @return 该集合的元素数量
     */
    int size();

    /**
     * 如果这个集合没有任何元素返回{@code true}
     * @return 如果这个集合没有任何元素返回true
     */
    boolean isEmpty();

    /**
     * 如果这个集合包含指定元素obj,则返回{@code true}
     * 当且仅当该集合包含至少一个元素 且 obj == null ? e == null : obj.equals(e);
     * @param obj 特定元素
     * @return 是否包含指定元素obj
     * @throws NullPointerException 如果该对象为空
     * @throws ClassCastException 类型转换异常
     */
    boolean contains(T obj);

    /**
     * 将该集合转化为数组对象
     * @return 转化后的数组对象
     */
    T[] toArray();


    //<E> E toArray(E[] array);

    /**
     * 添加元素到该集合，如果添加成功返回{@code true}
     * @param e 元素
     * @return 如果添加成功返回true
     */
    boolean add(T e);

    /**
     * 在集合中，删除特定的元素。如果移除成功返回{@code true}
     * @param e 特定元素
     * @return 如果移除成功返回true
     */
    boolean remove(T e);

    /**
     * 针对传入参数 collection中的所有元素，是否包含在当前集合中，如果都包含，返回{@code true}
     * @param collection 匹配集合
     * @return 如果传入集合中所有元素都包含在当前集合中，则返回true
     */
    boolean containsAll(Collection<?> collection);


    /**
     * 将传入集合中所有元素，添加到当前集合中，如果添加成功返回{@code true}
     * @param collection 需要添加的集合
     * @return 添加结果
     */
    boolean addAll(Collection<? extends T> collection);

    /**
     * 将传入集合中所有元素，从当前集合中删除，如果至少一个元素被删除成功则返回{@code true}
     * @param collection 需要删除的集合
     * @return 删除结果
     */
    boolean removeAll(Collection<? extends T> collection);

    /**
     * 传入一个判断函数，对集合进行迭代，如果符合判断条件，则对其进行删除。如果其中有一个元素被删除，则返回{@code true}
     * @param filter 判断函数
     * @return 删除结果。
     */
    default boolean removeIf(Predicate<? super T> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final MyIterator<T> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    /**
     * 当前集合 只保留传入集合的元素，其余元素删除。如果该集合发生改变，则返回{@code true}
     * @param collection 需要保留的集合元素
     * @return 保存结果
     */
    boolean retainAll(Collection<? extends T> collection);


    /**
     * 删除该集合的所有元素。
     */
    void clear();

    /**
     * 判断对象是否相等
     * @param obj 对象
     * @return 判断结果
     */
    boolean equals(Object obj);

    /**
     * 返回计算后的哈希值
     * @return
     */
    int hashCode();

    /**TODO 先进行注释，后面实现
    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 0);
    }

    default Stream<E> stream() {
    return StreamSupport.stream(spliterator(), false);
    }

    default Stream<E> parallelStream() {
    return StreamSupport.stream(spliterator(), true);
    }

    */
}
