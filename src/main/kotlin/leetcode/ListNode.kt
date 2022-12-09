package leetcode

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

/** 通过数组生成链表 */
fun arrayToListNode(array: IntArray): ListNode? {
    if (array.isEmpty()) return null
    val tempHead = ListNode(array[0])
    var pre: ListNode? = tempHead
    for (index in 1 until array.size) {
        pre?.next = ListNode(array[index])
        pre = pre?.next
    }
    return tempHead
}