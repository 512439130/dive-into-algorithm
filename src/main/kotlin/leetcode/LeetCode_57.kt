package leetcode

import log.log

fun main() {
    val intervals = arrayOf(
        intArrayOf(1, 5),
        intArrayOf(6, 8),
    )
    val newInterval = intArrayOf(0, 9)
    log(insert(intervals, newInterval).contentDeepToString())
}

/**
 * 57. 插入区间
 */
fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    val result = mutableListOf<IntArray>()
    if (intervals.isEmpty()) {
        result.add(newInterval)
        return result.toTypedArray()
    }
    // 模拟插入过程
    var index = 0
    var newFlag = false
    while (index < intervals.size) {
        if (intervals[index][1] >= newInterval[0] && newInterval[1] >= intervals[index][0] && !newFlag) {
            newFlag = true
            val left = Math.min(intervals[index][0], newInterval[0])
            val right = Math.max(intervals[index][1], newInterval[1])
            result.add(intArrayOf(left, right))
            // 连续区间遍历，寻找下一个区间
            while (intervals[index][1] < right) {
                if (index == intervals.size - 1) break
                index++
            }
            // 合并区间，更新右边界
            if (right >= intervals[index][0]) {
                if (right < intervals[index][1]) {
                    result[result.lastIndex][1] = intervals[index][1]
                }
            } else {
                result.add(intervals[index])
            }
        } else {
            result.add(intervals[index])
        }
        index++
    }
    // 未找到可以合并的数组，需要补充合并
    if (!newFlag) {
        for (i in result.indices) {
            if (result[i][0] > newInterval[1]) {
                // 头部合并
                result.add(0, newInterval)
                break
            }
            if (i == result.size - 1 || (result[i][1] < newInterval[0] && result[i + 1][0] > newInterval[1])) {
                result.add(i + 1, newInterval)
                break
            }
        }
    }
    return result.toTypedArray()
}

fun insert2(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    val result = mutableListOf<IntArray>()
    val length = intervals.size
    var index = 0

    // 判断左边区间不重合
    while (index < length && intervals[index][1] < newInterval[0]) {
        result.add(intervals[index++])
    }

    // 处理中间重合区域
    while (index < length && intervals[index][0] <= newInterval[1]) {
        // 记录更新重合区间的最小值
        newInterval[0] = Math.min(intervals[index][0], newInterval[0])
        // 记录更新重合区间的最大值
        newInterval[1] = Math.max(intervals[index][1], newInterval[1])
        index++
    }
    // 保存合并完成的重合区间
    result.add(newInterval)

    // 判断右边区间不重合
    while (index < length && intervals[index][0] > newInterval[1]) {
        result.add(intervals[index++])
    }
    return result.toTypedArray()
}