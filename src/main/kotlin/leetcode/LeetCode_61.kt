package leetcode

import log.logListNode

fun main() {
    val array = intArrayOf(1, 2, 3, 4, 5)
    val listNode = arrayToListNode(array)
    logListNode("rotateRight",rotateRight(listNode, 2))
}

/** 61. 旋转链表 */
fun rotateRight(head: ListNode?, k: Int): ListNode? {
    if (k == 0 || head == null || head.next == null) {
        return head
    }
    // 1 -> 2 -> 3 -> 4 -> 5
    // length = 5 cur = 5 cur.next = null
    //
    // position = (5 - 3) % 5 = 3
    //
    // cur?.next = head
    // cur = 5 -> 1 -> 2 -> 3 -> 4 -> 5 -> 1 -> 2 -> 3 -> ...
    //
    // while (position-- > 0) cur = cur.next
    // 更新头结点 为 旋转结果位置头结点的上一个结点 3
    // 3 -> 4 -> 5 -> 1 -> 2 -> 3 -> ...
    //
    // result = 3.next
    // result = 4 -> 5 -> 1 -> 2 -> 3 -> ...
    //
    // 3.next = null 断开所有 3 结点的next
    // result =  4 -> 5 -> 1 -> 2 -> 3 -> null
    // 1. 计算链表长度 并 更新头结点为原链表的尾结点
    var length = 0
    var cur = head
    while (true) {
        length++
        if (cur?.next == null) break
        cur = cur.next
    }

    // 查找新头结点的位置
    var position = (length - k) % length
    // 移动原链表长度相当于不变，返回 head
    if (position == length) return head
    // 2. 构造环形链表（尾结点指向头结点）
    cur?.next = head

    // 3. 更新链表头结点
    while (position > 0) {
        cur = cur?.next
        position--
    }
    val result = cur?.next
    cur?.next = null
    logListNode("cur", result)
    return result
}
