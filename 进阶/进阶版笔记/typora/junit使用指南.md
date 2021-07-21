# junit使用指南



## 1. 再MAVEN PROJECT的pom中文件中添加依赖文件

MAVEN依赖文件

```xml
  <!-- https://mvnrepository.com/artifact/junit/junit -->
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```



## 2.代码格式

```java
import static org.junit.Assert.*; //导入Assert类的所有静态方法，自JDK1.5引入

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

	@Test
	public void test() {
		assertEquals(true, new Triangle().judgeEdges(1,2,3));
		//Assert.assertEquals(false, new Triangle().judgeEdges(1,2,3));
	}

}

```


## 3. 运行方式

右击java文件Junit Test 只能一次运行一个文件

右击pom文件Maven Test可以运行所有文件



## 4.运行结果

### junit运行结果

<img src="C:\Users\bhy\AppData\Roaming\Typora\typora-user-images\image-20210717160308554.png" alt="image-20210717160308554" style="zoom:80%;" />

### Maven Test运行结果

![image-20210717160425595](C:\Users\bhy\AppData\Roaming\Typora\typora-user-images\image-20210717160425595.png)