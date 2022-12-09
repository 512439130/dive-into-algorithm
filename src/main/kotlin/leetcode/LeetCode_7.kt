package leetcode

import log.log

fun main() {
    log(reverse(1464847412)) // 1534236469  -2147483648
}

/** 7. 整数反转 */
fun reverse(x: Int): Int {
    val max = Int.MAX_VALUE // 2147483647
    val min = Int.MIN_VALUE // -2147483648
    if (x > max || x < min) return 0
    // 末尾数字
    var end = 0
    // 剩余前半部分
    var last = x
    var result = 0
    while (last != 0) {
        end = last % 10
        last = last / 10
        if (result > max / 10 || (result == max / 10 && last > 7)) return 0
        if (result < min / 10 || (result == min / 10 && last < -8)) return 0
        // 逐渐构造新数字.
        result = result * 10 + end
//        print("result: $result last: $last max: $max min: $min")
    }
    return result
}