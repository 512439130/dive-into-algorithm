package leetcode

import log.log
import log.logDoubleIntList

fun main() {
    val array = intArrayOf(1, 2, 3)
    logDoubleIntList(subsets(array))
}

/** 78. 子集 */
fun subsets(nums: IntArray): List<List<Int>> {
    val list = arrayListOf<ArrayList<Int>>()
    val sonList = arrayListOf<Int>()
    // 数组的子集有多种情况，按子集数据长度划分为（0,1,2...size）
    for (length in 0..nums.size) {
        // 每趟回溯求得长度为 length 的所有子集
        backTracking(0, length, nums, sonList, list)
        log("subsets: $list")
    }
    return list
}

/**
 * start: 子集中数据遍历起始位置
 * length: 当前需要构造子集的长度
 * son: 子集
 * list: 结果
 */
fun backTracking(
    start: Int,
    length: Int,
    nums: IntArray,
    son: ArrayList<Int>,
    list: ArrayList<ArrayList<Int>>
) {
    // 结束条件：length = 0 代表当前子集构造完成，保存结果
    if (length == 0) {
        list.add(ArrayList(son))
        return
    }
    // start: 子集中数据遍历起始位置
    for (i in start until nums.size) {
        // 添加
        son.add(nums[i])
        backTracking(i + 1, length - 1, nums, son, list)
        // 回溯（回退操作）
        son.removeAt(son.lastIndex)
    }
}