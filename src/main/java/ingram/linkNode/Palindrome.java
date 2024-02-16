package ingram.linkNode;

import java.util.Stack;

/**
 * @Author qywu11
 * @Date 2022/7/25 9:33
 * @Version 1.0
 * 回文链表
 * 写熟
 */
public class Palindrome {

    public static class Node{
        public int value;
        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    public static boolean isPalindrome1(Node head){
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null){
            if(head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean isPalindrome2(Node head){
        return true;
    }

    public static boolean isPalindrome3(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n1.next != null && n2.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }// 1 2 3 2 1    n1 = 321  n2= 1
        n2 = n1.next;
        n1.next = null; // 取中点位置
        Node n3 = null;
        // 翻转n2 赋值给n1
        while (n2 != null){
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;
        n2 = head;
        boolean res = true;
        while (n1 != null && n2 != null){
            if(n1.value != n2.value){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;

    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(2);
        Node n5 = new Node(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        boolean res = isPalindrome4(n1);
        System.out.println(res);

    }

    static boolean isPalindrome4(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node n1  = head;
        Node n2 = head;
        while (n1.next != null && n2.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null){
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;
        n2 = head;
        boolean res = true;
        while (n1.next != null && n2.next != null){
            if(n1.value != n2.value){
                res = false;
                break;
            }

            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }

        return res;
    }
}
