package leetcode

import log.logDoubleIntList

fun main() {
    logDoubleIntList(combinationSum(3, 9))
}

/** 216. 组合总和 III */
fun combinationSum(k: Int, n: Int): List<List<Int>> {
    val result = arrayListOf<List<Int>>()
    val temp = arrayListOf<Int>()
    // 回溯法
    backTrackingCombinationSum(result, temp, n, k, 1, 9)
    return result
}

fun backTrackingCombinationSum(
    result: ArrayList<List<Int>>,
    temp: ArrayList<Int>,
    n: Int,
    k: Int,
    startIndex: Int,
    end: Int
) {
    if (temp.size == k) {
        var sum = 0
        for (i in temp.indices) {
            sum += temp[i]
        }
        // 筛选相加之和为 n 的 temp
        if (sum == n) {
            result.add(ArrayList(temp))
        }
        return
    }
    // maxStartIndex = end - (k - temp.size) + 1
    // (end = 9) ==>> (9 - (k - temp.size) + 1)
    for (i in startIndex..(9 - (k - temp.size) + 1)) {
        temp.add(i)
        backTrackingCombinationSum(result, temp, n, k, i + 1, end)
        temp.removeAt(temp.size - 1)
    }
}
