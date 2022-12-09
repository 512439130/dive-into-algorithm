package leetcode

import log.logDoubleIntList

fun main() {
    val array = intArrayOf(1, 2, 3, 4, 5)
    val target = 4
    logDoubleIntList(combinationSum(array, target))
}

/** 39. 组合总和. */
fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    val result = arrayListOf<ArrayList<Int>>()
    val temp = arrayListOf<Int>()
    backTrackingCombinationSum(candidates, result, temp, target, 0, 0)
    return result
}

fun backTrackingCombinationSum(
    candidates: IntArray,
    result: ArrayList<ArrayList<Int>>,
    temp: ArrayList<Int>,
    target: Int,
    sum: Int,
    startIndex: Int
) {
    var sumValue = sum
    if (sumValue > target) {
        return
    }
    if (sumValue == target) {
        result.add(ArrayList(temp))
        return
    }
    for (index in startIndex until candidates.size) {
        sumValue += candidates[index]
        temp.add(candidates[index])
        backTrackingCombinationSum(candidates, result, temp, target, sumValue, index)
        sumValue -= candidates[index]
        temp.removeAt(temp.size - 1)
    }
}