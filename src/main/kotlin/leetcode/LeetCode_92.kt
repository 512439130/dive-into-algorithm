package leetcode

import log.logListNode

fun main() {
    val array = intArrayOf(1, 2, 3, 4, 5)
    val listNode = arrayToListNode(array)
    logListNode("input", listNode)
    val result = reverseBetween(listNode, 2, 4)
    logListNode("result", result)
}

fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
    // 建立结果头结点，防止链表操作中头结点改变
    val result = ListNode(-1)
    result.next = head
    // 分为 3 部分构造链表
    // 反转链表头部节点
    var startNode: ListNode? = result
    // 反转链表末尾节点
    var reverseRightNode: ListNode? = result
    var count = 1
    while (true) {
        // startNode 移动到反转的首个位置(left)
        if (count < left) {
            startNode = startNode?.next
        }
        // reverseRightNode 移动到反转的末尾位置(right)
        if (count < right + 1) {
            reverseRightNode = reverseRightNode?.next
        } else {
            break
        }
        count++
    }
    // 截取中间反转部分链表
    val reverseLeftNode: ListNode? = startNode?.next
    val endNode = reverseRightNode?.next
    // 反转操作前先断开前后部分链
    startNode?.next = null
    reverseRightNode?.next = null
    // 反转中间部分链表
    reverseLinkedList(reverseLeftNode)
    // 反转后 reverseRightNode 为 left, reverseLeftNode 为 right
    // 把 前中后 部分链表拼接起来
    startNode?.next = reverseRightNode
    reverseLeftNode?.next = endNode
    return result.next
}

/** 反转链表 */
fun reverseLinkedList(head: ListNode?) {
    var pre: ListNode? = null
    var cur: ListNode? = head
    // 从头向尾反转
    while (cur != null) {
        val next = cur.next
        cur.next = pre
        // 移动 pre 和 cur，反转下一个节点
        pre = cur
        cur = next
    }
}