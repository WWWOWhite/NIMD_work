# 第十一章 JAVA文件读写



## 第一节 文件系统及Java文件基本操作



**java.io.File类**

- File类与OS无关，但会受到OS的权限限制 
- 常用方法 
  - createNewFile,delete,exists, getAbsolutePath, getName, getParent,getPath, isDirectory, isFile, length, listFiles, mkdir, mkdirs 
- 注意：File不涉及到具体的文件内容，只涉及属性

```java
/*
Java用File对象作为文件或者目录的句柄（代理人）。操作File对象，就是操作文件或目录。

exists 判断file对象是否存在
mkdir 创建一级目录
mkdirs 创建多级目录
isDirectory 是否为目录
ifFile 是否为文件
createNewFile 创建新文件

*/

//创建目录
File d = new File("c:/temp");		
if (!d.exists()) {			
    d.mkdirs();
}

System.out.println("Is d directory? " + d.isDirectory());
File f = new File("C:/temp/abc.txt");
if (!f.exists()) {
    try {
        f.createNewFile();		//创建abc.txt
    } catch (IOException var8) {
        var8.printStackTrace();
    }
}

/*
isDirectory	 是否为目录
ifFile	是否是文件
getName	获取文件名
getParent	获取上一层目录路径
getPath		获取这个文件的全路径
length		获取文件的大小
lastModified	返回文件最后一次修改时间
*/
System.out.println("Is f file? " + f.isFile());
System.out.println("Name: " + f.getName());
System.out.println("Parent: " + f.getParent());
System.out.println("Path: " + f.getPath());
System.out.println("Size: " + f.length() + " bytes");
System.out.println("Last modified time: " + f.lastModified() + "ms");
System.out.println("list files in d directory");

//listFiles 列出当前目录的所有子文件，但不包括子目录下的文件，即不会递归显示
//delete	删除文件或者目录
File[] fs = d.listFiles();
for(file f1:fs)
{
    System.out.println(f1.getPath());
}

File[] var7 = fs;
int var6 = fs.length;

for(int var5 = 0; var5 < var6; ++var5) {
    File f1 = var7[var5];
    System.out.println(f1.getPath());
}
```





**Java NIO**

- Java 7提出的NIO包，提出新的文件系统类 
- Path, Files, DirectoryStream, FileVisitor,FileSystem 
- 是java.io.File的有益补充 
  - 文件复制和移动 
  - 文件相对路径 
  - 递归遍历目录 
  - 递归删除目录

```java
//获得path方法一
Path path = FileSystems.getDefault().getPath("c:/temp", "abc.txt"); //目录名和文件名隔开
System.out.println(path.getNameCount());

//获得path方法二 用File的toPath()方法获得Path对象
File file = new File("c:/temp/abc.txt");
Path pathOther = file.toPath();
//0，说明这两个path是相等的
System.out.println(path.compareTo(pathOther));

//获得path方法三 
Path path3 = Paths.get("c:/temp", "abc.txt");
System.out.println(path3.toString());


Path path4 = Paths.get("c:/temp");
System.out.println("path4: " + path4.resolve("abc.txt"));

if (Files.isReadable(path)) {
    System.out.println("it is readable");
} else {
    System.out.println("it is not readable");
}
```



**moveFile()**

```java
/*
将c:\temp\abc.txt文件，采用Files.move方法，移动到c:\temp\test下，并更名为def.txt
最后的路径是c:\temp\test\def.txt
*/

Path from = Paths.get("c:/temp", "abc.txt");
//移动C:/temp/abc.txt到C:/temp/test/def.tt，如果目标文件已存在，就替换
Path to = from.getParent().resolve("test/def.txt");

try {
   //文件的大小
    System.out.println(Files.size(from));
   //调用文件移动方法 如果目标母舰已经存在，就替换
    Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
} catch (IOException var3) {
    System.err.println("移动文件错误" + var3.getMessage());
}
```

**fileAttributes()**

```java
/*
File.isDirectory	判断是否是目录
BasicFileAttributes.isDirectory		判断是否是目录
BasicFileAttributes.creationTime	返回创建时间
BasicFileAttributes.lastModifiedTime	返回最后修改时间
*/

Path path = Paths.get("c:/temp");
System.out.println(Files.isDirectory(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS}));

try {
    //获得文件的基本属性
    BasicFileAttributes attributes =Files.readAttributes(path,BasicFileAttributes.class);
    System.out.println(attributes.isDirectory());			             
    System.out.println((newDate(attributes.lastModifiedTime().toMillis())).toLocaleString());
} catch (IOException var2) {
    var2.printStackTrace();
}
```

**createDirectory()**

