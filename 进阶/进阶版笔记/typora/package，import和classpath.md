

# 第七章 package，import和classpath



## 第一节 package 和import



### ackage规则：

• 包名 package name尽量唯一
• 域名是唯一的，因此常用域名做包名
• 域名逆序：cn.edu.ecnu，范围通常从大到小
• 类的完整名字：包名+类名，cn.edu.ecnu.PackageExample
• 包名：和目录层次一样, cn\edu\ecnu\PackageExample.java
• 但是包具体放在什么位置不重要，编译和运行的时候再指定。



• 在PackageExampleTest.java中，可用import关键字来引入PackageExample类

 <img src="C:\Users\bhy\AppData\Roaming\Typora\typora-user-images\image-20210719140228712.png" alt="image-20210719140228712" style="zoom:67%;" />



### import规则：

–import必须全部放在package之后，类定义之前。
–多个import的顺序无关。
–可以用*来引入一个目录下的所有类，比如import java.lang.*; 
	此意思是引入java.lang下面所有的类文件，当不包括java.lang下面所有的子目录文件，即并不包括		      

​    java.lang.reflect.*; 换句话说，不能递归包含各个目录下的文件。
–import 尽量精确，不推荐用*，以免新增的同名程序会使得老程序报错。



程序中需要引用多个同名的类，那么只能import其中一个，并可用类名调用，其他的类必须用全称（包名+类名）调用。





## 第二节 jar文件的导入导出

**jar文件**

一种扩展名为jar的文件，是Java所特有的一种文件格式，用于可执行程序文件的传播。
 jar文件实际上是一组class文件的压缩包



**jar文件优势**
–jar文件可以包括多个class，比多层目录更加简洁实用
–jar文件经过压缩，只有一个文件，在网络下载和传播方面，更具有优势
–jar文件只包括class，而没有包含java文件，在保护源文件知识版权方面，能够可以起到更好的作用
–将多个class文件压缩成jar文件（只有一个文件），可以规定给一个版本号，更容易进行版本控制



### 如何导出jar包

File -> export (java/JAR file) ->选择导出内容和导出路径->确定

### 如何导入jar包

项目右键 -> properties ->Java Build Path / Libraries -> Add External JARS -> 添加jar包  -> apply 



## 第三节 命令行调用

• 包名：和目录层次一样
–cn.com.test.Man.java 必须放在cn\com\test目录下
• 类的完整名字：包名+类名
–cn.com.test.Man
• 但是包具体放在什么位置不重要，编译和运行的时候通过classpath再指定。



• java -classpath .;c:\temp cn.com.test.Man
• 第一部分：java，执行命令，是java.exe的简写。
• 第二部分：-classpath 固定格式参数，可以简写成-cp. 
• 第三部分：是一个(Windows分号,Linux/Mac冒号连接起来的)字符串。按分隔符隔开，得到一个个子路径。当运行cn.com.test.Man类的过程中，如果需要用到其他的类，就会分裂第三部分的字符串，得到多个子路径，然后依次在每个路径下，再去寻找相应类（全称，包名以点隔开对应到目录）。
• 第四部分：主执行类的全称（含包名）

 <img src="C:\Users\bhy\AppData\Roaming\Typora\typora-user-images\image-20210719143556016.png" alt="image-20210719143556016" style="zoom:67%;" />



• 编译和运行规则
–编译一个类，需要java文件的全路径，包括扩展名。
–运行一个类，需写类名全称（非文件路径），无须写扩展名。
–编译类的时候，需要给出这个类所依赖的类（包括依赖的类再次依赖的所有其他类）的所在路径。
–运行类的时候，需要给出这个类，以及被依赖类的路径总和。
–classpath参数也可以包含jar包。如果路径内有空格，请将classpath参数整体加双引号。
–java -classpath “.;c:\test.jar;c:\temp;c:\a bc” cn.com.test.Man



## 访问权限

• Java访问权限有四种
–private: 私有的，只能本类访问
–default(通常忽略不写)：同一个包内访问
–protected：同一个包，子类均可以访问
–public：公开的，所有类都可以访问
• 使用范围
–四种都可以用来修饰成员变量、成员方法、构造函数
–default和public可以修饰类

 <img src="C:\Users\bhy\AppData\Roaming\Typora\typora-user-images\image-20210719144059843.png" alt="image-20210719144059843" style="zoom:67%;" />

> 不同包的子类： 包A有类A  ；包B有类B 继承于包A的类A  那么就称B为不同包的子类
>
> 不同包的非子类： 