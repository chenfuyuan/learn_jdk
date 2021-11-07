package com.java.learn.jdk.collection.impl;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.AbstractCollection;
import java.util.function.BinaryOperator;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2021/11/5 22:53
 */
public class MyAbstractCollectionTest {
    @Test
    public void finishToArray() {
        int MAX_VALUE = Integer.MAX_VALUE;
        int MIN_VALUE = Integer.MIN_VALUE;
        print("MAX_VALUE",MAX_VALUE);
        print("0 - MAX_VALUE", 0 - MAX_VALUE);
        print("-1 - MAX_VALUE", -1 - MAX_VALUE);
        print("-1", -1);
        print("-2 - MAX_VALUE", -2 - MAX_VALUE);
        print("-2", -2);
        print("1 - MAX_VALUE", 1 - MAX_VALUE);
        print("1", 1);
        print("MIN_VALUE - MAX_VALUE", MIN_VALUE - MAX_VALUE);
        print("1 + MAX_VALUE", 1 +MAX_VALUE);
        print("2 + MAX_VALUE", 2 +MAX_VALUE);
        System.out.println(0x80000001);

    }

    @Test
    public void test() {
        int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
        int cap = MAX_ARRAY_LENGTH;
        System.out.println(cap + (cap >> 1) + 1);
    }

    private void print(String msg,int data) {
        System.out.println(msg + ", " + toBinary(data) + ", " + (data > 0));
    }

    private String toBinary(int num) {
        String str = Integer.toBinaryString(num);
        if (str.length() == 32) {
            return str;
        }

        int lackZero = 32 - str.length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lackZero; i++) {
            stringBuilder.append("0");
        }
        stringBuilder.append(str);
        return stringBuilder.toString();
    }
}