```java
/*
Files.notExists 测试此路径所在的文件是否不存在
Files.createDirectories	首先创建所有不存在的目录来创建目录
Files.createFile	创建一个新的和空的文件
Files.newDirectoryStream	打开一个目录，返回一个DirectoryStream以遍历目录中的条目。
*/
Path path = Paths.get("c:/temp/test");

try {
    if (Files.notExists(path, new LinkOption[0])) {
        Files.createDirectories(path);
        System.out.println("create dir");
    } else {
        System.out.println("dir exists");
    }

    Path path2 = path.resolve("A.java");
    Path path3 = path.resolve("B.java");
    Path path4 = path.resolve("C.txt");
    Path path5 = path.resolve("D.jpg");
    Files.createFile(path2);
    Files.createFile(path3);
    Files.createFile(path4);
    Files.createFile(path5);
    
    //遍历方式1
    DirectoryStream<Path> paths = Files.newDirectoryStream(path);
    Iterator var7 = paths.iterator();
    while(var7.hasNext()) {
        Path p = (Path)var7.next();
        System.out.println(p.getFileName());
    }
	
    //遍历方式2
    for(Path p : paths)
    {
        System.out.println(p.getFileName());
    }
    System.out.println();
    
    //创建一个带有过滤器，过滤文件名以java，txt结尾的文件,即保留java,txt为结尾的文件
    DirectoryStream<Path> pathsFilter = Files.newDirectoryStream(path, "*.{java,txt}");
    for(Path p : pathsFilter)
    {
        System.out.println(p.getFileName());
    }
    
    Iterator var8 = pathsFilter.iterator();
    while(var8.hasNext()) {
        Path p = (Path)var8.next();
        System.out.println(p.getFileName());
    }
    
} catch (IOException var9) {
    var9.printStackTrace();
}

```



**递归遍历一个文件夹的所有jpg文件**

```java
String ext = "*.jpg";
Path fileTree = Paths.get("C:/temp/");
Search walk = new Search(ext);
EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
Files.walkFileTree(fileTree, opts, 2147483647, walk);
```



## 第二节 IO包概述

**java.io包中** 

- 节点类：直接对文件进行读写 
- 包装类 
  - 转化类：字节/字符/数据类型的转化类 
  - 装饰类：装饰节点类



**概念区分：**

- 字节：byte, 8bit, 最基础的存储单位 
-  字符：a, 10000, 我，の 
-  数据类型: 3， 5.25，abcdef 
- 文件是以字节保存，因此程序将变量保存到文件需要转化



节点类

**节点类: 直接操作文件类**

