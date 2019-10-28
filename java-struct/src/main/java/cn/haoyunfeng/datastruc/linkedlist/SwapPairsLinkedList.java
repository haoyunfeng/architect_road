package cn.haoyunfeng.datastruc.linkedlist;

/**
 * @Description: 链表 两两交换
 * @author: haoyunfeng2
 * @Date: 2019/10/16 9:10 下午
 */
public class SwapPairsLinkedList {

    public static void swapParis(SingleLinkedList linkedList){
        int size = linkedList.getSize();
        SingleLinkedList.ListNode head = linkedList.getHead();
        //设置一个头结点
        SingleLinkedList.ListNode fakeHead = new SingleLinkedList.ListNode(0);
        fakeHead.setNext(head);
        //当前要开始遍历的节点
        SingleLinkedList.ListNode current = head;
        //上一轮的尾节点
        SingleLinkedList.ListNode pre = fakeHead;

        while (current != null && current.getNext() !=null) {
            //下一个遍历的节点
            SingleLinkedList.ListNode next = current.getNext().getNext();
            //要交换的节点
            SingleLinkedList.ListNode swap = current.getNext();
            //pre为上一轮的尾节点，交换节点前移
            pre.setNext(swap);
            //当前节点变为交换节点 的后 节点
            swap.setNext(current);
            //本轮尾节点 的后节点 设为下一轮要遍历的节点
            current.setNext(next);
            //本轮的尾节点
            pre = current;
            //下一轮要遍历的节点
            current = next;
        }
        //list fake的后节点设为list的头节点
        linkedList.setHead(fakeHead.getNext());
    }

    public static void main(String []args){
        SingleLinkedList list = new SingleLinkedList();
        for(int i =1;i<7;i++){
            list.add(i);
        }
        swapParis(list);
    }
}
