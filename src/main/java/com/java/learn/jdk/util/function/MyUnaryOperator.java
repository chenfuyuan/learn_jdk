package com.java.learn.jdk.util.function;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 函数式接口
 *
 * <p>表示对单个操作数的操作，该操作返回与操作数相同数据类型的结果。</p>
 *
 * <p>使用场景:对于操作数和结果 类型相同的情况下使用</p>
 *
 * @author chenfuyuan
 * @date 2021/11/9 22:21
 */
@FunctionalInterface
public interface MyUnaryOperator<T> extends Function<T,T> {

    /**
     * 返回一个 输入和输出总是相等的 一元运算函数
     * @param <T> 输入和输出对象 的数据类型
     * @return 返回一个 输入和输出总是相等的 一元运算函数
     */
    static <T> MyUnaryOperator<T> identity() {
        return t -> t;
    }
}
