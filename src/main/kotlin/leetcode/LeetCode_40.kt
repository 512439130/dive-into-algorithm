package leetcode

import log.logDoubleIntList

fun main() {
    val array = intArrayOf(1, 2, 3, 4, 5)
    val target = 4
    logDoubleIntList(combinationSum2(array, target))
}

/** 40. 组合总和 II. */
fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
    val result = arrayListOf<ArrayList<Int>>()
    val temp = arrayListOf<Int>()
    val used = Array(candidates.size) { false }
    candidates.sort()
    backTrackingCombinationSum2(candidates, result, temp, used, target, 0, 0)
    return result
}

fun backTrackingCombinationSum2(
    candidates: IntArray,
    result: ArrayList<ArrayList<Int>>,
    temp: ArrayList<Int>,
    used: Array<Boolean>,
    target: Int,
    sum: Int,
    startIndex: Int
) {
    var sumValue = sum
    if (sum > target) {
        return
    }
    if (sum == target) {
        result.add(ArrayList(temp))
        return
    }
    for (index in startIndex until candidates.size) {
        if (target - candidates[index] < 0) break
        // used[index - 1] = true：同一树枝 candidates[i - 1] 使用过
        // used[index - 1] = false：同一树层 candidates[i - 1] 使用过
        // 要对同一树层使用过的元素进行跳过
        if (index > 0 && candidates[index] == candidates[index - 1] && !used[index - 1]) continue
        sumValue += candidates[index]
        temp.add(candidates[index])
        used[index] = true
        backTrackingCombinationSum2(candidates, result, temp, used, target, sumValue, index + 1)
        used[index] = false
        sumValue -= candidates[index]
        temp.removeAt(temp.size - 1)
        Int
    }
}