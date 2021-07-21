# 第十章 Java数据结构

## 第一节 数组

java数组定义和初始化

```java
int a[];					//a 还没有new操作，实际上是null，也不知道内存位置
int[] b;					//b 还没有new操作，实际上是null，也不知道内存位置
int[] c = new int[2];		//c有2个元素 都是0
c[0]=10;c[1]=20;			//逐个初始u哈
	
int d[]=new int[]{0,2,4};	//d有3个元素，0，2，4，同时定义和初始化
int d1[]={1,3,5};			//d1有3个元素，1，3，5同时定义和初始化

//注意声明变量时候没有分配内存，不需要指定大小，以下是错误示例
//int e[5];
//int[5] f;
//int[5] g = new int[5];
//int h[5] = new int[5];
```



数组索引

- 数组的length属性标示数组的长度
- 从0开始，到length - 1
- int[] a = new int[5];  a.length 是5
- 数组不能越界访问，否则会报错



数组遍历

```java
for(int i = 0 ; i < d.length;i++)
{
    System.out.printlin(d[i]);
}
//无需控制
for(int e:d)
{
    System.out.println(e);
}

```



多维数组

- 数组的数组
- 存储是按照行存储原则

```java
//规则数组
int a[][]=new int[2][3];
//不规则数组
int b[][];
b = new int[3][];
b[0] = new int[3];
b[1] = new int[4];
b[2] = new int[5];

//赋值
int k = 0;
for(int i = 0; i < a.length;i++)
{
    for(int j = 0 ; j<a[i].length; j++)
    {
        a[i][j] = ++k;
    }
}

//遍历
for(int[] items:a)
{
    for(int item:items)
    {
        System.out.print(item+",");
    }
    System.out.println();
}
```



## 第二节 JCF

**容器：**能够存放数据的空间结构

- 数组/多维数组，只能线性存放
- 列表/散列表/树



**集合接口Collection**

- add,contains,remove,size
- iterator



**迭代器结构Iterator**

- hasNext 判断是否有下一个元素
- next
- rmove



**主要的数据结构实现类：**

- 列表（List,ArrayList,LinkedList)
- 集合（Set,HashSet,TreeSet,LinkedHashSet)
- 映射（Map,HashMap,TreeMap,LinkedHashMap)



**主要的算法类：**

- Arrays: 对数组进行查找和排序等操作
- Collections：对Collection及其子类进行排序和查找操作



## 第三节 列表List

 ArrayList：

- 以数组实现的列表，不支持同步

- 利用索引位置可以快速定位访问

- 不适合指定位置的插入、删除操作

- 适合变动不大，**主要用于查询的数据**

- 和Java数组相比，其容量是可动态调整的

- ArrayList在元素填满容器时会自动扩充容器大小的50%




```java
 public static void main(String[] a) {
        ArrayList<Integer> al = new ArrayList();
        al.add(3);	//arraylist只能装对象，当add(3)时，
        al.add(2);	//会自动将普通int变量3自动装箱为integar(3)的对象,然后放入容器中
        al.add(1);
        al.add(4);
        al.add(5);
        al.add(6);
        al.add(new Integer(6));
        System.out.print("The third element is  ");
        System.out.println(al.get(3)); //元素是从0开始的，所以取出来的是第四个
        al.remove(3); 	//删除第四个元素，后面元素往前挪动
        al.add(3, 9);	//将9插入到第4个元素，后面元素往后挪动
        System.out.println("======遍历方法=============");
        ArrayList<Integer> as = new ArrayList(100000);

        for(int i = 0; i < 100000; ++i) {
            as.add(i);
        }

        traverseByIterator(as);
        traverseByIndex(as);
        traverseByFor(as);
    }

    public static void traverseByIterator(ArrayList<Integer> al) {
        long startTime = System.nanoTime();
        System.out.println("============迭代器遍历==============");
        Iterator iter1 = al.iterator();

        while(iter1.hasNext()) {
            iter1.next();
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(duration + "纳秒");
    }

    public static void traverseByIndex(ArrayList<Integer> al) {
        long startTime = System.nanoTime();
        System.out.println("============随机索引值遍历==============");

        for(int i = 0; i < al.size(); ++i) {
            al.get(i);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(duration + "纳秒");
    }

    public static void traverseByFor(ArrayList<Integer> al) {
        long startTime = System.nanoTime();
        System.out.println("============for循环遍历==============");

        Integer var3;
        for(Iterator var4 = al.iterator(); var4.hasNext(); var3 = (Integer)var4.next()) {
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(duration + "纳秒");
    }
```



LinkedList：

- 以双向链表实现的列表，不支持同步
- 可被当作堆栈、队列和双端队列进行操作
- 顺序访问高效，随机访问较差，中间插入和删除高效
- 适用于经常变化的数据

