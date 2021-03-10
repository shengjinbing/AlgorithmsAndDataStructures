package cn.modesty.suanfa;

import cn.modesty.suanfa.gzip.GzipUtils;

import java.io.IOException;
import java.util.*;

public class main {
    public static void main(String[] args) {

       /* //String str = "scheme=bitcoin&acceptAddress=1G8a6BuYnpT9ND4cmMfAARzAqV4P6ejKXR&outAddress=1P9UfvzmsYpMyrTgK6uF5pHpertaCLoZ7x&amount=0.00005&balance=0.00833754&fee=0.00005";
        String str = "scheme=bitcoin&acceptAddress=1G8a6BuYnpT9ND4cmMfAARzAqV4P6ejKXR&outAddress=1P9UfvzmsYpMyrTgK6uF5pHpertaCLoZ7x&amount=0.00005&balance=0.00833754&fee=0.00005&gasLimit=&gasPrice=&addressIndex=0&utxoBean=%7B%22found%22%3A%220.00833754%22%2C%22reqAmount%22%3A%220.00005%22%2C%22utxo%22%3A%5B%7B%22address%22%3A%221P9UfvzmsYpMyrTgK6uF5pHpertaCLoZ7x%22%2C%22addressIndex%22%3A0%2C%22script%22%3A%2276a914f2ed319715965d355ed70f400c85e12a6a5cccd888ac%22%2C%22txid%22%3A%222e5e733f9228c8f6b73b8a16023709ae82c4c167a2bd77428cdb985fe480f236%22%2C%22value%22%3A%220.00833754%22%2C%22vout%22%3A1%7D%5D%7D&nonce=&rate=8scheme=bitcoin&acceptAddress=1G8a6BuYnpT9ND4cmMfAARzAqV4P6ejKXR&outAddress=1P9UfvzmsYpMyrTgK6uF5pHpertaCLoZ7x&amount=0.00005&balance=0.00833754&fee=0.00005&gasLimit=&gasPrice=&addressIndex=0&utxoBean=%7B%22found%22%3A%220.00833754%22%2C%22reqAmount%22%3A%220.00005%22%2C%22utxo%22%3A%5B%7B%22address%22%3A%221P9UfvzmsYpMyrTgK6uF5pHpertaCLoZ7x%22%2C%22addressIndex%22%3A0%2C%22script%22%3A%2276a914f2ed319715965d355ed70f400c85e12a6a5cccd888ac%22%2C%22txid%22%3A%222e5e733f9228c8f6b73b8a16023709ae82c4c167a2bd77428cdb985fe480f236%22%2C%22value%22%3A%220.00833754%22%2C%22vout%22%3A1%7D%5D%7D&nonce=&rate=8scheme=bitcoin&acceptAddress=1G8a6BuYnpT9ND4cmMfAARzAqV4P6ejKXR&outAddress=1P9UfvzmsYpMyrTgK6uF5pHpertaCLoZ7x&amount=0.00005&balance=0.00833754&fee=0.00005&gasLimit=&gasPrice=&addressIndex=0&utxoBean=%7B%22found%22%3A%220.00833754%22%2C%22reqAmount%22%3A%220.00005%22%2C%22utxo%22%3A%5B%7B%22address%22%3A%221P9UfvzmsYpMyrTgK6uF5pHpertaCLoZ7x%22%2C%22addressIndex%22%3A0%2C%22script%22%3A%2276a914f2ed319715965d355ed70f400c85e12a6a5cccd888ac%22%2C%22txid%22%3A%222e5e733f9228c8f6b73b8a16023709ae82c4c167a2bd77428cdb985fe480f236%22%2C%22value%22%3A%220.00833754%22%2C%22vout%22%3A1%7D%5D%7D&nonce=&rate=8scheme=bitcoin&acceptAddress=1G8a6BuYnpT9ND4cmMfAARzAqV4P6ejKXR&outAddress=1P9UfvzmsYpMyrTgK6uF5pHpertaCLoZ7x&amount=0.00005&balance=0.00833754&fee=0.00005&gasLimit=&gasPrice=&addressIndex=0&utxoBean=%7B%22found%22%3A%220.00833754%22%2C%22reqAmount%22%3A%220.00005%22%2C%22utxo%22%3A%5B%7B%22address%22%3A%221P9UfvzmsYpMyrTgK6uF5pHpertaCLoZ7x%22%2C%22addressIndex%22%3A0%2C%22script%22%3A%2276a914f2ed319715965d355ed70f400c85e12a6a5cccd888ac%22%2C%22txid%22%3A%222e5e733f9228c8f6b73b8a16023709ae82c4c167a2bd77428cdb985fe480f236%22%2C%22value%22%3A%220.00833754%22%2C%22vout%22%3A1%7D%5D%7D&nonce=&rate=8scheme=bitcoin&acceptAddress=1G8a6BuYnpT9ND4cmMfAARzAqV4P6ejKXR&outAddress=1P9UfvzmsYpMyrTgK6uF5pHpertaCLoZ7x&amount=0.00005&balance=0.00833754&fee=0.00005&gasLimit=&gasPrice=&addressIndex=0&utxoBean=%7B%22found%22%3A%220.00833754%22%2C%22reqAmount%22%3A%220.00005%22%2C%22utxo%22%3A%5B%7B%22address%22%3A%221P9UfvzmsYpMyrTgK6uF5pHpertaCLoZ7x%22%2C%22addressIndex%22%3A0%2C%22script%22%3A%2276a914f2ed319715965d355ed70f400c85e12a6a5cccd888ac%22%2C%22txid%22%3A%222e5e733f9228c8f6b73b8a16023709ae82c4c167a2bd77428cdb985fe480f236%22%2C%22value%22%3A%220.00833754%22%2C%22vout%22%3A1%7D%5D%7D&nonce=&rate=8";
        try {
            String compress = GzipUtils.gzip(str);
            System.out.println(compress);
            System.out.println(compress.length());
            String compress1 = GzipUtils.gunzip(compress);
            System.out.println(compress1);
            System.out.println(compress1.length());

            //System.out.println(GzipUtils.uncompress(str1));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
       try {
           String a = "as";

           int b = Integer.parseInt(a);
           System.out.println("无异常");
       }catch (Exception e){
           System.out.println("异常");

       }
        System.out.println("异常后");
    }
    public int FirstNotRepeatingChar(String str) {
        Map<Integer,Integer> map = new HashMap();
        char[] chars = str.toCharArray();
        for(int i= 0;i < chars.length;i++){
            int j = 0;
            if(map.containsKey((int)chars[i])){
                j = map.get((int)chars[i]);
            }
            map.put((int)chars[i],++j);

        }
        for(int i= 0;i < chars.length;i++){
            int j = map.get((int)chars[i]);;
            if(j == 1){
                return i;
            }

        }
        return 0;
    }
}
