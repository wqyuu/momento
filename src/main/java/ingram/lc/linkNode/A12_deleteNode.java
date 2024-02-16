package ingram.lc.linkNode;

/**
 * 237.
 * 有一个单链表的 head，我们想删除它其中的一个节点 node。
 * 给你一个需要删除的节点 node 。你将 无法访问 第一个节点 head
 *
 * 输入：head = [4,5,1,9], node = 5
 * 输出：[4,1,9]
 * 解释：指定链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9
 */
public class A12_deleteNode {

    /**
     * 在给定节点 node 的情况下，可以通过修改 node 的 next 指针的指向，
     * 删除 node 的下一个节点。但是题目要求删除 node，为了达到删除 node 的效果，
     * 只要在删除节点之前，将 node 的节点值修改为 node.next 的节点值即可
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
