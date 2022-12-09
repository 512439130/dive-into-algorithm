package leetcode

import log.logDoubleIntList
import java.util.*

fun main() {
//    val array = intArrayOf(-1,2,1,-4)
//    val array = intArrayOf(1,0,4,3,4,5,4,1,2,3,4,2,-10,4,3,8,12,13)
//    val array = intArrayOf(1, 2, 3, 4)
    val array = intArrayOf(-1, 0, 1, 1, 55)
    logDoubleIntList(threeSum(array))
}

/** 15. 三数之和 */
fun threeSum(nums: IntArray): List<List<Int>> {
    // 双指针法
    val result = mutableListOf<List<Int>>()
    if (nums.size < 3) return result
    // 先对数组进行排序
    Arrays.sort(nums)

    // 左指针
    var left: Int
    // 右指针
    var right: Int
    for (a in nums.indices) {
        // 剪枝1：已排序，a > 0 ==>> a + b + c > 0
        if (nums[a] > 0) return result
        // 特殊情况处理 1：不重复的三元组，过滤重复元素
        if (a > 0 && nums[a] == nums[a - 1]) continue

        // 更新左指针
        left = a + 1
        // 归位右指针
        right = nums.size - 1
        while (right > left) {
            val sum = nums[a] + nums[left] + nums[right]
            when {
                sum < 0 -> {
                    left++
                }
                sum > 0 -> {
                    right--
                }
                else -> {
                    result.add(listOf(nums[a], nums[left], nums[right]))
                    // 特殊情况处理 2：不重复的三元组，过滤重复元素
                    while (right > left && nums[right] == nums[right - 1]) right--
                    // 特殊情况处理 3：不重复的三元组，过滤重复元素
                    while (right > left && nums[left] == nums[left + 1]) left++
                    left++
                    right--
                }
            }
        }
    }
    return result
}