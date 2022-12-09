package leetcode

import log.log

fun main() {
    val result = lengthOfLastWord("   fly me   to   the moon  ")
    log(result)
}

/** 58. 最后一个单词的长度 */
fun lengthOfLastWord(s: String): Int {
    val charArray = s.toCharArray()
    var length = 0
    for (i in s.length - 1 downTo 0) {
        // 消除末端单词前多余空格
        if (charArray[i] == ' ' && length == 0) continue
        // 末尾单词后的第一个空格字符为结束标识
        if (charArray[i] == ' ') return length
        // 从非空格字符位置开始累加计算单词长度
        length++
    }
    return length
}