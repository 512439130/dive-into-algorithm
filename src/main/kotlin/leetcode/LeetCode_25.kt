package leetcode

import log.logListNode

fun main() {
    val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    val listNode = arrayToListNode(array)
    logListNode("result", reverseKGroup(listNode, 2))
}

/**
 * 25. K 个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 */
fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
    logListNode("head", head)
    if (k <= 1) return head
    // 链表移动指针
    var headPoint: ListNode? = head
    var resultPoint: ListNode? = ListNode(-1)
    var reversePoint: ListNode? = ListNode(-1)
    var reverseNodeHead: ListNode? = reversePoint
    val resultHead: ListNode? = resultPoint
    var index = 0
    while (headPoint != null) {
        index++
        reversePoint?.next = ListNode(headPoint.`val`) // 当前结点
        reversePoint = reversePoint?.next
        if (index % k == 0) {
            // 反转部分链表
            // 前置节点
            var pre: ListNode? = null
            // 当前节点
            var cur: ListNode? = reverseNodeHead?.next
            // 反转后的末尾结点
            val end: ListNode? = cur
            // 缓存节点
            var temp: ListNode?
            while (cur != null) {
                temp = cur.next
                cur.next = pre
                pre = cur
                cur = temp
            }
            logListNode("pre-part", pre)
            // pre 为反转后的头结点
            resultPoint?.next = pre
            // 更新链表指针位置为反转后的末尾结点
            resultPoint = end
            // 更新反转链表的头结点
            reversePoint = ListNode(-1)
            reverseNodeHead = reversePoint
        }
        headPoint = headPoint.next
    }
    logListNode("reverseNodeTemp", reverseNodeHead)
    // 剩余未反转的链表直接拼接在末尾
    while (reverseNodeHead != null) {
        resultPoint?.next = reverseNodeHead.next
        resultPoint = resultPoint?.next
        reverseNodeHead = reverseNodeHead.next
    }
    logListNode("resultHead", resultHead)
    return resultHead?.next
}