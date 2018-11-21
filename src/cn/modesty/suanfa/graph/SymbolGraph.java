package cn.modesty.suanfa.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

import java.awt.event.KeyEvent;

public class SymbolGraph {
    private ST<String, Integer> st;//符号名 -> 索引
    private String[]            keys;//索引 -> 符号表
    private Graph               G;

    public SymbolGraph(String stream, String sp) {
        st = new ST<>();
        In in = new In(stream);
        st.put("JFK", 0);
        st.put("MCO", 1);
        st.put("ORD", 2);
        st.put("DEN", 3);
        st.put("HOU", 4);
        st.put("DFW", 5);
        st.put("PHX", 6);
        st.put("ATL", 7);
        st.put("LAX", 8);
        st.put("LAS", 9);

        keys = new String[st.size()];
        for (String name : st) {
            keys[st.get(name)] = name;
        }

        in = new In(stream);//第二遍
        while (in.hasNextLine()) {//构造图
            String[] a = in.readLine().split(sp);
            int      v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s){
        return st.contains(s);
    }

    public int index(String s){
        return st.get(s);
    }

    public String name(int v ){
        return keys[v];
    }

    public Graph G(){
        return G;
    }

}
