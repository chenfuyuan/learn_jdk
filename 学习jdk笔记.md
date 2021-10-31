jdk源码解析

# Collection

## interface Interable

```java
public interface Iterable<T>{
	Iterator<T> iterator();
	
	default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
    
    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
```

## class Objects

### 所在包

``java.util``

### 类定义

``public final class Objects``

### 细节

#### 无法实例化

原因: 构造函数，抛出异常。

```java
private Objects() {
        throw new AssertionError("No java.util.Objects instances for you!");
}
```

#### equals()

``public static boolean equals(Object a,Object b)``

判断两个对象是否相等。



判断依据:

``a==b`` 或 ``a!=null && a.equals(b)``

```java
public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
}
```



#### deepEquals()

``public static boolean deepEquals(Object a, Object b)``

进行深层次的对象相等判断。



判断依据:

1. 首先使用 a == b进行判断相等。

2. 如果a或b，其中一个为null，返回False

3. 调用``Arrays.deepEquals(a,b)``，如果a,b两者为数组，针对每个元素分别进行判断相等。如果a,b其中一个不为数组，调用 ``a.equals(b)``进行判断



#### hashCode()

``public static int hashCode(Object obj)``

获得该对象计算后的哈希值



当对象不为空时，调用对象的``hashCode()``方法。为空时返回0。



该方法作用是为了避免空指针错误。



## class Arrays

 ### 所在包

java.util

### 类定义

``public class Array``

### 细节

#### deepEquals0()

``static boolean deepEquals0(Object e1, Object e2) ``

进行深层次判断相等。



判断依据:

1. 断言校验 ``e1!=null``

2. 针对不同类型数组进行判断

   - 使用``instance``关键字，判断数据类型，强转成对应对象调用不同的判断方法 进行判断

     ```java
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
             } else if (e1 instanceof char[] && e2 instanceof char[]) {
                 //a1,a2都为char数组
                 eq = equals((char[]) e1, (char[]) e2);
             }else if (e1 instanceof short[] && e2 instanceof short[]) {
                 //a1,a2都为short数组
                 eq = equals((short[]) e1, (short[]) e2);
             } else if (e1 instanceof int[] && e2 instanceof int[]) {
                 //a1,a2都为int数组
                 eq = equals((int[]) e1, (int[]) e2);
             } else if (e1 instanceof long[] && e2 instanceof long[]) {
                 //a1,a2都为整数数组
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
     ```

     

   - 当对象类型为``Object``时，需要递归调用``deepEquals0()``方法。因为``Object``类型可能为数组类型。

     ```java
     //============Object类型==================
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
     ```

   - 当对象为基本数据类型(除浮点型)时，可直接使用``==``进行相等判断。

     ```java
     //==================基本数据类型(除浮点型)判断模板===============
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
     ```

   - 当对象为浮点类型时，需要使用``Math.abs(a1[i]-a2[i])>0``作为判断条件，大于0，代表不相等。因为浮点型使用``==``判断，不精确

     ```
     //=============浮点型判断模板===============
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
     ```

3. 如果不为数组，使用``e1.equals(e2)``判断。

#### deepEquals()

``public static boolean deepEquals(Object[] a1, Object[] a2)``

判断两个``Object``数组是否相等



判断依据:

1. 判断``a1==a2`` a1,a2是否为同一引用。

2. 判断``a1`` ``a2``两者其中一个为空，返回``False``

3. 判断``a1``和``a2``的数组长度是否相等

4. 循环判断每个元素是否相等

   ```java
   public static boolean deepEquals(Object[] a1, Object[] a2) {
           if (a1 == a2)
               return true;
           if (a1 == null || a2==null)
               return false;
           int length = a1.length;
           if (a2.length != length)
               return false;
   
           for (int i = 0; i < length; i++) {
               Object e1 = a1[i];
               Object e2 = a2[i];
   
               if (e1 == e2)
                   continue;
               if (e1 == null)
                   return false;
   
               // Figure out whether the two elements are equal
               boolean eq = deepEquals0(e1, e2);
   
               if (!eq)
                   return false;
           }
           return true;
       }
   ```





   

