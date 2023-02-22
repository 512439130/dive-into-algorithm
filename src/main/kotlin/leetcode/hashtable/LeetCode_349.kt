package leetcode.hashtable

import log.logArray

fun main() {
//    logArray(intersection(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2)))
    logArray(intersection(intArrayOf(4, 9, 5), intArrayOf(9, 4, 9, 8, 4)))
}

/**
 * 349. 两个数组的交集
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 *
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 解释：[4,9] 也是可通过的
 */
fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
    val result = hashSetOf<Int>()
    val hashMap = hashSetOf<Int>()
    for (i in nums1.indices) {
        hashMap.add(nums1[i])
    }
    for (i in nums2.indices) {
        if (hashMap.contains(nums2[i])) {
            result.add(nums2[i])
        }
    }
    return result.toIntArray()
}