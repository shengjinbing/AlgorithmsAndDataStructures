package cn.modesty.suanfa.linked;
import java.util.*;

/**
 * 百度面试题，也可以通过HashMap和LinkedList的方式解决，但是性能低下。
 */
public class LRUCache2 {
    class Node{
        int key;
        int value;
        Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
    private int capacity = 0;
    private int size = 0;
    private Map<Integer,Node> map = new HashMap();
    private LinkedList<Node> list = new LinkedList();

    public int[] LRU (int[][] operators, int k) {
        ArrayList<Integer> data = new ArrayList();
        capacity = k;
        for(int i = 0; i < operators.length;i++){
            if(operators[i][0] == 1){
                set(operators[i][1],operators[i][2]);
            }else if(operators[i][0] == 2){
                data.add(get(operators[i][1]));
            }
        }
        int[] result = new int[data.size()];
        for(int i = 0;i < data.size();i++){
            result[i] = data.get(i);
        }
        return result;
    }

    public void set(int key,int value){
        Node node = new Node(key,value);
        if(map.containsKey(key)){
            //包含了结点需要覆盖
            list.remove(map.get(key));
            list.addFirst(node);
            map.put(key,node);
        }else{
            if(size >= capacity){
                Node removeNode = list.removeLast();
                map.remove(removeNode.key);
            }else{
                size++;
            }
            map.put(key,node);
            list.addFirst(node);
        }
    }

    public int get(int key){
        if(map.containsKey(key)){
            Node node = map.get(key);
            list.remove(node);
            list.addFirst(node);
            return map.get(key).value;
        }else{
            return -1;
        }
    }
}
