package leetcode

import log.log

fun main() {
    val array = intArrayOf(3, 4, 5, 6, 7, 9, 10, 0, 1, 2)
    log(search(array, 9))
    log(search2(array, 9))
}

/**
 * 33. 搜索旋转排序数组
 * 二分查找 + 逻辑控制边界
 * */
fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1
    while (left <= right) {
        val mid = (right - left).shr(1) + left
        when {
            nums[mid] < target -> {
                if (nums[right] < target) {
                    right--
                } else {
                    left = mid + 1
                }
            }
            nums[mid] > target -> {
                if (nums[left] > target) {
                    left++
                } else {
                    right = mid - 1
                }
            }
            else -> {
                return mid
            }
        }
    }
    return -1
}

/**
 * 33. 搜索旋转排序数组
 * 二分查找优化
 * */
fun search2(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1
    // 将数组一分为二, 其中一定有一个是有序的, 另一个可能是有序。
    // 有序部分用二分法查找, 无序部分再一分为二（其中一个一定有序, 另一个可能有序）
    // 无限缩进 left, right 的坐标，循环查到结果
    while (left <= right) {
        val mid = (right - left).shr(1) + left
        if (nums[mid] == target) return mid
        // 找到结果
        if (nums[mid] >= nums[left]) {
            // 新范围中-左半边有序
            // target 值在 [left, mid - 1] 区间中
            if (target < nums[mid] && target >= nums[left]) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        } else {
            // 新范围中-右半边有序
            // target 值在 [mid + 1, right] 区间中
            if (target > nums[mid] && target <= nums[right]) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
    }
    return -1
}

