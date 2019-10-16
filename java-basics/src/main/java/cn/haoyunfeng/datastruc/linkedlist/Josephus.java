package cn.haoyunfeng.datastruc.linkedlist;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Description: 约瑟夫问题
 * @author: haoyunfeng
 * @Date: 2019/10/14 11:05 上午
 */
public class Josephus {

    public static void singleLinkedList(int mod){
        long startTime=System.currentTimeMillis();
        //耗时100000数据的情况
        //1385 arraylist  分配耗时9
        //7792 linkedlist 分配耗时21
        LinkedList list = new LinkedList();
        for(int i=1;i<=100000;i++){
            list.add(i);
        }
        long middleTime=System.currentTimeMillis();
        System.out.println("分配耗时："+(middleTime-startTime));
//        int index = list.indexOf(list.getFirst());
        int index = 0;
        while (list.size() > 1){
            index = (index+mod+1)%list.size();
            System.out.println("remove:"+new Integer(index)+";value:"+list.get(index));
            list.remove(index);
            index -- ;
        }
        System.out.println("*************************");
        for(Iterator iter = list.iterator(); iter.hasNext();) {
            System.out.println(iter.next());
        }
        long endTime=System.currentTimeMillis();
        System.out.println("耗时："+(endTime-startTime));
    }

    private static class SingleLinkedList{

        private int size =0;
        private Node first;
        private Node current;
        public SingleLinkedList(){

        }

        @Data
        private class Node{
            public Node (int data,Node next){
                this.data = data;
                this.next = next;
            }
            private int data;
            private Node next;
        }


        private void addfirst(int data){
            Node node = new Node(data,null);
            first = node;
            current = node;
            size++;
        }

        public void add(int data){
            if(size == 0){
                addfirst(data);
            }else {
                Node node = new Node(data,null);
                current.setNext(node);
                current = node;
                size ++;
            }
        }

        public Node findpre(int data){
            Node temp = first;
            if(temp.getData() == data){
                return temp;
            } else {
                temp = temp.next;
            }
            do{

            }while (temp.next !=null);
            return null;
        }

        public void remove(int data){
            Node pre = findpre(data);
            Node dataNode = pre.next;
//            pre.next =
        }
    }

    public static void main(String []args){
        singleLinkedList(2);
    }
}
