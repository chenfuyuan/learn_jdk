jdk源码解析

# 集合

## interface Collection

集合框架的根接口。

### 所在包

``java.util``

### 定义

``public interface MyCollection<T> extends Iterable<T>``

### 方法

#### size()

返回集合中的元素数量,如果数量大于int类型可表示的最大的值，则返回int的最大值( Integer.MAX_VALUE)

``int size()``

#### isEmpty()

如果这个集合没有任何元素返回``true``

``boolean isEmpty()``

#### contains(T)

如果这个集合包含指定元素obj,则返回`` true``
当且仅当该集合包含至少一个元素 且`` obj == null ? e == null : obj.equals(e);``

``boolean contains(T obj)``

#### toArray()

将该集合转化为数组对象

``T[] toArray()``

#### add(T)

添加元素到该集合，如果添加成功返回``true``

``boolean add(T e)``

#### remove(T)

在集合中，删除特定的元素。如果移除成功返回``true``

``boolean remove(T e)``

#### containsAll(Collection<T>)

针对传入参数 collection中的所有元素，是否包含在当前集合中，如果都包含，返回``true``

``boolean containsAll(Collection<T> collection)``

#### addAll(Collection<T>)

将传入集合中所有元素，添加到当前集合中，如果添加成功返回``true``

``boolean addAll(Collection<? extends T> collection)``

#### removeAll(Collection<T>)

将传入集合中所有元素，从当前集合中删除，如果至少一个元素被删除成功则返回``true``

``boolean removeAll(Collection<? extends T> collection);``

#### removeIf(Predicate<T>)

传入一个判断函数，对集合进行迭代，如果符合判断条件，则对其进行删除。如果其中有一个元素被删除，则返回``true``

``default boolean removeIf(Predicate<? super T> filter)``

#### retainAll(Collection<T>)

当前集合 只保留传入集合的元素，其余元素删除。如果该集合发生改变，则返回``true``

``boolean retainAll(Collection<? extends T> collection);``

#### clear()

清空集合

``void clear()``

#### equals(Object)

判断对象是否相等

``boolean equals(Object obj);``

#### hashCode()

计算哈希值

``int hashCode();``





## interface Iterator

迭代器接口

### 所在包

``java.util``

### 定义

``public interface Iterator<T>``

### 方法

#### hasNext()

``boolean hasNext()``

##### 实现规则

如果还有下一个元素则返回``true``



#### next()

``T next()``

##### 实现规则:

返回下一个元素。如果没有下一个元素，则抛出``NoSuchElementException``



#### remove()

``default void remove()``

##### 实现详情

直接抛出``UnsupportedOperationException("remove")``

实现类可以不实现该方法，但是如果未实现该方法，将报错。

##### 重载细节



#### forEachRemaining()

``default void forEachRemaining(Consumer<? super T> action) ``

##### 实现细节:

1. 判断action函数非空
2. 对剩余元素进行迭代，将action函数作用于剩余元素中。





## abstract class AbstractCollection

实现``Collection``接口的抽象类。

定义一些通用的集合方法

### 所在包

``java.util``

### 定义

``public abstract class AbstractCollection<E> implements Collection<E> ``

### 实现的方法

#### isEmpty()

```java
    /**
     * 如果size为0时，则返回{@code true}
     * @return 集合是否为空
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
```

#### contains(T)

```java
/**
 * 集合是否包含元素obj，如果包含则返回{@code true}
 * @param obj 特定元素
 * @return 集合是否包含元素obj
 */
@Override
public boolean contains(T obj) {
    MyIterator<T> iterator = iterator();
    while (iterator.hasNext()) {
        if (Objects.equals(obj, iterator.next())) {
            return true;
        }
    }
    return false;
}
```

使用迭代器遍历查找

#### toArray()

