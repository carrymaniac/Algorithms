package cn.code.LeetCode.char1;

import java.awt.*;

/**
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 */

/**
 * 思路:依旧是双指针,其中一个一次移动一个节点,另一个移动两个节点,若该链表为循环链表,则最后二者必将相遇
 */
public class hasCycle {
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode l1 = head;
        ListNode l2 = head.next;
        while (l1!=null && l2!=null && l2.next!=null){
            if(l1==l2){
                return true;
            }else {
                l1 = l1.next;
                l2 = l2.next.next;
            }
        }
        return false;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
