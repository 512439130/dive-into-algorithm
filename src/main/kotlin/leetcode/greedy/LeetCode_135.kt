package main.kotlin.leetcode.greedy

import log.log

fun main() {
    val array = intArrayOf(1, 0, 2)
    log(candy(array))
}

/**
 * 135. 分发糖果
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 * 示例 1：
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 *
 * 示例 2：
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 * 提示：
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[ i ] <= 2 * 104
 */
fun candy(ratings: IntArray): Int {
    var count = 0
    val candyVec = IntArray(ratings.size, init = { 0 })
    candyVec[0] = 1
    // 贪心解法:
    // 相邻孩子会得到更多糖果，分为 2 次遍历:
    // 1. 从前向后遍历，右边孩子评分大于左边孩子规则分配糖果
    // 2. 从后向前遍历，左边孩子评分大于右边孩子规则分配糖果
    // 2 次遍历局部最优推出整体最优
    for (i in 1 until ratings.size) {
        if (ratings[i] > ratings[i - 1]) candyVec[i] = candyVec[i - 1] + 1 else candyVec[i] = 1
    }
    for (i in ratings.size - 2 downTo 0) {
        // 保证第 i 个孩子糖果数量既大于左边，也大于右边
        if (ratings[i] > ratings[i + 1]) candyVec[i] = Math.max(candyVec[i], candyVec[i + 1] + 1)
    }
    // 统计最小糖果数量
    for (i in candyVec.indices) {
        count += candyVec[i]
    }
    return count
}