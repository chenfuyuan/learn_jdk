package com.java.learn.jdk.util;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Objects;

/**
 * @Description: MyObjects测试
 * @Author: chenfuyuan
 * @Date: 2021/11/1 0:26
 */
public class MyObjectsTest extends TestCase {

    @Test
    public void testTestEquals() {
        String[] str_array_01 = {"1", "2", "3", "4", "5"};
        String[] str_array_02 = {"1", "2", "3", "4", "5"};
        boolean eq = MyObjects.equals(str_array_01, str_array_02);
        System.out.println("String[](所有元素相等):" + eq);

        String str_01 = "abc";
        String str_02 = "abc";
        String str_03 = "ab";
        eq = MyObjects.equals(str_01, str_02);
        System.out.println("String(相等字符串):" + eq);
        eq = MyObjects.equals(str_01, str_03);
        System.out.println("String(不相等字符串):" + eq);
    }

    @Test
    public void testTestEquals_02() {
        Object obj_01 = null;
        Object obj_02 = null;
        System.out.println(MyObjects.equals(obj_01,obj_02));
    }

    @Test
    public void testDeepEquals() {
        String[] str_array_01 = {"1", "2", "3", "4", "5"};
        String[] str_array_02 = {"1", "2", "3", "4", "5"};
        String[] str_array_03 = {"1", "2", "3", "4"};
        String[] str_array_04 = {"5", "4", "3", "2", "1"};
        boolean eq = MyObjects.deepEquals(str_array_01, str_array_02);
        System.out.println("object[](所有元素相等):" + eq);
        eq = MyObjects.deepEquals(str_array_01, str_array_03);
        System.out.println("object[](长度不同):" + eq);
        eq = MyObjects.deepEquals(str_array_01, str_array_04);
        System.out.println("object[](长度相同,元素不同):" + eq);
    }

    @Test
    public void testHashCode() {
        String str_01 = "abc";
        String str_02 = "abc";
        String str_03 = "ab";
        //当String对象，字符串相同时，返回同一个哈希值
        System.out.println("\"abc\"(str1) = " + MyObjects.hashCode(str_01));
        System.out.println("\"abc\"(str2) = " + MyObjects.hashCode(str_02));

        System.out.println("\"ab\"(str3) = " + MyObjects.hashCode(str_03));
    }
}