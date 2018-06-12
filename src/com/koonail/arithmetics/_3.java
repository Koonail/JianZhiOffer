package com.koonail.arithmetics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WEN KANG
 * @Time 2018年4月13日 星期五
 */
public class _3 {
    /**
     * 题目：输入一个链表，从尾到头打印链表每个节点的值。
     * @param args
     *    public class ListNode {
     *        int val;
     *        ListNode next = null;
     *
     *        ListNode(int val) {
     *            this.val = val;
     *        }
     *    }
     */
    public static void main(String[] args) {
        System.out.println();
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> rs = new ArrayList<Integer>();
        rs = getResData(listNode,rs);
        return rs;
    }

    public ArrayList<Integer> getResData(ListNode listNode,ArrayList<Integer> rs){
        if(listNode != null){
            getResData(listNode.next,rs);
            rs.add(listNode.val);
            return rs;
        }
        return rs;
    }

    class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val){
            this.val = val;
        }
    }
}
