package log

import leetcode.ListNode

private const val TAG = "print ==>>"
fun log(message: String) {
    println("$TAG $message")
}

fun log(message: Int) {
    println("$TAG $message")
}

fun log(message: Long) {
    println("$TAG $message")
}

fun log(message: Boolean) {
    println("$TAG $message")
}

fun log(message: Byte) {
    println("$TAG $message")
}


fun logArray(array: IntArray) {
    println("$TAG ${array.contentToString()}")
}

fun logArray(array: CharArray) {
    println("$TAG ${array.contentToString()}")
}

fun logDeepArray(deepArray: Array<IntArray>) {
    println("$TAG ${deepArray.contentDeepToString()}")
}

fun logIntList(list: List<Int>) {
    println("$TAG $list")
}

fun logStringList(list: List<String>) {
    println("$TAG $list")
}

fun logDoubleIntList(doubleList: List<List<Int>>) {
    println("$TAG $doubleList")
}

fun logDoubleStringList(doubleList: List<List<String>>) {
    println("$TAG $doubleList")
}

fun logListNode(tag: String? = null, head: ListNode?) {
    var node: ListNode? = head
    if (node == null) println("head == null")
    System.out.print("result【${tag ?: "leetcode.ListNode"}】 ==>> ")
    while (node != null) {
        System.out.print(node.`val`)
        node = node.next
        if (node != null) {
            System.out.print(" -> ")
        }
    }
    println()
}