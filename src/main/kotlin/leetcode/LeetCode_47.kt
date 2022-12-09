package leetcode

import log.logDoubleIntList

fun main() {
    val array = intArrayOf(1, 2, 1)
    logDoubleIntList(permuteUnique(array))
}


/** 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * */
fun permuteUnique(nums: IntArray): List<List<Int>> {
    val result = arrayListOf<ArrayList<Int>>()
    val temp = arrayListOf<Int>()
    val used = Array(nums.size) { false }
    // 先对元素进行排序，通过相邻的节点来判断是否重复使用
    nums.sort()
    backTrackingPermuteUnique(result, temp, used, nums)
    return result
}

fun backTrackingPermuteUnique(
    result: ArrayList<ArrayList<Int>>,
    temp: ArrayList<Int>,
    used: Array<Boolean>,
    nums: IntArray
) {
    // 递归结束条件
    if (temp.size == nums.size) {
        result.add(ArrayList(temp))
        return
    }
    for (i in nums.indices) {
        // used[index - 1] = true：同一树枝 nums[i - 1] 使用过
        // used[index - 1] = false：同一树层 nums[i - 1] 使用过
        // 要对同一树层使用过的元素进行跳过
        if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue
        temp.add(nums[i])
        used[i] = true
        backTrackingPermuteUnique(result, temp, used, nums)
        used[i] = false
        temp.removeAt(temp.size - 1)
    }
}
