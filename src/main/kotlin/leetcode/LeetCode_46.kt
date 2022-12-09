package leetcode

import log.logDoubleIntList

fun main() {
    val array = intArrayOf(1, 2, 3)
    logDoubleIntList(permute(array))
}

/** 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * */
fun permute(nums: IntArray): List<List<Int>> {
    val result = arrayListOf<ArrayList<Int>>()
    val temp = arrayListOf<Int>()
    // 回溯法
    backTrackingPermute(result, temp, nums)
    return result
}

fun backTrackingPermute(result: ArrayList<ArrayList<Int>>, temp: ArrayList<Int>, nums: IntArray) {
    // 递归结束条件
    if (temp.size == nums.size) {
        result.add(ArrayList(temp))
        return
    }

    for (i in nums.indices) {
        // 过滤重复元素
        if (temp.contains(nums[i])) continue
        temp.add(nums[i])
        backTrackingPermute(result, temp, nums)
        temp.removeAt(temp.size - 1)
    }
}
