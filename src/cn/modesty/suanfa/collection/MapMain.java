package cn.modesty.suanfa.collection;

import cn.modesty.suanfa.linked.LRUCache;
import cn.modesty.suanfa.linked.LruCache1;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap的源码分析
 * 1.加载因子（默认0.75）：为什么需要使用加载因子，为什么需要扩容呢？因为如果填充比很大，说明利用的空间很多，
 * 如果一直不进行扩容的话，链表就会越来越长，这样查找的效率很低，因为链表的长度很大（当然最新版本使用了红黑树后会改进很多）
 * 扩容之后，将原来链表数组的每一个链表分成奇偶两个子链表分别挂在新链表数组的散列位置，这样就减少了每个链表的长度，
 * 增加查找效率；
 * 2.当add（在此时创建桶的数组）一个元素到某个位桶，其链表长度达到8时将链表转换为红黑树(默认为8)；
 * 3.get(key)方法时获取key的hash值，计算hash&(n-1)得到在链表数组中的位置first=tab[hash&(n-1)],先判断first的key是否与参数key相等，不等
 * 就遍历后面的链表找到相同的key值返回对应的Value值即可；
 * 4.扩容：构造hash表时，如果不指明初始大小，默认大小为16（即Node数组大小16），如果Node[]数组中的元素达到（填充比*Node.length）重新调整
 *   HashMap大小 变为原来2倍大小,扩容很耗时；
 * 5.使用哈希值作为树的分支变量，如果两个哈希值不等，但指向同一个桶的话，较大的那个会插入到右子树里。如果哈希值相等，HashMap希望key值最好是实现了
 * Comparable接口的，这样它可以按照顺序来进行插入；
 * 6.hash碰撞，拉链法；再次hash；hash扰乱
 * 7.新加元素添加到链表头部倍，扩容会从新进行hash计算分布。
 * 8.每次扩容原来容量的2
 *
 * 1.8JDK版本
 * 1.先添加元素再扩容；
 * 2.红黑树取代链表；
 * 3.没有使用头插法；
 * 4.扩容机制更加巧妙，利用高位和低位赋值新数组上面的值，不需要直接遍历老数组来放元素；
 *
 *
 *
 * 6.HashSet和HashMap的关系
 *   HashSet里面实际上是创建了一个HashMap，所以Value都是一个默认的final的Object对象
 *
 * 7.HashMap与HashTable的区别
 *  1、HashTable是先线程安全的（在put，get等方法上都粗暴的加上了synchronized关键字）；
 *  2、HashMap的默认initialCapacity是16，HashTable是11；
 *  3、HashTable的put方法的key（key.hashCode()）和value(抛出异常)不能为null；
 *  4、当需要多线程操作的时候可以使用线程安全的ConcurrentHashMap。ConcurrentHashMap虽然也是线程安全的，但是它的效率比Hashtable要高好多倍。
 *  5、因为ConcurrentHashMap使用了分段锁，并不对整个数据进行锁定;
 *  6、Hashtable、HashMap都使用了 Iterator。而由于历史原因，Hashtable还使用了Enumeration(列举)的方式 。HashMap的Iterator是fail-fast迭代器。
 *  当有其它线程改变了HashMap的结构（增加，删除，修改元素），将会抛出ConcurrentModificationException。不过，通过Iterator的remove()方
 *  法移除元素则不会抛出ConcurrentModificationException异常。但这并不是一个一定发生的行为，要看JVM。JDK8之前的版本中，Hashtable是没有
 *  fail-fast机制的。在JDK8及以后的版本中 ，HashTable也是使用fail-fast的， 源码如下：modCount的使用类似于并发编程中的CAS（Compare and Swap）
 *  技术。我们可以看到这个方法中，每次在发生增删改的时候都会出现modCount++的动作。而modcount可以理解为是当前hashtable的状态。每发生一次操作，
 *  状态就向前走一步。设置这个状态，主要是由于hashtable等容器类在迭代时，判断数据是否过时时使用的。尽管hashtable采用了原生的同步锁来保护数据安全。
 *  但是在出现迭代数据的时候，则无法保证边迭代，边正确操作。于是使用这个值来标记状态。一旦在迭代的过程中状态发生了改变，则会快速抛出一个异常，终止迭代行为。
 *  fail-fast和fail_safe(失败安全，拷贝了一份数据进行迭代)
 *  7.Hashtable扩容时，将容量变为原来的2倍加1，而HashMap扩容时，将容量变为原来的2倍。
 *  8.哈希值的使用不同，HashTable直接使用对象的hashCode。而HashMap重新计算hash值。
 *
 * 8.ConcurrentHashMap知道吗，ConcurrentHashMap在jdk1.8之后的优化?
 * https://www.jianshu.com/p/865c813f2726
 * 1.如果没有初始化就先调用initTable（）方法来进行初始化过程
 * 2.如果没有hash冲突就直接CAS插入
 * 3.如果还在进行扩容操作就先进行扩容
 * 4.如果存在hash冲突，就加锁来保证线程安全，这里有两种情况，一种是链表形式就直接遍历到尾端插入，一种是红黑树就按照红黑树结构插入，
 * 5.最后一个如果Hash冲突时会形成Node链表，在链表长度超过8，Node数组超过64时会将链表结构转换为红黑树的结构，break再一次进入循环
 * 6.如果添加成功就调用addCount（）方法统计size，并且检查是否需要扩容
 * 7.多线程扩容
 * 8.put的流程现在已经分析完了，你可以从中发现，他在并发处理中使用的是乐观锁，当有冲突的时候才进行并发处理
 *
 * 9、JDK1.8的实现降低锁的粒度，JDK1.7版本锁的粒度是基于Segment的，包含多个HashEntry，而JDK1.8锁的粒度就是HashEntry（首节点）
 * 10、JDK1.8版本的数据结构变得更加简单，使得操作也更加清晰流畅，因为已经使用synchronized来进行同步，所以不需要分段锁的概念，也就不需要Segment这种数据结构了，由于粒度的降低，实现的复杂度也增加了
 * 11、JDK1.8使用红黑树来优化链表，基于长度很长的链表的遍历是一个很漫长的过程，而红黑树的遍历效率是很快的，代替一定阈值的链表，这样形成一个最佳拍档
 * 12、JDK1.8为什么使用内置锁synchronized来代替重入锁ReentrantLock，我觉得有以下几点
 * 13、因为粒度降低了，在相对而言的低粒度加锁方式，synchronized并不比ReentrantLock差，在粗粒度加锁中ReentrantLock可能通过Condition来控制各个低粒度的边界，更加的灵活，而在低粒度中，Condition的优势就没有了
 * 14、JVM的开发团队从来都没有放弃synchronized，而且基于JVM的synchronized优化空间更大，使用内嵌的关键字比使用API更加自然
 * 15、在大量的数据操作下，对于JVM的内存压力，基于API的ReentrantLock会开销更多的内存，虽然不是瓶颈，但是也是一个选择依据
 *
 * 9.HashMap的hash算法和扩容机制的原因？？？？？
 *   1.之前一条链现在分成两条链表
 *   2.当前容量的大小是否小于64，如果小于这个值还是要进行扩容而不是转化为红黑树
 *10.SparseArray和HashMap的区别?
 *11.hashmap 实现原理，给一个 key 如何计算槽位，如何取值?
 *
 *
 * SynchronizedMap
 * 对自己每个方法加锁
 *
 */
