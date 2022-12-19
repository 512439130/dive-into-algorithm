package leetcode

import log.log

fun main() {
    val array = intArrayOf(7, 1, 5, 3, 6, 4)
    log(maxProfit(array))
    val array2 = intArrayOf(7, 1, 8, 2, 6, 11)
    log(maxProfit2(array2))
    val array3 = intArrayOf(7, 1, 8, 2, 6, 11)
    log(maxProfit3(array3))
}

/** 121. 买卖股票的最佳时机 */
fun maxProfit(prices: IntArray): Int {
    var result = 0
    // 暴力解法
    for (i in prices.indices) {
        for (j in i + 1 until prices.size) {
            result = Math.max(prices[j] - prices[i], result)
        }
    }
    return result
}

fun maxProfit2(prices: IntArray): Int {
    if (prices.isEmpty()) return 0
    // 一次循环遍历不断计算最大收益
    // 买入最低点
    var minBuy = 0
    // 最大收益
    var maxSell = 0
    for (i in prices.indices) {
        if (i == 0) {
            minBuy = prices[i]
            continue
        }
        // 计算到当天为止最低买入点和最大收益
        val cur = prices[i] - minBuy
        when {
            prices[i] < minBuy -> {
                minBuy = prices[i]
            }
            cur > maxSell -> {
                maxSell = cur
            }
        }
    }
    return maxSell
}

fun maxProfit3(prices: IntArray): Int {
    if (prices.isEmpty()) return 0
    val size = prices.size
    // 动态规划解法
    // dp: 前 i 天的最大收益
    val dp = IntArray(size = size, init = { 0 })
    var min = 0
    for (i in prices.indices) {
        if (i == 0) {
            min = prices[0]
            continue
        }
        min = Math.min(prices[i], min)
        dp[i] = Math.max(prices[i] - min, dp[i - 1])
    }
    return dp[size - 1]
}