package cn.modesty.suanfa.collection;

import cn.modesty.suanfa.linked.LRUCache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * HashMap的源码分析
 * 1.加载因子（默认0.75）：为什么需要使用加载因子，为什么需要扩容呢？因为如果填充比很大，说明利用的空间很多，
 * 如果一直不进行扩容的话，链表就会越来越长，这样查找的效率很低，因为链表的长度很大（当然最新版本使用了红黑树后会改进很多）
 * 扩容之后，将原来链表数组的每一个链表分成奇偶两个子链表分别挂在新链表数组的散列位置，这样就减少了每个链表的长度，
 * 增加查找效率
 * 2.当add一个元素到某个位桶，其链表长度达到8时将链表转换为红黑树(默认为8)
 *
 * 3.get(key)方法时获取key的hash值，计算hash&(n-1)得到在链表数组中的位置first=tab[hash&(n-1)],先判断first的key是否与参数key相等，不等
 * 就遍历后面的链表找到相同的key值返回对应的Value值即可
 *
 * 4.扩容：构造hash表时，如果不指明初始大小，默认大小为16（即Node数组大小16），如果Node[]数组中的元素达到（填充比*Node.length）重新调整
 *   HashMap大小 变为原来2倍大小,扩容很耗时
 *
 * 5.使用哈希值作为树的分支变量，如果两个哈希值不等，但指向同一个桶的话，较大的那个会插入到右子树里。如果哈希值相等，HashMap希望key值最好是实现了
 * Comparable接口的，这样它可以按照顺序来进行插入
 *
 */
public class MapMain {
    public static void main(String[] args) {
        Map<String,String> hashMap = new HashMap();
        hashMap.put("1","2");
        //重复会覆盖
        hashMap.put("1","2");
        //key和value都可以为空
        hashMap.put(null,null);
        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey()+"=="+entry.getValue());
        }


        //LinkHashMap
        //ddBefore(lm.header)是把当前访问的元素挪到head的前面,即最近访问的元素被放到了链表头，
        // 如此要实现LRU算法只需要从链表末尾往前删除就可以了，多么巧妙的方法
        LRUCache<String,String> linkedHashMap = new LRUCache(4);
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
