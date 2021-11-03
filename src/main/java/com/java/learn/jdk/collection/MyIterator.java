package com.java.learn.jdk.collection;

import com.java.learn.jdk.util.MyObjects;

import java.util.Objects;
import java.util.function.Consumer;

/**
 *
 * @author chenfuyuan
 * @date 2021/11/2 20:28
 */
public interface MyIterator<T> {

    /**
     * 如果还有下一个元素则返回 {@code true}
     * （换句话说, 如果{@link #next} 返回一个元素，而不是抛出异常。则返回{@code true}）
     * @return 如果还有下一个元素则返回{@code true}
     */
    boolean hasNext();

    /**
     * 下一个元素
     * @return 下一个元素
     * @throws java.util.NoSuchElementException 如果这个迭代器没有下一个元素
     */
    T next();

    /**
     *
     * @throws UnsupportedOperationException 如果在这个迭代器中，不支持remove操作
     */
    default void remove(){
        throw new UnsupportedOperationException("remove");
    }

    /**
     * 对剩余的元素进行迭代。会将函数action，作用于每个剩余的元素中。
     * @param action 函数
     * @throws NullPointerException 如果action为null
     */
    default void forEachRemaining(Consumer<? super T> action) {
        MyObjects.requireNonNull(action);
        while (hasNext()) {
            action.accept(next());
        }
    }

}