public class MapMain {
    public static void main(String[] args) {
        //0001 0100
        System.out.println(20>>>2);
        System.out.println(-20>>2);

        Map<Integer,String> synchronizedMap = Collections.synchronizedMap(new HashMap<Integer,String>());
        synchronizedMap.put(1,"2");

        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("1","2");

        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("1","2");
        //重复会覆盖
        hashMap.put("1","2");
        System.out.println(hashMap.get("3"));
        //key和value都可以为空
        hashMap.put(null,null);
        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey()+"=="+entry.getValue());
        }

        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(2);
        Iterator iterator = hashSet.iterator();
        for (Object o : hashSet) {
            System.out.println(o);
        }
        Map<String,String> hashtable = new Hashtable<>();
        System.out.println("11".hashCode());
        System.out.println("10".hashCode());
        hashtable.put("11","22");
        //重复会覆盖
        hashtable.put("11","22");
        //key和value都可以为空
        hashtable.put("33","33");
        Set<Map.Entry<String, String>> entrie = hashtable.entrySet();
        for (Map.Entry<String, String> entry : entrie) {
            System.out.println(entry.getKey()+"=="+entry.getValue());
        }

        //LinkHashMap
        //ddBefore(lm.header)是把当前访问的元素挪到head的前面,即最近访问的元素被放到了链表头，
        // 如此要实现LRU算法只需要从链表末尾往前删除就可以了，多么巧妙的方法
        LruCache1<String,String> linkedHashMap = new LruCache1(4);
        linkedHashMap.put("1","1");
        linkedHashMap.put("2","2");
        linkedHashMap.put("3","3");
        linkedHashMap.put("4","4");
        linkedHashMap.put("5","5");
        linkedHashMap.get("3");
        Set<String> strings = linkedHashMap.getLinkedHashMap().keySet();
        for(String str:strings){
            System.out.println(str);
        }
    }
}

/**
 * 一：快速失败（fail—fast)
 * 快速失败迭代器，如果在使用迭代器遍历一个集合的时候，如果这个集合的内容被修改了，比如增加删除或者修改。就会跑出异常。
 * 原因是：在迭代的时候，先计算一个modCount变量。然后当时候hasNext（）方法时，要判断modCount是否等于expectedModCount值，如果是，
 * 就返回遍历，如果不是就跑出异常。终止遍历。
 * 注意：这里异常的抛出条件是检测到 modCount！=expectedmodCount 这个条件。如果集合发生变化时修改modCount值刚好又设置为了expectedmodCount
 * 值，则异常不会抛出。因此，不能依赖于这个异常是否抛出而进行并发操作的编程，这个异常只建议用于检测并发修改的bug。
 * 场景：java.util包下的集合类都是快速失败的，不能在多线程下发生并发修改（迭代过程中被修改）。
 *
 * 二：安全失败（fail—safe）
 * 采用安全失败机制的集合容器，在遍历时不是直接在集合内容上访问的，而是先复制原有集合内容，在拷贝的集合上进行遍历。
 * 原理：由于迭代时是对原集合的拷贝进行遍历，所以在遍历过程中对原集合所作的修改并不能被迭代器检测到，所以不会触发Concurrent Modification Exception。
 * 缺点：基于拷贝内容的优点是避免了Concurrent Modification Exception，但同样地，迭代器并不能访问到修改后的内容，即：迭代器遍历的是开始遍历那一刻拿到的集合拷贝，在遍历期间原集合发生的修改迭代器是不知道的。
 * 场景：java.util.concurrent包下的容器都是安全失败，可以在多线程下并发使用，并发修改。
 */

