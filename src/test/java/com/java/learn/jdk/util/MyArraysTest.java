package com.java.learn.jdk.util;


import org.junit.Test;

import java.util.Arrays;

/**
 * @Description: 测试Arrays工具类
 * @Author: chenfuyuan
 * @Date: 2021/10/31 23:35
 */
public class MyArraysTest {

    @Test
    public void deepEquals0() {
        //测试Object数组
        String[] str_array_01 = {"1", "2", "3", "4", "5"};
        String[] str_array_02 = {"1", "2", "3", "4", "5"};
        String[] str_array_03 = {"1", "2", "3", "4"};
        String[] str_array_04 = {"5", "4", "3", "2", "1"};
        boolean eq = MyArrays.deepEquals0(str_array_01, str_array_02);
        System.out.println("object[](所有元素相等):" + eq);
        eq = MyArrays.deepEquals0(str_array_01, str_array_03);
        System.out.println("object[](长度不同):" + eq);
        eq = MyArrays.deepEquals0(str_array_01, str_array_04);
        System.out.println("object[](长度相同,元素不同):" + eq);

        int[] int_array_01 = {1, 2, 3, 4, 5};
        int[] int_array_02 = {1, 2, 3, 4, 5};
        int[] int_array_03 = {1, 2, 3, 4};
        int[] int_array_04 = {5, 4, 3, 2, 1};
        eq = MyArrays.deepEquals0(int_array_01, int_array_02);
        System.out.println("int[](所有元素相同):"+eq);
        eq = MyArrays.deepEquals0(int_array_01, int_array_03);
        System.out.println("int[](长度不同):"+eq);
        eq = MyArrays.deepEquals0(int_array_01, int_array_04);
        System.out.println("int[](长度相同,元素不同):"+eq);

        long[] long_array_01 = {1L, 2L, 3L, 4L, 5L};
        short[] short_array_01 = {1, 2, 3, 4, 5};
        eq = MyArrays.deepEquals0(int_array_01, long_array_01);
        System.out.println("int[] and long[](整数数组，类型不同，元素相同):"+eq);

    }

    @Test
    public void copyArray() {
        String[] array = {"1", "2", "3", "4"};
        String[] copyArrays = MyArrays.copyOf(array, 3);
        System.out.println("originArray:" + MyArrays.toString(array));
        System.out.println("copyArray:" + MyArrays.toString(copyArrays));
    }
}