```java
public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList();
        ll.add(3);
        ll.add(2);
        ll.add(5);
        ll.add(6);
        ll.add(6);
        System.out.println(ll.size());
        ll.addFirst(9);
        ll.add(3, 10);
        ll.remove(3);
        LinkedList<Integer> list = new LinkedList();

        for(int i = 0; i < 100000; ++i) {
            list.add(i);
        }

        traverseByIterator(list);
        traverseByIndex(list);
        traverseByFor(list);
    }

    public static void traverseByIterator(LinkedList<Integer> list) {
        long startTime = System.nanoTime();
        System.out.println("============迭代器遍历==============");
        Iterator iter1 = list.iterator();

        while(iter1.hasNext()) {
            iter1.next();
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(duration + "纳秒");
    }

    public static void traverseByIndex(LinkedList<Integer> list) {
        long startTime = System.nanoTime();
        System.out.println("============随机索引值遍历==============");

        for(int i = 0; i < list.size(); ++i) {
            list.get(i);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(duration + "纳秒");
    }

    public static void traverseByFor(LinkedList<Integer> list) {
        long startTime = System.nanoTime();
        System.out.println("============for循环遍历==============");

        Integer var3;
        for(Iterator var4 = list.iterator(); var4.hasNext(); var3 = (Integer)var4.next()) {
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(duration + "纳秒");
    }
```





## 第四节 集合Set

**集合set特性**

* 确定性	
* 互异性
* 无序性

**集合接口set种类**

* hashset	基于散列函数的集合，无序，不支持同步
* treeset      基于树结构的集合，可排序的，不支持同步
* linkedHashSet    基于散列函数和双向链表的集合，可排序的，不 支持同步



### **HashSet	---输出无顺序**

- 基于HashMap实现的，可以容纳null元素, 不支持同步 
- add 添加一个元素 
- clear 清除整个HashSet 
- contains 判定是否包含一个元素 
- remove 删除一个元素 
- size 大小 
- retainAll 计算两个集合交集

```java
HashSet<Integer> hs = new HashSet<Integer>();
hs.add(null);
hs.add(1);
hs.add(1);
hs.add(2);
hs,add(null);
hs.size();   // 3   1重复
hs.contains(6);   //hs是否包含6
hs.remove(1)；       //移除某元素
set1.retainAll(set);   //set1返回set1与set2的交集
```

遍历

```java
//for循环遍历
for(Integer item : hs)
{
    System.out.println(item);
}

//迭代器遍历
Iterator<Integer> iter1 = hs.iterator();
while(iter1.hasNext())	iter1.next();

```



### **LinkedHashSet    ---类似于hashset，但插入输出有顺序**

- 继承HashSet，也是基于HashMap实现的，可以容纳null元素 
- 不支持同步
- 方法和HashSet基本一致
  - add, clear, contains, remove, size 
- 通过一个双向链表维护插入顺序 



### **TreeSet 	---按照所存储对象大小升序输出的**

- 基于TreeMap实现的，不可以容纳null元素，不支持同步 
-  add 添加一个元素 
- clear 清除整个TreeSet 
- contains 判定是否包含一个元素
-  remove 删除一个元素 size 大小 
- 根据compareTo方法或指定Comparator排序



> HashSet,LinkedHashSet,TreeSet的元素都只能是对象



### <font color = "red">**不同set判断元素重复的原则**</font>

**1.HashSet和LinkedHashSet判定元素重复的原则** 

- 判定两个元素的hashCode返回值是否相同，若不同，返回false 

- 若两者hashCode相同，判定equals方法，若不同，返回false；否则 返回true。 

- hashCode和equals方法是所有类都有的，因为Object类有

  **类中判断方法：**

  ​	在类中写hasCode函数和equals函数

  ​	hashCode相同才会调用equals函数



> 三个方法三位一体，equals()是相同的，hashCode()是相同的，toString()也应该是相同的



**2.TreeSet判定元素重复的原则** 

* 添加到treeset，需要实现Comparable接口，即实现compareTo方法

- 需要元素继承自Comparable接口 

- 比较两个元素的compareTo方法

  **类中判断方法：**

  实现Comparable接口，必须实现compareTo方法来比较大小

  compareTo方法具体规则如下：

  `int a = obj1.compareTo(obj2);  //如果a>0,则obj1>obj2 如果a==0，则obj1 ==obj2 如果a<0...`





## 第五节 映射Map

**Map映射**

数学定义：两个集合之间的元素对应关系。 

–一个输入对应到一个输出 

–{1，张三}，{2，李四}，{Key，Value}，键值对，K-V对



**Map种类** 

–Hashtable（同步，慢，数据量小） 

–HashMap（不支持同步，快，数据量大） 

–Properties (同步，文件形式，数据量小)



### **1、Hashtable** 

- K-V对，K和V都不允许为null 
- 同步，多线程安全 
- 无序的 –适合小数据量 –
- **主要方法：clear, contains/containsValue, containsKey, get, put,remove, size**

```java
Hashtable<Integer,String> ht = new Hashtable<Integer,String>();
ht.put(1000,"aaa");			//添加元素
ht.contains("aaa");			//是否包含元素
ht.containsValue("aaa");	//是否包含value
ht.containsKey(10000);		//是否包含键
ht.get(1000);				//获得key为1000下的value值
ht.remove(1000);			//删除key为1000的值
ht.clear();					//删除所有元素
```

- **遍历**

