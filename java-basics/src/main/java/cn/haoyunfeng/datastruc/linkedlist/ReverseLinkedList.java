package cn.haoyunfeng.datastruc.linkedlist;

/**
 * @Description: 链表翻转
 * @author: haoyunfeng
 * @Date: 2019/10/16 9:41 上午
 */
public class ReverseLinkedList {


    public static SingleLinkedList reverseList(SingleLinkedList singleLinkedList) {
        SingleLinkedList.ListNode head = singleLinkedList.getHead();
        SingleLinkedList.ListNode pre = null;
        SingleLinkedList.ListNode current =head;
        SingleLinkedList.ListNode next ;
        while (current !=null){
            singleLinkedList.setHead(current);
            next = current.getNext();
            current.setNext(pre);
            pre = current;
            current = next;
        }
        return singleLinkedList;
    }
    public static void main(String []args){
        SingleLinkedList list = new SingleLinkedList();
        for(int i=1;i<6;i++){
            list.add(i);
        }
        System.out.println(list);
        reverseList(list);
    }
}
