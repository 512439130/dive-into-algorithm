package leetcode

import log.logArray

fun main() {
    val array = intArrayOf(2, 1, 4, 7, 4, 8, 3, 6, 4, 8)
    logArray(plusOne(array))
}

/** 66. 加一 */
fun plusOne(digits: IntArray): IntArray {
    var result = IntArray(digits.size + 1)
    // 模拟加法进位
    // 倒叙遍历
    var added = false
    for (i in digits.size - 1 downTo 0) {
        if (digits[i] == 9) {
            digits[i] = 0
            added = true
            continue
        } else {
            digits[i] = digits[i] + 1
            added = false
            break
        }
    }
    if (added) {
        result[0] = 1
        for (i in digits.indices) {
            result[i + 1] = digits[i]
        }
    } else {
        result = digits
    }
    return result
}

fun plusOne2(digits: IntArray): IntArray {
    // 模拟加法进位
    // 倒叙遍历
    for (i in digits.size - 1 downTo 0) {
        if (digits[i] != 9) {
            digits[i] = digits[i] + 1
            return digits
        }
        // 当前位非 9 则进位并赋值 0
        digits[i] = 0
    }
    // 所有位全 9 情况
    val result = IntArray(digits.size + 1)
    result[0] = 1
    for (i in digits.indices) {
        result[i + 1] = digits[i]
    }
    return result
}