```java
1.KeySet迭代器遍历

Integer key;
String value;
Iterator<Integer> iter = ht.keySet().iterator();
while(iter.hasNext())
{
    key = iter.next();
    value = ht.get(key);
    System.out.println("Key"+key+",Value:"+value);
}

2.KeyEnumeration迭代器遍历
    
Integer key;
String value;
Enumeration<Integer> keys = ht.keys();
while(iter.hasMoreElements())
{
    key = iter.next();
    value = ht.get(key);
    System.out.println("Key"+key+",Value:"+value);
}
```



### **2、HashMap**

K-V对，K和V都允许为null 

不同步，多线程不安全 

无序的 

主要方法：clear, containsValue, containsKey, get, put,remove, size

```java
1.Entry迭代器遍历

Integer key;
String value;
Iterator<Entry<Integer,String>> iter = ht.entrySet().iterator();
while(iter.hasNext())
{
	Map.Entry<Integer,String> entry = iter.next();
    key = entry.getKey();
    value = entry.getValue;
}

2.KeySet迭代器遍历
```



### **3、LinkedHashMap** 

基于双向链表的维持插入顺序的HashMap 

​	**遍历：**Entry遍历 KeySet遍历



### **4、TreeMap** 

基于红黑树的Map，可以根据key的自然排序或者compareTo方法 进行排序输出

​	**遍历：**Entry遍历 KeySet遍历



### **4、Properties** 

继承于Hashtable 

可以将K-V对保存在文件中 

适用于数据量少的配置文件 

继承自Hashtable的方法：clear, contains/containsValue, containsKey, get, put,remove, size 

从文件加载的load方法， 写入到文件中的store方法

 获取属性 getProperty ，设置属性setProperty 



## 第六节 工具类

**JCF中工具类**

–不存储数据，而是在数据容器上，实现高效操作 

• 排序 

• 搜索 

Arrays类 

Collections类



### **1、Arrays：处理对象是数组** 

排序：对数组排序, sort/parallelSort。 

查找：从数组中查找一个元素, binarySearch。 

批量拷贝：从源数组批量复制元素到目标数组, copyOf。 

批量赋值：对数组进行批量赋值, fill。

等价性比较：判定两个数组内容是否相同, equals。

```java
int [] a = new int[10];
Arrays.sort(a);
Arrays.binarySearche(a,1000); //返回1000所在的位置
int b = Arrays.copyOf(a,5);   //拷贝前五个元素
Arrays.fill(a,100);		//全部赋值为100
Arrays.fill(a,2,8,200);	//第2个到第8个元素赋值200
Arrays.equals(a,b); 	//判定内容是否相等
```



### **2、Collections: 处理对象是 Collection及其子类** 

排序：对List进行排序，sort。

搜索：从List中搜索元素，binarySearch 

批量赋值：对List批量赋值，fill。 

最大、最小：查找集合中最大/小值，max，min 

反序：将List 反序排列，reverse

```java
ArrayList<Integer> list  = new ArrayList<Integer>();
//排序
Collections.sort(list);
//检索
Collections.binarySearch(list,12); 返回元素的索引位置
//最大值 最小值
Collections.max(list);
Collections.min(list); 
//翻转
Collections.reverse(list);
//赋值
Collections.fill(list,100);
```





对象比较方法Comparable/Comparator

**对象实现Comparable接口（需要修改对象类）** 

- compareTo方法
  - .  > 返回1， ==返回0，<返回-1 
- Arrays和Collections在进行对象sort时，自动调用该方法 

```java
public class Person implements Comparable<Person>
{
    String name;
    int age;
	public Person(String name,int age){
        this.name = name;
        this.age = age;
    }
    public int compareTo(Person another)
    {
        int i = 0 ;
        i = name.compareTo(another.name); //使用字符串的比较
        if(i==0)	return age-another.age;	//如果名字一样，比较年龄，返回年龄结果
        else return i;		//名字不一样，返回比较名字的结果
    }
 	public static void main(String... a)
    {
        Person[] ps = new Person[3];
        ps[0] = new Person("Tom",20);
        ps[1] = new Person("Mike",18);
        ps[1] = new Person("Mike",20);
        
        Arrays.sort(ps);
    }
}
```



**新建Comparator（适用于对象类不可更改的情况）** 

- compare方法 • 
  - . > 返回1， ==返回0，<返回-1 
- Comparator比较器将作为参数提交给工具类的sort方法

```java
public class Person2{
    private String name;
    private int age;
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public Person2(String name,int age)
    {
        this.name = name;
        this.age = age;
    }
}
public class Person2Computer implements Comparator<Person2>{
    public int compare(Person2 one,Person2 another){
        int i = 0;
        i = one.getName().compareTo(another.getName());
        if(i==0)	return one.getAge() - another.getAge();
        else return i;
    }
    
}
public static void main(String... a)
{
        Person[] ps = new Person[3];
        ps[0] = new Person("Tom",20);
        ps[1] = new Person("Mike",18);
        ps[1] = new Person("Mike",20);
        
        Arrays.sort(ps,new Person2Comparator());
    }
```

