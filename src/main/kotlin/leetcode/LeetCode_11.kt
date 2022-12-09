package leetcode

import log.log

fun main() {
    val array = intArrayOf(1, 2, 3, 4, 5, 4, 4, 3, 2)
    log(maxArea(array))
    log(maxArea2(array))
}

/** 11. 盛最多水的容器（暴力解法） */
fun maxArea2(height: IntArray): Int {
    var max = 0
    var left = 0
    var right = height.size - 1
    while (left < right) {
        max = Math.max(max, Math.min(height[left], height[right]) * (right - left))
        if (height[left] < height[right]) {
            left++
        } else {
            right--
        }
        println("test：max $max  left：$left right：$right")
    }
    return max
}

/** 11. 盛最多水的容器 （暴力解法）*/
fun maxArea(height: IntArray): Int {
    var max = 0
    for (start in height.indices) {
        for (end in height.size - 1 downTo 0) {
            if (start >= end) break
            max = Math.max(max, Math.min(height[start], height[end]) * (end - start))
            println("test：max $max  start：$start end：$end")
        }
    }
    return max
}