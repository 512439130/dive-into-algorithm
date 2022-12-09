package leetcode

import log.log

fun main() {
//    val array = intArrayOf(1,2,0)
    val array = intArrayOf(4, 5, -1, 2, 1, 6)
    log(firstMissingPositive(array))
}

// 原地调整数组元素位置: 数值放置数组对应下标位置, 数组下标作为 每个数字的 key 将数组作为 哈希表)
// 例如：数值 4 应该放在索引 3 的位置上

// 第一趟循环 i = 0
// 4 5 -1 2 1 6  交换 4 和 2
// 2 5 -1 4 1 6  交换 2 和 5
// 5 2 -1 4 1 6  交换 5 和 1
// 1 2 -1 4 5 6  不满足条件(nums[nums[i] - 1] != nums[i]) 本次循环结束, 数组下标 0 位置元素成功调整

// 第二趟循环 i = 1
// 1 2 -1 4 5 6 不满足条件 nums[nums[i] - 1] != nums[i] 本次循环结束, 数组下标 1 位置元素成功调整
// 第二趟循环 i = 2
// 1 2 -1 4 5 6 不满足条件 nums[i] > 0 本次循环结束, 数组下标 2 位置元素成功调整
// 第二趟循环 i = 3
// 1 2 -1 4 5 6 不满足条件 nums[i] > 0 本次循环结束, 数组下标 3 位置元素成功调整
// 第二趟循环 i = 4
// 1 2 -1 4 5 6 不满足条件 nums[i] > 0 本次循环结束, 数组下标 4 位置元素成功调整
// 第二趟循环 i = 5
// 1 2 -1 4 5 6 不满足条件 nums[i] > 0 本次循环结束, 数组下标 5 位置元素成功调整
// 最终调整后的结果: 1 2 -1 4 5 6, 寻找 nums[i] != i + 1 的元素就为结果
// 如果不存在 nums[i] != i + 1 的元素，则该调整后的数组元素为连续的正整数，则没有出现的最小正整数就是数组末尾下标对应的数字 + 1，结果等同于数组大小 + 1
fun firstMissingPositive(nums: IntArray): Int {
    for (i in nums.indices) {
        // 根据数组下标调整数字的位置，使下标作为数字的 key
        while (nums[i] > 0 && nums[i] <= nums.size && nums[nums[i] - 1] != nums[i]) {
            swapArray(nums, nums[i] - 1, i)
        }
    }
    // 没有出现的最小正整数 = 遍历寻找 nums[i] != i + 1 的元素
    for (i in nums.indices) {
        if (nums[i] != i + 1) return i + 1
    }
    // 数组调整后为无间隔的连续数字，没有出现的最小正整数 = 数组长度 + 1
    return nums.size + 1
}

fun swapArray(nums: IntArray, index1: Int, index2: Int) {
    val temp = nums[index1]
    nums[index1] = nums[index2]
    nums[index2] = temp
}
