package main.kotlin.leetcode.greedy

import log.log

fun main() {
    log(largestSumAfterKNegations(intArrayOf(3, -1, 0, 2), 3))
    log(largestSumAfterKNegations(intArrayOf(4, -2, 3, 6, 1), 2))
    log(largestSumAfterKNegations(intArrayOf(-4, -2, 3, 5, -1), 4))
}

/**
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * 选择某个下标 i并将 nums[ i ] 替换为 -nums[ i ] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 *
 * 示例 1：
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 *
 * 示例 2：
 * 输入：nums = [3,-1,0,2], k = 3
 * 输出：6
 * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
 *
 * 示例 3：
 * 输入：nums = [2,-3,-1,5,-4], k = 2
 * 输出：13
 * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
 *
 *
 * 提示：
 * 1 <= nums.length <= 104
 * -100 <= nums[ i ] <= 100
 * 1 <= k <= 104
 *
 */
fun largestSumAfterKNegations(nums: IntArray, k: Int): Int {
    var kNum = k
    var sum = 0
    // 贪心算法 -> 局部最优推出整体最优
    // 局部最优: 绝对值大的负数变为负数，当前数组最大
    // 整体最优: 转变后整个数组求和达到最大
    // 1. 数组按照绝对值大小排序(由大到小)
    nums.sortedByDescending { number ->
        Math.abs(number)
    }
    // 2. 根据 k 值，逐个将数组中的负数转变
    for (i in nums.indices) {
        if (kNum <= 0) return sum
        if (nums[i] < 0) {
            nums[i] = -nums[i]
            kNum--
        }
    }
    // 3. 如果此时 k > 0，重复转变最小的数，直到 k <= 0
    if (kNum % 2 == 1) nums[nums.size - 1] = -nums[nums.size - 1]
    // 4. 累加结果
    for (i in nums.indices) sum += nums[i]
    return sum
}