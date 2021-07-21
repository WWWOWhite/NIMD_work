# 第九章 JAVA异常和异常处理



## 第一节 JAVA异常分类



### 异常：程序不正常的行为或者状态

- int  a = 5/0;
- 数组越界
- 读取文件，结果该文件不村子啊

### 异常处理

- 程序返回到安全状态
- 允许用户保存结果，并以适当方式关闭程序



### Throwable：所有错误的祖先

- Error:系统内部错误或者资源耗尽
- Exception：程序有关的异常
  - RuntimeException：程序自身的错误
    - 5/0,空指针，数组越界...
  - 非RuntimeException：外界相关的错误
    - 打开一个不存在文件
    - 加载一个不存在的类



### Checker和Unchecked

- Unchecked Exception	(编译器不会辅助检查的，需要程序员自己管的)
  - Error子类
  - RuntimeException类
- Checked Exception（编译器会辅助检查）
  - 非RuntimeException类



## 第二节 JAVA异常处理

### 异常处理：

- 允许用户及时保存结果
- 抓住异常，分析异常内容
- 控制程序返回到安全状态



### try-catch-finally

- 异常结构

  - try...catch	(catch可以有多个，下同)
  - try...catch...finally
  - try...finally
  - <font color = 'red'>try必须有，catch和finally至少要有一个</font>

- 执行模块
  - try：正常业务逻辑代码
  - catch：当try发生异常，将执行catch代码。若无异常，绕之。
  - finally：当try或catch执行结束后，必须要执行finally
  
  

  

小异常写在上面，大异常写在下面。

```java
public static void main(String[] args) {
    	try {
            int a = 2;
            System.out.println("a is " + a);
        } catch (Exception var26) {
            var26.printStackTrace();
        } finally {
            System.out.println("Phrase 1 is over");
        }

        int a;
        try {
            a = 5 / 0;
            System.out.println("a is " + a);
        } catch (Exception var24) {
            var24.printStackTrace();
        } finally {
            System.out.println("Phrase 2 is over");
        }

        try {
            a = 5 / 0;
            System.out.println("a is " + a);
        } catch (Exception var22) {
            var22.printStackTrace();
            int var2 = 5 / 0;
        } finally {
            System.out.println("Phrase 3 is over");
        }

    }
```



try结构中，如果有finally块，finally肯定会被执行

try-catch-finally每个模块里面也可能会发生异常，所以也可以在内部继续写一个完整的try结构



### throws

- 方法中存在可能异常的语句，但不方便处理，可以用throws来声明异常
- 调用throws异常（checked exception)的方法，要么处理这些异常，或者再次向外throws，直到main函数为止
- 子类重写的方法所声明的异常不能超出父类规定的范围

```java
 public static void main(String[] args) {
    int result;
    try {
        result = (new Test()).divide(3, 1);
        System.out.println("the 1st result is" + result);
    } catch (ArithmeticException var2) {
        var2.printStackTrace();
    }

    result = (new Test()).divide(3, 0);
    System.out.println("the 2nd result is" + result);
    }

class Test {
    Test() {
    }

    public int divide(int x, int y) throws ArithmeticException {
        int var10000 = x / y;
        return x / y;
    }
}

```



### 第三节 自定义异常

自定义异常，需要继承Exception类或其子类。

- 继承自Exception，就变成Checked Exception，IDE辅助检查

- 继承自RuntimeException，就变成Unchecked Exception，IDE不会辅助检查，程序员自行处理

自定义重点在构造函数

- 调用父类Exception的message构造函数

- 可以自定义自己的成员变量

在程序中采用throw主动抛出异常

Public class MyException extend Exception {
    
    private String returnCode; //异常代码
    private String returnMsg; //异常描述信息
     
    public MyException() {
        super();
    }
     
    public MyException(String returnMsg){
        super();
        this.returnMsg = returnMsg;
    }
     
    public MyException(String returnCode, String returnMsg){
        super();
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }
     
    public String getReturnCode() {
        return returnCode;
    }
    
    public String getReturnMessage() {
        return returnMessage;
    }
    }
    
    throw new MyException(“10001”, “The reason of myException”);


