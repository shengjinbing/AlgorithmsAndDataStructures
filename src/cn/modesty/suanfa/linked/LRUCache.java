package cn.modesty.suanfa.linked;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {

    private LinkedHashMap<K, V> linkedHashMap;

    public LRUCache(int capacity) {
        linkedHashMap = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public void put(K key, V value) {
        linkedHashMap.put(key, value);
    }

    public V get(K key) {
        return linkedHashMap.get(key);
    }

    public LinkedHashMap<K, V> getLinkedHashMap(){
        return linkedHashMap;
    }
}
