package cn.haoyunfeng.datastruc.linkedlist;

import java.util.HashSet;

/**
 * @Description: 判断链表是否有环
 * @author: haoyunfeng2
 * @Date: 2019/10/17 11:13 上午
 */
public class CycleLinkedList {

    /**
     * @description: hash表
     * @param:  [list]
     * @return: java.lang.Boolean
     * @author: haoyunfeng
     * @date: 2019/10/17 2:36 下午
     */
    public static Boolean hasCycleByHash(SingleLinkedList list){
        SingleLinkedList.ListNode head = list.getHead();
        SingleLinkedList.ListNode current = head;

        HashSet set = new HashSet();
        while (current != null ){
            if(set.contains(current)){
                return true;
            }else {
                set.add(current);
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * @description: 双指针
     * @param:  [list]
     * @return: java.lang.Boolean
     * @author: haoyunfeng
     * @date: 2019/10/17 2:37 下午
     */
    public static Boolean hasCycleByDualPointer(SingleLinkedList list){
        SingleLinkedList.ListNode slow = list.getHead();
        SingleLinkedList.ListNode fast = slow.getNext();
        while (slow != fast){
            if(fast == null || fast.getNext() == null){
                return false;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return true;
    }

    public static void main(String []args){
        SingleLinkedList list = new SingleLinkedList();
        SingleLinkedList.ListNode node1 = new SingleLinkedList.ListNode(1);
        SingleLinkedList.ListNode node2 = new SingleLinkedList.ListNode(2);
        SingleLinkedList.ListNode node3 = new SingleLinkedList.ListNode(3);
        SingleLinkedList.ListNode node4 = new SingleLinkedList.ListNode(4);
        SingleLinkedList.ListNode node5 = new SingleLinkedList.ListNode(5);
        SingleLinkedList.ListNode node6 = new SingleLinkedList.ListNode(6);
        list.setHead(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        Boolean isCycle = hasCycleByHash(list);
        System.out.println(isCycle);
        Boolean hasCycle = hasCycleByDualPointer(list);
        System.out.println(hasCycle);
    }
}
