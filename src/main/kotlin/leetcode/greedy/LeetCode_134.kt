package main.kotlin.leetcode.greedy

import log.log

fun main() {
    val gasArray = intArrayOf(1, 2, 3, 4, 5)
    val costArray = intArrayOf(3, 4, 5, 1, 2)

    log(canCompleteCircuit(gasArray, costArray))
    log(canCompleteCircuit2(gasArray, costArray))

    val gasArray2 = intArrayOf(2, 3, 4)
    val costArray2 = intArrayOf(3, 4, 3)

    log(canCompleteCircuit(gasArray2, costArray2))
    log(canCompleteCircuit2(gasArray2, costArray2))
}

/**
 *
 * 134. 加油站
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[ i ] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[ i ] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 *
 * 示例 1:
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * 示例 2:
 * 输入: gas = [2,3,4], cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 *
 *
 * 提示:
 *
 * gas.length == n
 * cost.length == n
 * 1 <= n <= 105
 * 0 <= gas[ i ], cost[ i ] <= 104
 */

fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
    val length = gas.size
    // 遍历尝试所有起始点
    for (i in 0 until length) {
        // 记录剩余油量
        var rest = gas[i] - cost[i]
        // 取余操作控制遍历全部加油站
        var index: Int = (i + 1) % length
        // 模拟以 i 为起点环路行驶一周
        while (rest > 0 && index != i) {
            // 计算到达下个油站的剩余油量（累计油站加油量 - 下一油站消耗量）
            rest += gas[index] - cost[index]
            index = (index + 1) % length
        }
        // 完成绕环路行驶一周，返回油车起始点
        if (rest >= 0 && index == i) return i
    }
    return -1
}

fun canCompleteCircuit2(gas: IntArray, cost: IntArray): Int {
    // 贪心解法
    // 总油量减去总消耗 >= 0 时，说明一定可以环路行驶一周。
    // 局部最优：当前累加rest[j]的和curSum一旦小于0，起始位置至少要是j+1，因为从j开始一定不行。
    // 全局最优：找到可以跑一圈的起始位置。
    val length = gas.size
    var curSum = 0
    var totalSum = 0
    var index = 0
    for (i in gas.indices) {
        curSum += gas[i] - cost[i]
        totalSum += gas[i] - cost[i]
        if (curSum < 0) {
            index = (i + 1) % length
            curSum = 0
        }
    }
    if (totalSum < 0) return -1
    return index
}