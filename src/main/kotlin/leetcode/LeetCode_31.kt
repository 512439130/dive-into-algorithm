package leetcode

fun main() {
//    val array = intArrayOf(1, 8, 7, 6, 5, 4, 3)
//    val array = intArrayOf(2, 1, 3, 4, 5, 6)
//    val array = intArrayOf(8, 7, 6, 5, 4, 3)
//    val array = intArrayOf(2, 7, 5, 5, 4, 3)
//    val array = intArrayOf(2, 3, 7, 4, 2, 1)
//    val array = intArrayOf(1, 1, 1, 5)
//    val array = intArrayOf(3, 4, 5, 6, 7)
//    val array = intArrayOf(3)
//    val array = intArrayOf(1, 3, 2)
//    val array = intArrayOf(2, 3, 1)
//    val array = intArrayOf(2, 3, 4, 1)
//    val array = intArrayOf(2, 3, 5, 4, 2, 1)
//    val array = intArrayOf(2, 1, 8, 7, 6, 5, 4, 3)
//    val array = intArrayOf(1, 2, 4, 3, 5)
//    val array = intArrayOf(1, 2, 4, 3, 5, 6)
//    val array = intArrayOf(1, 2, 4, 3, 6, 5)
//    val array = intArrayOf(1, 1, 5)
    val array = intArrayOf(1, 5, 1)
    nextPermutation(array)
    println(array.contentToString())
}

/**
 * 31. 下一个排列
 */
fun nextPermutation(nums: IntArray): Unit {
    if (nums.size == 1) return
    var pre = -1
    for (i in (nums.size - 1) downTo 0) {
        if (i == 0 && nums[i] > pre) {
            // 按字典排序最大的排列，则下一个排列为该排列的逆序
            for (j in nums.indices) {
                if (j >= nums.size - j - 1) break
                val cur = nums[j]
                nums[j] = nums[nums.size - j - 1]
                nums[nums.size - j - 1] = cur
            }
            break
        }
        if (nums[i] < pre) {
            // 1. 查找当前数组中下一个大于num[i]的数字
            var index = -1
            for (j in (i + 1) until nums.size) {
                if (nums[j] <= nums[i]) break
                index = j
            }
            // 2. 用 num[index] 交换 num[i]
            val temp = nums[i]
            nums[i] = nums[index]
            nums[index] = temp
            // 3. 使用双指针调整元素顺序
            for (j in (i + 1) until nums.size) {
                if (j >= nums.size - j + i) break
                val cur = nums[j]
                nums[j] = nums[nums.size - j + i]
                nums[nums.size - j + i] = cur
            }
            break
        }
        pre = nums[i]
    }
}

fun nextPermutation2(nums: IntArray): Unit {
    if (nums.size == 1) return
    var pre = -1
    for (i in (nums.size - 1) downTo 0) {
        if (i == 0 && nums[i] > pre) {
            // 按字典排序最大的排列，则下一个排列为该排列的逆序
            reverse(nums, 0)
            break
        }
        if (nums[i] < pre) {
            // 1. 查找当前数组中下一个大于num[i]的数字
            var index = -1
            for (j in (i + 1) until nums.size) {
                if (nums[j] <= nums[i]) break
                index = j
            }
            // 2. num[index] 和 num[i] 原地交换
            swap(nums, i, index)
            // 3. 调整元素顺序
            reverse(nums, i + 1)
            break
        }
        pre = nums[i]
    }
}

/** 反转数组元素 */
fun reverse(array: IntArray, start: Int) {
    var left = start
    var right = array.size - 1
    while (left < right) {
        swap(array, left, right)
        left++
        right--
    }
}