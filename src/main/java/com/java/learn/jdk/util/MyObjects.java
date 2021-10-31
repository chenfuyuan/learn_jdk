package com.java.learn.jdk.util;

/**
 * @Description: Objects工具类
 * @Author: chenfuyuan
 * @Date: 2021/11/1 0:18
 */
public final class MyObjects {

    /**
     * 私有化构造函数
     */
    private MyObjects() {
        throw new AssertionError("No java.util.Objects instances for you!");
    }

    /**
     * 判断两个对象是否相等
     * 相等条件:
     * 1. 地址相等
     * 2. 调用 obj1.equals(obj2)返回true
     * 一方为null,返回false
     * @param obj1 对象1
     * @param obj2 对象2
     * @return 两对象是否相等
     */
    public static boolean equals(Object obj1, Object obj2) {
        //对象地址是否相等
        if (obj1 == obj2) {
            return true;
        }

        //对象一方为空，返回false
        if (obj1 == null || obj2 == null) {
            return false;
        }

        //调用对象equals方法，比较是否相等
        return obj1.equals(obj2);
    }

    /**
     * 深层次的比较是否相等，主要用于数组判断
     * 如果对象为数组，会对每个元素进行相等比较
     * @param obj1 对象1
     * @param obj2 对象2
     * @return 两对象是否相等
     */
    public static boolean deepEquals(Object obj1, Object obj2) {
        if (equals(obj1, obj2)) {
            return true;
        }

        return MyArrays.deepEquals0(obj1, obj2);
    }

}
