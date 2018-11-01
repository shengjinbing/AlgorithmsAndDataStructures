package cn.modesty.suanfa.bagqueuestack;



public class PQdata implements Comparable<PQdata> {
    private int data;

    public PQdata(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    @Override
    public int compareTo(PQdata o) {
        return this.data - o.data;
    }
}
