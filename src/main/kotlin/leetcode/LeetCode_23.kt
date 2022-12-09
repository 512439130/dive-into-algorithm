package leetcode

import log.logListNode

fun main() {
    val lists = arrayOf(
        arrayToListNode(intArrayOf(1, 4, 5)),
        arrayToListNode(intArrayOf(1, 3, 4)),
        arrayToListNode(intArrayOf(2, 6))
    )
    logListNode("mergeKLists", mergeKLists(lists))
}

/**
 * 23. 合并K个升序链表
 * 暴力解法：通过遍历构造合并后的升序链表
 */
fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    val result = ListNode(-1)
    var tempHead: ListNode? = result
    while (true) {
        var minNode: ListNode? = null
        var minPoint = -1
        for (i in lists.indices) {
            val curNode: ListNode = lists[i] ?: continue
            // 记录最小结点
            if (minNode == null || curNode.`val` < minNode.`val`) {
                minNode = ListNode(curNode.`val`)
                minPoint = i
            }
        }
        // 没有比当前升序链表尾结点更小的值，结束循环
        if (minPoint == -1) break
        // 构造升序链表
        tempHead?.next = minNode
        tempHead = tempHead?.next
        // 待合并结点：有序链表的下一个结点
        lists[minPoint] = lists[minPoint]?.next
    }
    return result.next
}

/**
 * 23. 合并 K 个升序链表
 * 暴力解法：顺序合并
 */
fun mergeKLists2(lists: Array<ListNode?>): ListNode? {
    var result: ListNode? = null
    for (i in lists.indices) {
        result = mergeTwoLists(result, lists[i])
    }
    return result
}

fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 != null && list2 == null) return list1
    if (list2 != null && list1 == null) return list2
    val result = ListNode(-1)
    var tail: ListNode? = result
    var list1Temp = list1
    var list2Temp = list2
    while (list1Temp != null && list2Temp != null) {
        if (list1Temp.`val` < list2Temp.`val`) {
            tail?.next = list1Temp
            list1Temp = list1Temp.next
        } else {
            tail?.next = list2Temp
            list2Temp = list2Temp.next
        }
        tail = tail?.next
    }
    if (list1Temp != null) {
        tail?.next = list1Temp
    }
    if (list2Temp != null) {
        tail?.next = list2Temp
    }
    return result.next
}

/**
 * 23. 合并 K 个升序链表
 * 优化解法：分治法
 * 降低算法T(n) 和 S(n)
 */
fun mergeKLists3(lists: Array<ListNode?>): ListNode? {
    return merge(lists, 0, lists.size - 1)
}

// 模拟分治法合并过程
// lists[1->10, 2->3, 4->8, 5->6->7, 9->11, 12->15->16, 13->14->17]  size = 7
// middle = 3 mergeTwoLists(merge(0,3),merge(4,6))
//      merge(0,3) -> middle = 1 mergeTwoLists(merge(0,1),merge(2,3))
//          merge(0,1) -> middle = 0 mergeTwoLists(merge(0,0),merge(1,1)) = mergeTwoLists(lists[0],lists[1])
// result(0,3)-> mergeTwoLists(mergeTwoLists(lists[0],lists[1]),mergeTwoLists(lists[2],lists[3]))
//      merge(4,6) -> middle = 5 mergeTwoLists(merge(4,5),merge(6,6))
//          merge(4,5) -> middle = 4 mergeTwoLists(merge(4,4),merge(5,5)) = mergeTwoLists(lists[4],lists[5])
// result(4,6)-> mergeTwoLists(mergeTwoLists(lists[4],lists[5]),lists[6])
// result(0,6)-> mergeTwoLists(result(0,3),result(4,6))
fun merge(lists: Array<ListNode?>, left: Int, right: Int): ListNode? {
    return if (left > right) {
        null
    } else if (left == right) {
        lists[left]
    } else {
        val middle = left + (right - left).shr(1)
        mergeTwoLists(merge(lists, left, middle), merge(lists, middle + 1, right))
    }
}
