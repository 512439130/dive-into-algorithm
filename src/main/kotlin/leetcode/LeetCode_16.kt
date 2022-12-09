package leetcode

import log.log

fun main() {
//    val array = intArrayOf(-1,2,1,-4)
//    val array = intArrayOf(1,0,4,3,4,5,4,1,2,3,4,2,-10,4,3,8,12,13)
//    val array = intArrayOf(1, 2, 3, 4)

    val array = intArrayOf(-1, 0, 1, 1, 55)
    log(threeSumClosest(array, -1))
}

/** 16. 最接近的三数之和 */
fun threeSumClosest(nums: IntArray, target: Int): Int {
    var near = Int.MAX_VALUE
    // 排序 + 双指针
    nums.sort()
    var left: Int
    var right: Int
    for (a in nums.indices) {
        // 剪枝1：优化重复结果
        if (a > 0 && nums[a] == nums[a - 1]) {
            continue
        }
        // 重置双指针位置
        left = a + 1
        right = nums.size - 1
        while (right > left) {
            val sum = nums[a] + nums[left] + nums[right]
            // 寻找 target 最接近的数
            // Int溢出：Int.MAX_VALUE - 1 = Int.MIN_VALUE
            if ((Math.abs(near - target) > Math.abs(sum - target)) || (near == Int.MAX_VALUE && target == -1)) {
                near = sum
            }
            when {
                sum > target -> {
                    right--
                    // 剪枝2：优化重复结果
                    while (right > left && nums[right] == nums[right + 1]) right--
                }
                sum < target -> {
                    left++
                    // 剪枝3：优化重复结果
                    while (right > left && nums[left] == nums[left - 1]) left++
                }
                else -> return target
            }
        }
    }
    return near
}