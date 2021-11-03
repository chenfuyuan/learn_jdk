package com.java.learn.jdk.util.function;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * 函数式接口:
 * 判断 传入的参数 是否符合条件
 * @author chenfuyuan
 * @date 2021/11/3 19:37
 */
@FunctionalInterface
public interface MyPredicate<T>{


    /**
     * 对obj进行条件判断，如果符合返回{@code true}
     * @param obj 判断对象
     * @return 对obj进行条件判断，如果符合返回{@code true}
     */
    boolean test(T obj);


    /**
     * 根据规则‘predicate1 && predicate2’组合成一个新的Predicate
     * @param other 用于结合的另一个predicate对象
     * @return 结合后的Predicate对象
     */
    default MyPredicate<T> and(MyPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    /**
     * 根据规则‘predicate1 || predicate2’组合成一个新的Predicate
     * @param other 用于结合的另一个predicate对象
     * @return 结合后的Predicate对象
     */
    default MyPredicate<T> or(MyPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    /**
     * 根据规则:'!predicate'，生成一个新的Predicate对象
     * @return 新的Predicate对象
     */
    default MyPredicate<T> negate() {
        return (t) -> !test(t);
    }


    /**
     * 返回一个'Predicate'对象，规则为:'targetRef.equals(obj)'
     * 如果targetRef为null，返回'Objects.isNull()'
     * @param targetRef 对象引用
     * @param <T> 特定对象类型
     * @return Predicate对象
     */
    static<T> MyPredicate<T> isEquals(Object targetRef) {
        return (targetRef == null) ? Objects::isNull : object -> targetRef.equals(object);
    }
}
