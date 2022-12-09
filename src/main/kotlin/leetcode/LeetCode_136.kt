package leetcode

import log.log

/** 5. 最长回文子串 */
fun main() {
    log(singleNumber(intArrayOf(4,1,2,1,2)))
}

fun singleNumber(nums: IntArray): Int {
    // xor: 按位异或
    // 0 xor x = x
    // x xor x = 0
    // 时间复杂度: O(n)
    // 空间复杂度: O(1)
    var result = 0
    for (i in nums.indices) {
        result = result xor nums[i]
    }
    return result
}