- InputStream<font color="red">(数据从文件读取到Java里）</font>, OutputStream<font color="red">(数据从java输出到文件里）</font>（字节） 		
  - FileInputStream,FileOutputStream 
- Reader,Writer(字符) 
  - FileReader, FileWriter

包装类

**转换类：字符到字节之间的转化** 

- InputStreamReader：文件读取时字节，转化为Java能理解的字符 
- OutputStreamWriter：Java将字符转化为字节输入到文件中



**装饰类：装饰节点类** 

- DataInputStream,DataOutputStream: 封装数据流 
- BufferedInputStream,BufferOutputStream：缓存字节流 
- BufferedReader, BufferedWriter：缓存字符流



## 第三节 文本文件读写



- 文件类型 
  - 一般文本文件(若干行字符构成的文件)，如txt等 
  - 一般二进制文件，如数据文件dat 
  - 带特殊格式的文本文件，如xml等 
  - 带特殊格式二进制文件，如doc,ppt等
-  文件是数据的一个容器(口袋) 
- 文件可以存放大量的数据 
- 文件很大，注定Java只能以流形式依次处理



- 从Java角度理解 
  - 输出：数据从Java到文件中，写操作 
  - 输入：数据从文件到Java中，读操作 
- 文本文件读写 
  - 输出文本字符到文件中 
  - 从文件中读取文本字符串



**写文件** 

- 先创建文件，写入数据，关闭文件 
- FileOutputStream, OutputStreamWriter, BufferedWriter 
- BufferWriter 
  -  write 
  - newLine 
- try-resource 语句，自动关闭资源 
- 关闭最外层的数据流，将会把其上所有的数据流关闭

```java
/*

FileOutputStream	往文件写字节
OutputStreamWriter	字节和字符转换
BufferWriter		写缓冲类，加速写操作

三者构建关系
BufferWriter(OutputStreamWriter(FileOutputStream))

write("contents")
newLine();
*/
方法一

public static void writeFile1() {
    FileOutputStream fos = null;	//节点类，负责写字符
    OutputStreamWriter osw = null;	//转换类，负责字符到字节转化
    BufferedWriter bw = null;		//装饰类，负责写字符到缓冲区

    try {
        fos = new FileOutputStream("c:/temp/abc.txt");
        osw = new OutputStreamWriter(fos, "UTF-8");
        bw = new BufferedWriter(osw);
        bw.write("我们是");
        bw.newLine();
        bw.write("Ecnuers.^^");
        bw.newLine();
    } catch (Exception var12) {
        var12.printStackTrace();
    } finally {
        try {
            bw.close();			//关闭外围的类
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

}

/*
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("")));
*/
public static void writeFile2() {
    try {
        Throwable var0 = null;
        Object var1 = null;

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("c:/temp/abc.txt")));

            try {
                bw.write("我们是");
                bw.newLine();
                bw.write("Ecnuers.^^");
                bw.newLine();
            } finally {
                if (bw != null) {
                    bw.close();
                }

            }
        } catch (Throwable var10) {
            if (var0 == null) {
                var0 = var10;
            } else if (var0 != var10) {
                var0.addSuppressed(var10);
            }

            throw var0;
        }
    } catch (Exception var11) {
        var11.printStackTrace();
    }

}
```





**读文件** 

- 先打开文件，逐行读入数据，关闭文件 
- FileInputStream, InputStreamWriter, BufferedReader 
- BufferReader 
  - readLine 
- –try-resource 语句，自动关闭资源 
- –关闭最外层的数据流，将会把其上所有的数据流关闭

```java
/*
FileInputStream	节点类，负责读字节
InputStreamReader	转换类，负责字节到字符转化
BufferedReader	装饰类，负责从缓存区读入字符
三者构建关系
BufferedReader(InputStreamReader(FileInputStream))

line = br.readLine()

*/
public static void readFile1() {
    FileInputStream fis = null;
    InputStreamReader isr = null;
    BufferedReader br = null;

    try {
        fis = new FileInputStream("c:/temp/abc.txt");
        isr = new InputStreamReader(fis, "UTF-8");
        br = new BufferedReader(isr);

        String line;
        while((line = br.readLine()) != null) {		//每次读一行
            System.out.println(line);
        }
    } catch (Exception var12) {
        var12.printStackTrace();
    } finally {
        try {
            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

}

/*
BufferedReader bw = new BufferedReader(new InputStreamWriter(new FileInputStream("")));
*/
public static void readFile2() {
    try {
        Throwable var1 = null;
        Object var2 = null;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("c:/temp/abc.txt")));

            String line;
            try {
                while((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } finally {
                if (in != null) {
                    in.close();
                }

            }
        } catch (Throwable var11) {
            if (var1 == null) {
                var1 = var11;
            } else if (var1 != var11) {
                var1.addSuppressed(var11);
            }

            throw var1;
        }
    } catch (Exception var12) {
        var12.printStackTrace();
    }

}
```





## 第四节 二进制文件读写

- 二进制文件 
  - 狭义上说，采用字节编码，非字符编码的文件 
  - 广义上说，一切文件都是二进制文件 
  - 用记事本等无法打开/阅读 
- 二进制文件读写 
  - 输出数据到文件中 
  - 从文件中读取数据



**写文件** 

- 先创建文件，写入数据，关闭文件 
- FileOutputStream, BufferedOutputStream，DataOutputStream 
- DataOutputStream 
  - flush 
  - write/writeBoolean/writeByte/writeChars/writeDouble/writeInt/WriteUTF/… 
- try-resource 语句，自动关闭资源 
- 关闭最外层的数据流，将会把其上所有的数据流关闭

```java
/*
FileOutputStream 节点类，负责写字节
BufferOutputStream	装饰类，负责写字节到缓冲区
DataOutputStreamWriter	转化类，负责数据类型到字节转化

三者构建关系
DataOutputStreamWriter(BufferOutputStream(FileOutputStream))

*/


public static void writeFile() {
    FileOutputStream fos = null;
    DataOutputStream dos = null;
    BufferedOutputStream bos = null;

    try {
        fos = new FileOutputStream("c:/temp/def.dat");
        bos = new BufferedOutputStream(fos);
        dos = new DataOutputStream(bos);
        dos.writeUTF("a");
        dos.writeInt(20);
        dos.writeInt(180);
        dos.writeUTF("b");
    } catch (Exception var12) {
        var12.printStackTrace();
    } finally {
        try {
            dos.close();
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }
```





**读文件** 

- 先打开文件，读入数据，关闭文件 
- FileInputStream, BufferedInputStream，DataInputStream 
- DataInputStream 
  - read/readBoolean/readChar/readDouble/readFloat/readInt/readUTF/… 
- try-resource 语句，自动关闭资源 
- 关闭最外层的数据流，将会把其上所有的数据流关闭

```java
/*
FileInputStream	节点类，负责读字节
BufferedInputStream	装饰类，负责读字节数据到缓冲区
DataInputStreamWriter 转化类，负责字节到数据类型组汉化

三者构建关系：
DataInputStreamWriter(BufferedInputStream(FileInputStream))
*/

public static void readFile() {
    try {
        Throwable var0 = null;
        Object var1 = null;

        try {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("c:/temp/def.dat")));

            try {
                String a = dis.readUTF();
                int c = dis.readInt();
                int d = dis.readInt();
                String b = dis.readUTF();
                System.out.println("a: " + a);
                System.out.println("c: " + c);
                System.out.println("d: " + d);
                System.out.println("b: " + b);
            } finally {
                if (dis != null) {
                    dis.close();
                }

            }
        } catch (Throwable var14) {
            if (var0 == null) {
                var0 = var14;
            } else if (var0 != var14) {
                var0.addSuppressed(var14);
            }

            throw var0;
        }
    } catch (Exception var15) {
        var15.printStackTrace();
    }

}
```

