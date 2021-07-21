# Java常用类



## **数字相关类**

**Java数字类**

* 整数 Short，Int，Long
* 浮点数 Float，Double
* 大数类 BigInteger（大整数），BigDecimal(大浮点数)
* 随机数类 Random
* 工具类 Math



**BigInteger**

```java
import java.math.BigInteger;

public class BigIntegerTest {
    public BigIntegerTest() {
    }

    public static void main(String[] args) {
        BigInteger b1 = new BigInteger("123456789");
        BigInteger b2 = new BigInteger("987654321");
        System.out.println("b1: " + b1 + ", b2:" + b2);
        System.out.println("加法操作：" + b2.add(b1));
        System.out.println("减法操作：" + b2.subtract(b1));
        System.out.println("乘法操作：" + b2.multiply(b1));
        System.out.println("除法操作：" + b2.divide(b1));
        System.out.println("最大数：" + b2.max(b1));
        System.out.println("最小数：" + b2.min(b1));
        BigInteger[] result = b2.divideAndRemainder(b1);
        System.out.println("商是：" + result[0] + "；余数是：" + result[1]);
        System.out.println("等价性是：" + b1.equals(b2));
        int flag = b1.compareTo(b2);
        if (flag == -1) {
            System.out.println("比较操作: b1<b2");
        } else if (flag == 0) {
            System.out.println("比较操作: b1==b2");
        } else {
            System.out.println("比较操作: b1>b2");
        }

    }
}

```



**BigDecimal**

```java

import java.math.BigDecimal;

public class BigDecimalTest {
    public BigDecimalTest() {
    }

    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal("123456789.987654321");
        BigDecimal b2 = new BigDecimal("987654321.123456789");
        System.out.println("b1: " + b1 + ", b2:" + b2);
        System.out.println("加法操作：" + b2.add(b1));
        System.out.println("减法操作：" + b2.subtract(b1));
        System.out.println("乘法操作：" + b2.multiply(b1));
        System.out.println("除法操作：" + b2.divide(b1, 10, 4));
        System.out.println("最大数：" + b2.max(b1));
        System.out.println("最小数：" + b2.min(b1));
        int flag = b1.compareTo(b2);
        if (flag == -1) {
            System.out.println("比较操作: b1<b2");
        } else if (flag == 0) {
            System.out.println("比较操作: b1==b2");
        } else {
            System.out.println("比较操作: b1>b2");
        }

        System.out.println("===================");
        System.out.println(new BigDecimal("2.3"));
        System.out.println(new BigDecimal(2.3D));
        System.out.println("===================");
        BigDecimal num1 = new BigDecimal("10");
        BigDecimal num2 = new BigDecimal("3");
        BigDecimal num3 = num1.divide(num2, 3, 4);
        System.out.println(num3);
    }
}

```



**Random随机数**

* nextInt() 返回一个随机int
* nextInt(int a) 返回一个[0,a)之间的随机int
* nextDouble（）返回一个[0.0,1.0]之间的double
* ints 方法批量返回随机数数组
* Maths.random()返回一个[0.0,1.0]之间double

```java
import java.util.Random;

public class RandomTest {
    public RandomTest() {
    }

    public static void main(String[] args) {
        Random rd = new Random();
        System.out.println(rd.nextInt());
        System.out.println(rd.nextInt(100));
        System.out.println(rd.nextLong());
        System.out.println(rd.nextDouble());
        System.out.println("=========================");
        System.out.println(Math.round(Math.random() * 10.0D));
        System.out.println("=========================");
        rd.ints();
        int[] arr = rd.ints(10L).toArray();

        int i;
        for(i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }

        System.out.println("=========================");
        arr = rd.ints(5L, 10, 100).toArray();

        for(i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }

        System.out.println("=========================");
        arr = rd.ints(10L).limit(5L).toArray();

        for(i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }

    }
}

```



**java.lang.Math**

* 绝对值函数abs
* 对数函数log
* 比较函数max、min
* 幂函数pow
* 四舍五入函数round
* 向下取整floor
* 向上取整ceil

```java
public class MathTest {
    public MathTest() {
    }

    public static void main(String[] args) {
        System.out.println(Math.abs(-5));
        System.out.println(Math.max(-5, -8));
        System.out.println(Math.pow(-5.0D, 2.0D));
        System.out.println(Math.round(3.5D));
        System.out.println(Math.ceil(3.5D));
        System.out.println(Math.floor(3.5D));
    }
}

```





## 字符串相关类

**String**

* 不可变对象，加减法操作性能较差
* Java中使用频率最高的类

* 常用方法：charAt, concat, contains, endsWith,equals, equalsIgnoreCase, hashCode, indexOf, length, matches, replace, replaceAll, split, startsWith, subString, trim, valueOf

