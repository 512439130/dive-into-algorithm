package leetcode.greedy

import log.log

fun main() {
    val array = intArrayOf(1, 17, 5, 10, 13, 15, 10, 5, 16, 8)
    log(wiggleMaxLength(array))

    val array2 = intArrayOf(1, 1, 5, 10, 13, 15, 10, 5, 16, 8)
    log(wiggleMaxLength(array2))

    val array3 = intArrayOf(3, 3, 3, 2, 5)
    log(wiggleMaxLength(array3))

    val array4 = intArrayOf(3, 3, 3, 4, 2, 3)
    log(wiggleMaxLength(array4))

    val array5 = intArrayOf(3, 3, 4, 4, 2, 3, 3)
    log(wiggleMaxLength(array5))

    val array6 = intArrayOf(
        102,
        101,
        20,
        91,
        156,
        78,
        75,
        142,
        69,
        81,
        46,
        142,
        113,
        163,
        190,
        178,
        13,
        36,
        134,
        54
    )
    log(wiggleMaxLength(array6))
}

/** 376. 摆动序列 */
fun wiggleMaxLength(nums: IntArray): Int {
    if (nums.size <= 1) return nums.size
    // 前一个差值
    var preDiff = 0
    var length = 1
    for (i in 0 until nums.size - 1) {
        // 当前差值
        val curDiff = nums[i + 1] - nums[i]
        // 摆动序列规律: 通过当前差值和前一个差值确定是否为峰或谷（一正一负情况）
        // 序列首尾也可能是峰或谷
        if ((preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)) {
            length++
            preDiff = curDiff
        }
    }
    return length
}