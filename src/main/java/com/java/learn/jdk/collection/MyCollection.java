package com.java.learn.jdk.collection;


import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @Description: 集合接口
 * @Author: chenfuyuan
 * @Date: 2021/10/30 22:32
 */
public interface MyCollection<T> extends Iterable<T> {

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
    boolean contains(Object obj);

    /**
     * 将该集合转化为数组对象
     * @return 转化后的数组对象
     */
    T[] toArray();


    /**
     * 将该集合转化为数组对象，数据类型为 array的数据类型
     * @param array 指定数据类型的数组
     * @param <E> 指定数据类型
     * @return 转化后的数组对象
     */
    <E> E[] toArray(E[] array);

    /**
     * 确保该集合包含指定元素。如果该集合因为调用此方法而发生改变，则返回{@code true}。(如果该集合不允许重复且已经包含指定元素，则返回{@code false})
     *
     * 支持此操作的集合可能会限制哪些元素可以添加到该集合中。特别是，一些集合将拒绝添加空元素，而其他集合将对可能添加的元素类型施加限制。集合类应该在它们的文档中明确指定对添加哪些元素的限制。
     *
     * 如果集合拒绝添加特定元素，而不是因为它已经包含该元素，则必须抛出异常(而不是返回{@code false})。
     *
     * @param e 元素
     * @return 如果添加成功返回true
     */
    boolean add(T e);

    /**
     * <p>从 此集合中移除特定类型的单个实例，如果这个实例存在的话。如果 此集合 包含指定元素 则返回true(相当于，如果这个集合 因调用此方法 而发生改变，则返回true)
     *
     * @param e 特定元素
     * @return 如果移除成功返回true
     */
    boolean remove(T e);

    /**
     * 针对传入参数 collection中的所有元素，是否包含在当前集合中，如果都包含，返回{@code true}
     * @param collection 匹配集合
     * @return 如果传入集合中所有元素都包含在当前集合中，则返回true
     */
    boolean containsAll(MyCollection<?> collection);


    /**
     * 将传入集合中所有元素，添加到当前集合中，如果添加成功返回{@code true}
     * @param collection 需要添加的集合
     * @return 添加结果
     */
    boolean addAll(MyCollection<? extends T> collection);

    /**
     * 将传入集合中所有元素，从当前集合中删除，如果至少一个元素被删除成功则返回{@code true}
     * @param collection 需要删除的集合
     * @return 删除结果
     */
    boolean removeAll(MyCollection<T> collection);

    /**
     * 传入一个判断函数，对集合进行迭代，如果符合判断条件，则对其进行删除。如果其中有一个元素被删除，则返回{@code true}
     * @param filter 判断函数
     * @return 删除结果。
     */
    default boolean removeIf(Predicate<? super T> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<T> each = iterator();
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
    boolean retainAll(MyCollection<?> collection);


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