```java
public class StringTest {
    public StringTest() {
    }

    public static void main(String[] args) {
        String a = "123;456;789;123 ";
        System.out.println(a.charAt(0));			//返回第0个元素
        System.out.println(a.indexOf(";"));			//返回第一个；的位置
        System.out.println(a.concat(";000"));		//连接一个新字符串并返回，a不变
        System.out.println(a.contains("000"));		//判断a是否包含000
        System.out.println(a.endsWith("000"));		//判断a是否以000结尾
        System.out.println(a.equals("000"));		//判断是否等于000
        System.out.println(a.equalsIgnoreCase("000"));//判断忽略大小写情况下是否等于000
        System.out.println(a.length());				//返回a长度
        System.out.println(a.trim());				//返回a去除前后空格后的字符串，a不变
        String[] b = a.split(";");					//将a字符串按照;分割成数组

        for(int i = 0; i < b.length; ++i) {
            System.out.println(b[i]);
        }

        System.out.println("===================");
        System.out.println(a.substring(2, 5));	//截取a的第2个到第5个字符 a不变
        System.out.println(a.replace("1", "a"));
        System.out.println(a.replaceAll("1", "a"));//replaceAll第一个参数是正则表达式
        System.out.println("===================");
        String s1 = "12345?6789";
        String s2 = s1.replace("?", "a");
        String s3 = s1.replaceAll("[?]", "a");
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s1.replaceAll("[\\d]", "a"));
    }
}

```



**可变字符串**

–StringBuffer（字符串加减，同步，性能好）

–StringBuilder（字符串加减，不同步，性能更好）

 • StringBuffer/StringBuilder： 方法一样，区别在同步

–append/insert/delete/replace/substring 

–length 字符串实际大小，capacity字符串占用空间大小

–trimToSize(): 去除空隙，将字符串存储压缩到实际大小 

–如有大量append，事先预估大小，再调用相应构造函数

```java
public static void main(String[] args) {
   StringBuffer sb1 = new StringBuffer("123");
   StringBuffer sb2 = sb1;						//  sb1 -> 123 <- sb2    
   sb1.append("12345678901234567890123456789012345678901234567890");
   System.out.println(sb1);
}

```



## 时间相关类

**Date类**
 目前Date类只有两种构造方法，Date()和Date(long date)，后一种方法需要传入一个毫秒数：

```csharp

import java.util.Date;
public class DateDemo {
    public static void main(String[] args){
        Date d1 = new Date();
        long timeMillis = System.currentTimeMillis();
        Date d2 = new Date(timeMillis);
        long value = d2.getTime();
        System.out.println("日期" + d1);
        System.out.println("毫秒数" + value);
    }    
}
```

输出为：

```css
日期Sat Jan 27 13:57:19 CST 2018
毫秒数1517032639754
```

**DataFormat类**
 DateFormat 类是日期／时间格式化子类的抽象类，在java.text包中，一般使用的是SimpleDateFormat类：

```java
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

public class DateFormatDemo {
    public static void main(String[] args) {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss EE");
        DateFormat df2 = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        System.out.println("格式化输出日期1：" + df1.format(date));
        System.out.println("格式化输出日期2：" + df2.format(date));
    }
}
```

输出为：

```css
格式化输出日期1：2018-01-27 02:03:16 星期六
格式化输出日期2：2018年01月27日
```

**Calendar类**
 为了更加便捷的对日期进行操作，Calendar类对YEAR、MONTH、DAY_OF_MONTH、HOUR等日历字段之间的转换提供了一些方法，并为操作日历字段（例如获得下星期的日期）提供了一些方法。

```csharp
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class CalendarDemo {
    Calendar calendar = null;

    @Before
    public void test() {
        calendar = Calendar.getInstance();
    }

    // 基本用法，获取年月日时分秒星期
    @Test
    public void test1() {
        // 获取年
        int year = calendar.get(Calendar.YEAR);

        // 获取月，这里需要需要月份的范围为0~11，因此获取月份的时候需要+1才是当前月份值
        int month = calendar.get(Calendar.MONTH) + 1;

        // 获取日
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // 获取时
        int hour = calendar.get(Calendar.HOUR);
        // int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24小时表示

        // 获取分
        int minute = calendar.get(Calendar.MINUTE);

        // 获取秒
        int second = calendar.get(Calendar.SECOND);

        // 星期，英语国家星期从星期日开始计算
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);

        System.out.println("现在是" + year + "年" + month + "月" + day + "日" + hour
                + "时" + minute + "分" + second + "秒" + "星期" + weekday);
    }

    // 一年后的今天
    @Test
    public void test2() {
        // 同理换成下个月的今天calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.YEAR, 1);

        // 获取年
        int year = calendar.get(Calendar.YEAR);

        // 获取月
        int month = calendar.get(Calendar.MONTH) + 1;

        // 获取日
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("一年后的今天：" + year + "年" + month + "月" + day + "日");
    }

    // 获取任意一个月的最后一天
    @Test
    public void test3() {
        // 假设求6月的最后一天
        int currentMonth = 6;
        // 先求出7月份的第一天，实际中这里6为外部传递进来的currentMonth变量
        // 1
        calendar.set(calendar.get(Calendar.YEAR), currentMonth, 1);

        calendar.add(Calendar.DATE, -1);

        // 获取日
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("6月份的最后一天为" + day + "号");
    }

    // 设置日期
    @Test
    public void test4() {
        calendar.set(Calendar.YEAR, 2000);
        System.out.println("现在是" + calendar.get(Calendar.YEAR) + "年");

        calendar.set(2008, 8, 8);
        // 获取年
        int year = calendar.get(Calendar.YEAR);

        // 获取月
        int month = calendar.get(Calendar.MONTH);

        // 获取日
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("现在是" + year + "年" + month + "月" + day + "日");
    }
}
```

输出为：

```undefined
现在是2016年11月7日11时42分18秒星期2
一年后的今天：2017年11月7日
6月份的最后一天为30号
现在是2000年
现在是2008年8月8日
```

