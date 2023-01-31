package leetcode.greedy

import log.log

fun main() {
    val array = intArrayOf(7, 1, 5, 3, 6, 4)
    log(maxProfit(array))
}

fun maxProfit(prices: IntArray): Int {
    var result = 0
    for (i in 1 until prices.size) {
        // 每日局部最优结果-正利润：current > 0
        // 局部最优：每日最大利润
        // 只记录正利润，累加求正利润总和
        val current = prices[i] - prices[i - 1]
        if (current > 0) {
            result += current
        }
    }
    return result
}
