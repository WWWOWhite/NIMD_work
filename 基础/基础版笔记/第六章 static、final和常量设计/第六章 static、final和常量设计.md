

# 第六章 static、final和常量设计



## 第一节 static



**static** : 静态的,java关键字

可作用在：

​	-变量

​	-方法

​	-类

​	-匿名方法



**静态变量：**类共有成员

-static变量只依赖于类存在，不依赖于对象实例存在。

-所有对象实例中的静态变量都共享存储在一个共同的空间（栈）

```java
class Potato()
{
    static price = 5;
}

System.out.println(Potato.price);
```



**static方法**

- 静态方法无需通过对象来引用，而通过类名可以直接引用
- 静态方法中，只能使用静态变量，不能使用非静态变量
- 静态方法禁止引用非静态方法



**static块**

- 只在类第一次被加载时调用
- 执行顺序：static块 > 匿名块 > 构造韩素华



## 第二节 单例模式

限定某一个类在整个程序运行过程中，只能保留一个实例对象在内存空间。

单例模式：保证一个类有且只有一个对象

​	- 采用static来共享对象实例	

​	- 采用private构造函数，防止外界new操作

​	

## 第三节 final

final可用来修饰：

-类

-方法

-字段

* final的类不能被继承
* 父类中如果有final的方法，子类不能改写此方法

* final的变量不能再次赋值

  * 如果是基本型的变量，不能修改其值

  * 如果似乎对象实例，那么不能修改其指针指向（可以修改对象内部的值）

    ```java
    class FinalObject
    {
        int a = 10;
    }
    public class FinalObjectTest{
        public static void main(String[] args)
        {
            final FinalObject obj1 = new FinalObject();
            obj1.a = 20;		//正确✔ 可以修改对象内部的值
            
            obj1 = new FinalObject();   //错误❌ ， final对象不能边跟指针
        }
    }
    ```

    

## 第四节 常量设计和常量池

### 1、变量

常量：一种不会修改的变量

* java没有const关键字
* 不能修改，final
* 不会修改/只读/只要一份，static
* 方便访问 public

常量定义： public static final

```Java
public class Constants{
    public final static double PI_NUMBER = 3.14;
    public static final String DEFAULT_COUNTRY = "China";
    
    public static void main(String[] a)
    {
        System.out.println(Constans.PI_NUMBER);
        System.out.println(Constans.DUFAULT_COUNTRY);
    }
}
```

特殊常量: 接口内定义的变量默认是常量

```Java
public interface SpecialAnimal{
    String color = "yellow";  //default: public static final
    public void move();
}
```



### 2、常量池

**常量池：**相同的值只存储一份，节省内存，共享访问。

基本类型的包装类：

* Boolean,Byte,Short,Integet,Long,Character,Float,Double
* Boolean:true,false
* Byte,Character:\u000--\u007f (0—127)
* Short,Int,Long: -128~127
* <font color='red'>Float,Double</font>:没有缓存（常量池）



Java为常量字符串都建立常量池缓存机制

字符串常量



基本类型的包装类和字符串有两种创建方式

* 常量式赋值创建，放在栈内存
  * Integar a = 10;
  * String b = "abc";
* new对象进行创建，放在堆内存（不会常量化）
  * Integer c = new Integer(10)；
  * String  d = new String("abc");



**Integer类**

* 基本类型和包装类比较，将对包装类自动装箱
* 对象比较，比较地址
* 加法+会自动拆箱



**String类**

* 常量赋值（堆内存）和new创建（栈内存）不是同一个对象
* 编译器只会优化确定的字符串，并缓存



## 第五节 不可变对象和字符串

**不可变对象：**

* 一旦创建，这个对象(状态/值) 不能被更改了
* 其内在的成员变量的值就不能修改了
* 八个基本型别的包装类
* String，BigInteger和BigDecimal等

```java
不可变对象是指值对象不再修改，即abc不会被修改，而指针(句柄/变量名)a的指向可以修改。

String a = new String("abc");		// a -> abc
String b = a;						// a -> abc <- b
System.out.println(b);				abc
a = "def";							//a -> def | b->abc
System.out.println(b);				abc
```

```java
由于不可变，临时变量指向新内存，外部实参的指针不改动

public static void change(String b) {
        b = "def";
}
a = new String("abc");	//a -> abc
change(a);				//对象a调用change（）函数，实参的指针会传给形参的指针,所以b -> abc <- a
    					//函数体内部申请了 “def”，b -> def | a -> abc	
System.out.println(a);	abc
```



**如何创建不可变对象？**

* 所有的属性都是final和private的
* 不提供setter方法
* 类是final 的，或者所有的方法都是final



**不可变对象：**

- 只读，线程安全

- 并发读，提高性能

- 可以重复是哦那个

- 缺点

  - 制造垃圾，浪费空间

  > 对不可变对象进行修改时，会新开辟空间，旧对象则被搁置，直到垃圾回收



**String类**

* 定义
  * String a = "abc"; 	//常量赋值，栈分配内存
  * String b = new String("abc")  //new对象，堆分配内存
* 字符串内容比较：equals方法
* 是否指向同一个对象：指针比较 ==



**Java常量池：**

* 保存在编译期间就已经确定的数据
* 是一块特殊的内存
* 相同的常量字符串只存储一份，节省内存，共享访问



**字符串的加法**

```java
String a = "abc";		a -> abc
a = a+ "def"; //由于String不可修改，效率差    a->abcdef | abc
```

**改进：**

* 使用StringBuffer/StringBuilder类的**append方法**进行修改

  ​	StringBuffer/StringBuilder的对象都是可变对象

  ​	StringBuffer （同步，线程安全，修改快速）

  ​	StringBuilder（不同步，线程不安全，修改更快）

```java
public class StringAppendTest {
    public StringAppendTest() {
    }

    public static void main(String[] args) {
        int n = 1000;

        String a = new String();					//String
        for(int i = 0; i < n; ++i) {				
            a = a + i + ",";
        }
        
        StringBuffer b = new StringBuffer("");		//StringBuffer
        for(int i = 0; i < n; ++i) {
            b.append(i);
            b.append(",");
        }

        StringBuilder c = new StringBuilder("");	//StringBuilder
        for(int i = 0; i < n; ++i) {
            c.append(i);
            c.append(",");
        }

    }
}

当程序涉及大量的字符串加法问题，可以用Stringbuffer和StringBuilder
```



**可变对象和不可变对象传递参数的区别**

```java
public class ArgumentPassing {
    public ArgumentPassing() {
    }

    public static void changeValue(int a) {			
        int a = 10;
    }

    public static void changeValue(String s1) {
        s1 = "def";
    }

    public static void changeValue(StringBuffer s1) {
        s1.append("def");
    }

    public static void main(String[] args) {
        int a = 5;									//基本型别
        String b = "abc";							//不可变对象
        StringBuffer c = new StringBuffer("abc");	//可变对象
        changeValue(a);				//当函数参数是基本类别变量时，是传值的
        							//实参 a=5 形参 a=5 -> 实参 a=5 形参 a =10
      
        changeValue(b);				//传指针
        							//b ->abc <- s1 | b->abc s1->def
       
        changeValue(c);				// c -> abc <- s1
        							// c -> abcdef <- s1
        
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}

```

