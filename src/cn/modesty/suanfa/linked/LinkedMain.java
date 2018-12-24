package cn.modesty.suanfa.linked;

/**
 * 检查代码是否正确的边界条件有这么几个：
 * 1.如果链表为空，代码是否能正常工作。
 * 2.如果链表只包含一个节点时，代码是否能正常工作？
 * 3.如果链表只包含两个节点时，代码是否能正常工作？
 * 4.代码逻辑在处理头节点和尾节点的时候，能否正常工作？
 */
public class LinkedMain {
    public static void main(String[] args) {
        //单链表测试数据
        /*Linked<Integer> linked = new Linked<Integer>();
        linked.add(2);
        linked.add(9);
        linked.add(3);
        linked.add(7);
        linked.add(1);
        linked.add(0);
        linked.add(22);
        linked.add(33);
        //linked.delete(4);
        linked.insert(1,3);
        linked.printElement();*/

        //LRU算法的使用
        LruLinked<Integer> lruLinked = new LruLinked();
        lruLinked.insert(0);
        lruLinked.insert(1);
        lruLinked.insert(2);
        lruLinked.insert(3);
        lruLinked.insert(4);
        lruLinked.insert(5);
        lruLinked.insert(2);
        lruLinked.insert(5);
        //lruLinked.delete(4);
        lruLinked.printElement();

        //单链表的反转
        /*Linked<Integer> linked = new Linked<Integer>();
        linked.add(2);
        linked.add(9);
        linked.add(3);
        linked.add(7);
        linked.add(1);
        linked.printElement();
        Linked.Node node = linked.recursionReversalLinked(linked.getHead());
        linked.setHead(node);
        linked.printElement();*/

        //找出链表中的环
      /*  Linked<Integer> linked = new Linked<Integer>();
        linked.add(2);
        linked.add(9);
        linked.add(3);
        linked.add(7);
        linked.add(1);
        //创建一个带环的链表
        linked.getTail().next = linked.indexElement(2);
        //输出环的长度
        System.out.println(linked.findLinkedRingLength());
        System.out.println(linked.findCoincideNode().data);*/

      //解除环
       /* Linked<Integer> linked = new Linked<Integer>();
        linked.add(2);
        linked.add(9);
        linked.add(3);
        linked.add(7);
        linked.add(1);
        //创建一个带环的链表
        linked.getTail().next = linked.indexElement(2);
        Linked.Node firstRingNode = linked.findCoincideNode();//获取第一个环结点
        int ringleng = linked.findLinkedRingLength();//获取环的长度
        for (int i = 0; i < ringleng; i++) {
            if (i == ringleng - 1){
                firstRingNode.next = null;
            }
            firstRingNode = firstRingNode.next;
        }
        linked.printElement();*/

     /*  //两个有序链表合并
        Linked<Integer> linkedone = new Linked<Integer>();
        linkedone.add(1);
        linkedone.add(3);
        linkedone.add(5);
        linkedone.add(7);
        linkedone.add(9);
        Linked<Integer> linkedtwo = new Linked<Integer>();
        linkedtwo.add(2);
        linkedtwo.add(4);
        linkedtwo.add(6);
        linkedtwo.add(8);
        linkedtwo.add(10);

        Linked.Node head = linkedone.mergeLinked(linkedone.getHead(), linkedtwo.getHead());
        linkedone.printElement(head);*/
    }


}
