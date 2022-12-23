package leetcode.greedy

import log.log

fun main() {
    val array = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
    log(maxSubArray(array))
    log(maxSubArray2(array))
}

/** 53. 最大子数组和. */
fun maxSubArray(nums: IntArray): Int {
    var result = Int.MIN_VALUE
    var count: Int
    // 第一层 for 循环确定起始位置
    for (i in nums.indices) {
        // 下一个起始位置前先清空上一个和的结果
        count = 0
        // 第二层 for 循环确定结束位置
        for (j in i until nums.size) {
            count += nums[j]
            // 遍历求解最大的连续子数组和
            result = Math.max(count, result)
        }
    }
    return result
}

fun maxSubArray2(nums: IntArray): Int {
    // 贪心解法
    // T(n) = O(n)
    // S(n) = O(1)
    // 局部最优：当“连续和”为负数时直接舍弃（因为之前的和为负数时，连续和加上后续元素一定不是最大的），应该重置起始值，从下一个元素计算”连续和“。
    // 全局最优：选取数组中最大的连续和。
    var result = Int.MIN_VALUE
    var count = 0
    for (i in nums.indices) {
        count += nums[i]
        result = Math.max(count, result)
        // 重置起始值
        if (count < 0) count = 0
    }
    return result
}