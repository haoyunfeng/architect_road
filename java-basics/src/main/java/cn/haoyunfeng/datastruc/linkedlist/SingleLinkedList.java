package cn.haoyunfeng.datastruc.linkedlist;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 单链表
 * @author: haoyunfeng
 * @Date: 2019/10/16 10:08 上午
 */
public class SingleLinkedList{
    @Getter
    @Setter
    private ListNode head;
    @Setter
    private ListNode current;
    @Getter
    private int size;
    private void addFirst(int val){
        head = new ListNode(val);
        current = head;
        size ++;
    }
    public void add(int val){
        if (head == null){
            addFirst(val);
        }else {
            ListNode node = new ListNode(val);
            current.next = node;
            current = node;
            size++;
        }
    }

    public static class ListNode {
        int val;

        @Getter
        @Setter
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