```java
/**
 * 将集合转换成数组
 * @return 转换后的数组
 */
@Override
public T[] toArray() {
    T[] result = (T[]) new Object[size()];
    MyIterator<T> iterator = iterator();
    for (int i = 0; i < result.length; i++) {
        if (!iterator.hasNext()) {    //如果已经迭代到了最后一个，生成一个新的数组,长度为i(迭代的次数即数组的真实长度)
            return Arrays.copyOf(result, i);
        }
        result[i] = iterator.next();
    }
    //如果还有下一个元素，则表示还没有迭代完成
    return iterator.hasNext()?finishToArray(result,iterator):result;
}

/**
 * 当迭代器返回比预期更多的元素时，重新分配toArray中使用的数组，并从迭代器中完成填充。
 * @param result 数组充满了先前存储的元素
 * @param iterator 在此集合上进行中的迭代器
 * @return 包含给定数组中的元素以及迭代器返回的其他元素的数组，并修剪为大小
 */
private T[] finishToArray(T[] result, MyIterator<T> iterator){
    int size = result.length;
    //循环:迭代器进行迭代，迭代至 没有下一个元素
    while (iterator.hasNext()) {
        int cap = result.length;
        //判断数组是否已满
        if (size == cap) {
            //扩充容积
            //将数据复制到更大的数组
            result = MyArrays.copyOf(result,  expansionCap(cap));
        }
        //迭代元素，填充数组
        result[size++] = iterator.next();
    }
    return result;
}

/**
 * 扩充容积，接收旧的容积，根据规则 oldCap + (oldCap>>1) + 1。如果得到的新的容积大于 MAX_ARRAY_LENGTH，则返回MAX_ARRAY_LENGTH
 * @param oldCap 旧的容积
 * @return 新的容积
 */
private static int expansionCap(int oldCap) {
    int newCap = oldCap + (oldCap >> 1) + 1;
    //如果newCap 大于 虚拟机可接收的数组最大大小
    if (newCap > MAX_ARRAY_LENGTH || newCap < 0) {    //扩容后的值可能溢出变为负数
        if (oldCap >= MAX_ARRAY_LENGTH) {
            //超出虚拟机 数组可表示的最大大小，抛出异常
            throw new OutOfMemoryError("Required array size too large");
        }
        return MAX_ARRAY_LENGTH;
    }
    return newCap;
}
```

根据集合的迭代器，有序生成一个数组。

##### finishToArray(T[],Iterator)

当迭代器返回比预期更多的元素时，重新分配toArray中使用的数组，并从迭代器中完成填充。

##### expansionCap(int)

扩充容积，接收旧的容积，根据规则 oldCap + (oldCap>>1) + 1。如果得到的新的容积大于 MAX_ARRAY_LENGTH，则返回MAX_ARRAY_LENGTH

如果oldCap已经大于MAX_ARRAY_LENGTH了，则抛出异常。

**MAX_ARRAY_LENGTH**: 要分配的数组的最大大小。有些虚拟机在数组中保留一些字头。尝试分配更大的数组可能会导致OutOfMemoryError: Requested array size exceeds VM limit



### 抽象方法





# 基本接口

## interface Interable

### 所在包

``java.lang``

### 实现

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





## interface Consumer

函数式接口，接收一个参数，并对这个参数进行处理。

### 所在包

``java.util.function``

### 定义

```java
@FunctionalInterface
public interface Consumer<T>
```

### 方法

#### accept()

``void accept(T t)``

##### 实现规则

接收一个参数，并对该参数进行处理。



## interface Predicate

函数式接口，接收一个参数，并判断是否符合条件。

### 所在包

``java.util.function``

### 定义

```java
@FunctionalInterface
public interface MyPredicate<T>
```

### 方法

#### test()

``boolean test(T obj)``

接收一个参数，根据条件进行判断，返回``boolean``类型

#### and()

``default MyPredicate<T> and(MyPredicate<? super T> other) ``

根据规则``predicate1 && predicate2``组合成一个新的``Predicate``

##### 默认实现

```java
default MyPredicate<T> and(MyPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }
```

#### or()

根据规则``predicate1 || predicate2``组合成一个新的``Predicate``

``default MyPredicate<T> and(MyPredicate<? super T> other) ``

#### negate()

``default MyPredicate<T> or(MyPredicate<? super T> other) ``

返回一个``Predicate``对象，规则为``!predicate``



#### isEquals()

``static<T> Predicate<T> isEquals(Object targetRef) ``

返回一个``Predicate``对象，规则为:``targetRef.equals(obj)``

如果targetRef为null，返回``Objects.isNull()``





# 工具类

## class Objects

针对Object对象的各种工具方法

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

针对数组对象的各种工具方法

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















