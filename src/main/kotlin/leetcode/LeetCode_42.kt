package leetcode

import log.log
import java.util.*

fun main() {
    val array = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
    log(trap(array))
}

/**
 * 42. 接雨水
 */
fun trap(height: IntArray): Int {
    var result = 0
    // 单调栈解法
    // 栈中存放对应柱子的下标
    val stack: Stack<Int> = Stack()
    for (index in height.indices) {
        while (stack.isNotEmpty() && height[index] > height[stack.peek()]) {
            val curHeight = height[stack.pop()]
            if (stack.isEmpty()) {
                break
            }
            // 凹槽的左边界
            val left = stack.peek()
            // 计算接雨水的宽度
            val validWidth = index - left - 1
            // 计算每个柱子接雨水的有效高度
            val validHeight = Math.min(height[left], height[index]) - curHeight
            result += validWidth * validHeight
        }
        stack.push(index)
    }
    return result
}
