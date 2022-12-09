package leetcode

import log.log

fun main() {
    log(divide(10,3))
    log(divide(1000,23))
}

/**
 * 29. 两数相除
 */
fun divide(dividend: Int, divisor: Int): Int {
    if (dividend == 0) return 0
    if (dividend == divisor) return 1
    if (divisor == 1) return dividend
    // 溢出情况考虑
    if (dividend == Int.MIN_VALUE && divisor == -1) return Int.MAX_VALUE
    if (divisor == -1) return -dividend
    // 整数除法转化为多次整数减法 O(n)
    var i = 0
    var change = dividend
    if (dividend > 0 && divisor > 0) {
        while (change - divisor >= 0) {
            change -= divisor
            i++
        }
    }
    if (dividend < 0 && divisor < 0) {
        while (change - divisor <= 0) {
            change -= divisor
            // 考虑溢出情况
            i++
        }
    }
    if (dividend < 0 && divisor > 0) {
        while (change + divisor <= 0) {
            change += divisor
            i--
        }
    }
    if (dividend > 0 && divisor < 0) {
        while (change + divisor >= 0) {
            change += divisor
            i--
        }
    }
    return i
}