package cn.modesty.suanfa.linked;

public class LruLinked<T> {

    //最大只能存放5个元素
    private int  maxSize = 5;
    private int  size;
    private Node head;
    private Node current;

    static class Node<T> {
        Node next;
        T    data;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(T data) {
        Node node = new Node();
        node.data = data;
        if (isContain(data)) {
            node.next = head;
            head = node;
        } else {
            if (isEmpty()) {
                head = node;
            } else if (size == 5) {
                //需要删除元素
                delete(4);
                node.next = head;
                head = node;
            } else {
                node.next = head;
                head = node;
            }
            size++;
        }

    }

    public boolean isContain(T data) {
        int j = 0;//记录当前节点的所言
        current = head;
        while (current != null) {
            if (current.data == data) {
                //存在当前节点，删除当前节点，把节点重新插入头部
                delete(j);
                return true;
            }
            current = current.next;
            j++;
        }
        return false;
    }


    public void delete(int index) {
        if (index == 0) {
            //删除头结点
            head = head.next;
        } else if (index == size - 1) {
            //删除尾部节点
            indexElement(index - 1);
            current.next = null;
        } else {
            indexElement(index - 1);
            if (current.next.next != null) {
                current.next = current.next.next;
            }

        }
        size--;
    }

    public void indexElement(int index) {
        int j = 0;
        current = head;
        while (current != null) {
            if (index == j) {
                break;
            }
            current = current.next;
            j++;
        }
    }

    public Object get(int index) {
        return null;
    }

    public void printElement() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + ",");
            current = current.next;
        }
    }
}
