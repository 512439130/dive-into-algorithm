package leetcode

import log.log

fun main() {
    log(isPalindrome(1534236469)) // 1534236469
    log(isPalindrome2(-2147483648)) //-2147483648
}

/** 9. 回文数(转换字符串) */
fun isPalindrome(x: Int): Boolean {
    val numStr = x.toString()
    var start = 0
    var end = numStr.length - 1
    while (start < end) {
        if (numStr[start++] != numStr[end--]) return false
    }
    return true
}

/** 9. 回文数 非字符串存储，反转整个数字比较 */
fun isPalindrome2(x: Int): Boolean {
    var end: Int
    var last = x
    var result = 0
    while (last != 0) {
        end = last % 10
        last /= 10
        if ((result > Int.MAX_VALUE / 10) || ((result == Int.MAX_VALUE / 10) && (last > Int.MAX_VALUE % 10))) return false
        if ((result < Int.MIN_VALUE / 10) || ((result == Int.MIN_VALUE / 10) && (last < Int.MIN_VALUE % 10))) return false
        result = result * 10 + end
    }
    return x == result
}

/** 9. 回文数 非字符串存储，反转一半数字比较 */
fun isPalindrome3(x: Int): Boolean {
    // 非回文直接返回
    if (x < 0 || (x != 0 && x % 10 == 0)) return false
    var last = x
    var result = 0
    // 反转一半数字
    while (last > result) {
        // 当前末尾数字：end = last % 10
        result = result * 10 + (last % 10)
        last /= 10
    }
    // 数字长度是奇数时，中位数不影响回文结果，通过 /10 剔除，x == result / 10 判断即可
    // 数字长度是偶数时，x == result 判断即可
    return (last == result) || (last == result / 10)
}