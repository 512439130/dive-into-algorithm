package leetcode

import log.logListNode

fun main() {
    val listNode = arrayToListNode(intArrayOf(4, 3, 5, 6, 7, 8))
    logListNode("insertionSortList", insertionSortList(listNode))
}

/** 147. 对链表进行插入排序 */
fun insertionSortList(head: ListNode?): ListNode? {
    if (head == null) return head
    // 创建临时头结点
    val tempHead = ListNode(-9999)
    tempHead.next = head
    // 记录已排序链表的最后一个结点，初始化为 head
    var lastSorted: ListNode? = head
    // 待插入元素，初始化为 head.next
    var cur: ListNode? = head.next
    while (cur != null) {
        if ((lastSorted?.`val` ?: 0) <= cur.`val`) {
            // 插入元素 大于等于 已排序链表的最后一个结点，满足顺序规则，不修改链表结点关系
            // lastSorted 遍历到下一个节点
            lastSorted = lastSorted?.next
            // cur 遍历到下一个结点
            cur = cur.next
        } else {
            // pre 为 cur 的前置结点
            var pre: ListNode? = tempHead
            // 从链表的头结点开始遍历，寻找插入 cur 的位置 pre， pre -> cur -> (old)pre.next
            while ((pre?.next?.`val` ?: 0) <= cur.`val`) {
                pre = pre?.next
            }
            // 修改末尾指针的链表关系 lastSorted -> cur ->  (old)cur.next  ==>> lastSorted -> (old)cur.next（cur从链表关系中取出）
            lastSorted?.next = cur.next
            // cur 指向 pre 的 next
            cur.next = pre?.next
            // pre 指向 cur 形成 pre -> cur -> pre.next(未插入 cur 前的 pre.next)
            pre?.next = cur
            // 记录下一个待插入元素 cur
            cur = lastSorted?.next
        }

    }
    return tempHead.next
}