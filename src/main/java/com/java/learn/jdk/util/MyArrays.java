package com.java.learn.jdk.util;

/**
 * @Description: 数组工具类
 * @Author: chenfuyuan
 * @Date: 2021/10/31 22:43
 */
public class MyArrays {

    /**
     * 判断 两个对象是否相等
     * 如果对象为数组，则针对每个元素判断是否相等
     * @param e1 对象1
     * @param e2 对象2
     * @return 两对象是否相等
     */
    public static boolean deepEquals0(Object e1, Object e2) {
        assert e1!=null;    //a1不为空，为空报错，避免空指针
        boolean eq;    //记录

        //根据不同类型判断
        if (e1 instanceof Object[] && e2 instanceof Object[]) {
            //e1,a2都为Object数组
            eq = deepEquals((Object[]) e1, (Object[]) e2);
        } else if (e1 instanceof byte[] && e2 instanceof byte[]) {
            //a1,a2都为byte数组
            eq = equals((byte[]) e1, (byte[]) e2);
        } else if (e1 instanceof short[] && e2 instanceof short[]) {
            //a1,a2都为short数组
            eq = equals((short[]) e1, (short[]) e2);
        } else if (e1 instanceof int[] && e2 instanceof int[]) {
            //a1,a2都为int数组
            eq = equals((int[]) e1, (int[]) e2);
        } else if (e1 instanceof long[] && e2 instanceof long[]) {
            //a1,a2都为long数组
            eq = equals((long[]) e1, (long[]) e2);
        } else if (e1 instanceof float[] && e2 instanceof float[]) {
            //a1,a2都为float数组
            eq = equals((float[]) e1, (float[]) e2);
        } else if (e1 instanceof double[] && e2 instanceof double[]) {
            //a1,a2都为double数组
            eq = equals((double[]) e1, (double[]) e2);
        } else if (e1 instanceof boolean[] && e2 instanceof boolean[]) {
            //a1,a2都为boolean数组
            eq = equals((boolean[]) e1, (boolean[]) e2);
        } else {
            eq = e1.equals(e2);
        }

        return eq;
    }

    /**
     * 判断两个boolean数组对象是否相等
     * @param a1 数组对象1
     * @param a2 数组对象2
     * @return 两对象是否相等
     */
    public static boolean equals(boolean[] a1, boolean[] a2) {
        //判断地址是否相等
        if (a1 == a2) {
            return true;
        }

        //如果其中一个对象为空，返回false
        if (a1 == null || a2 == null) {
            return false;
        }

        //判断数组长度是否相等
        int length = a1.length;
        if (a2.length != length) {
            return false;
        }

        //判断其中每个索引的元素是否相等
        for (int i = 0; i < length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断两个byte数组对象是否相等
     * @param a1 数组对象1
     * @param a2 数组对象2
     * @return 两对象是否相等
     */
    public static boolean equals(byte[] a1, byte[] a2) {
        //判断地址是否相等
        if (a1 == a2) {
            return true;
        }

        //如果其中一个对象为空，返回false
        if (a1 == null || a2 == null) {
            return false;
        }

        //判断数组长度是否相等
        int length = a1.length;
        if (a2.length != length) {
            return false;
        }

        //判断其中每个索引的元素是否相等
        for (int i = 0; i < length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断两个short数组对象是否相等
     * @param a1 数组对象1
     * @param a2 数组对象2
     * @return 两对象是否相等
     */
    public static boolean equals(short[] a1, short[] a2) {
        //判断地址是否相等
        if (a1 == a2) {
            return true;
        }

        //如果其中一个对象为空，返回false
        if (a1 == null || a2 == null) {
            return false;
        }

        //判断数组长度是否相等
        int length = a1.length;
        if (a2.length != length) {
            return false;
        }

        //判断其中每个索引的元素是否相等
        for (int i = 0; i < length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两个int数组对象是否相等
     * @param a1 数组对象1
     * @param a2 数组对象2
     * @return 两对象是否相等
     */
    public static boolean equals(int[] a1, int[] a2) {
        //判断地址是否相等
        if (a1 == a2) {
            return true;
        }

        //如果其中一个对象为空，返回false
        if (a1 == null || a2 == null) {
            return false;
        }

        //判断数组长度是否相等
        int length = a1.length;
        if (a2.length != length) {
            return false;
        }

        //判断其中每个索引的元素是否相等
        for (int i = 0; i < length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两个long数组对象是否相等
     * @param a1 数组对象1
     * @param a2 数组对象2
     * @return 两对象是否相等
     */
    public static boolean equals(long[] a1, long[] a2) {
        //判断地址是否相等
        if (a1 == a2) {
            return true;
        }

        //如果其中一个对象为空，返回false
        if (a1 == null || a2 == null) {
            return false;
        }

        //判断数组长度是否相等
        int length = a1.length;
        if (a2.length != length) {
            return false;
        }

        //判断其中每个索引的元素是否相等
        for (int i = 0; i < length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断两个float数组对象是否相等
     * @param a1 数组对象1
     * @param a2 数组对象2
     * @return 两对象是否相等
     */
    public static boolean equals(float[] a1, float[] a2) {
        //判断地址是否相等
        if (a1 == a2) {
            return true;
        }

        //如果其中一个对象为空，返回false
        if (a1 == null || a2 == null) {
            return false;
        }

        //判断数组长度是否相等
        int length = a1.length;
        if (a2.length != length) {
            return false;
        }

        //判断其中每个索引的元素是否相等
        for (int i = 0; i < length; i++) {
            //判断差额绝对值如果大于0，则返回false
            if (Math.abs(a1[i] - a2[i])>0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两个double数组对象是否相等
     * @param a1 数组对象1
     * @param a2 数组对象2
     * @return 两对象是否相等
     */
    public static boolean equals(double[] a1, double[] a2) {
        //判断地址是否相等
        if (a1 == a2) {
            return true;
        }

        //如果其中一个对象为空，返回false
        if (a1 == null || a2 == null) {
            return false;
        }

        //判断数组长度是否相等
        int length = a1.length;
        if (a2.length != length) {
            return false;
        }

        //判断其中每个索引的元素是否相等
        for (int i = 0; i < length; i++) {
            //判断差额绝对值如果大于0，则返回false
            if (Math.abs(a1[i] - a2[i])>0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 深层判断，两个Objects是否相等
     * @param a1 数组1
     * @param a2 数组2
     * @return 两对象是否相等
     */
    private static boolean deepEquals(Object[] a1, Object[] a2) {
        //地址是否相等
        if (a1 == a2) {
            return true;
        }

        //其中一个为空，返回false
        if (a1 == null || a2 == null) {
            return false;
        }

        //判断数组长度是否相等
        if (a1.length != a2.length) {
            return false;
        }

        //针对每个元素判断相等
        for (int i = 0,length = a1.length; i < length; i++) {
            Object e1 = a1[i];
            Object e2 = a2[i];
            if (e1 == e2) {
                return true;
            }
            if (e1 == null || e2 == null) {
                return false;
            }
            //因为元素 可能为数组对象，所以调用deepEquals0进行判断
            boolean eq = deepEquals0(e1, e2);
            if (!eq) {
                return false;
            }
        }
        return true;
    }
}
