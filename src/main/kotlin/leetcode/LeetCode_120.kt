package leetcode

import log.log

fun main() {
    val array1 = arrayListOf<List<Int>>(
        arrayListOf(-1),
        arrayListOf(2, 3),
        arrayListOf(1, -1, -3)
    )
    log(minimumTotal(array1))

    val array2 = arrayListOf<List<Int>>(
        arrayListOf(1),
        arrayListOf(4, 2),
        arrayListOf(3, 6, -5, 9)
    )
    log(minimumTotal2(array2))

    val array3 = arrayListOf<List<Int>>(
        arrayListOf(2),
        arrayListOf(3, 4),
        arrayListOf(6, 5, 7),
        arrayListOf(4, 1, 8, 3)
    )
    log(minimumTotal3(array3))
}

fun minimumTotal(triangle: List<List<Int>>): Int {
    if (triangle.isEmpty()) return 0
    val size = triangle.size
    // 动态规划
    // dp[i][j] 从三角形顶部(0,0)到(i,j)的最小路径和
    val dp = Array(size = size, init = { IntArray(size = size, init = { 0 }) })
    // 初始化
    dp[0][0] = triangle[0][0]
    for (i in 1 until size) {
        // 左边界处理
        dp[i][0] = dp[i - 1][0] + triangle[i][0]
        for (j in 1 until i) {
            // 求解顶部到当前位置(i,j)的最短路径和
            dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j]
        }
        // 右边界处理
        dp[i][i] = dp[i - 1][i - 1] + triangle[i][i]
    }

    // 三角形最后一行寻找结果
    var minTotal = Int.MAX_VALUE
    for (i in 0 until size) {
        minTotal = Math.min(minTotal, dp[size - 1][i])
    }
    return minTotal
}

fun minimumTotal2(triangle: List<List<Int>>): Int {
    val size = triangle.size
    // 自底向上反向递推
    // 底边作为起点
    // dp[i][j] 表示从点 (i, j) 到底边的最小路径和
    val dp = Array(size + 1, init = { IntArray(size = size + 1, init = { 0 }) })
    for (i in size - 1 downTo 0) {
        for (j in 0..i) {
            dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]
        }
    }
    return dp[0][0]
}

fun minimumTotal3(triangle: List<List<Int>>): Int {
    val size = triangle.size
    // 自底向上反向递推
    // 底边作为起点
    // dp[i][j] 表示从点 (i, j) 到底边的最小路径和
    // 因为 i 在本题求解时无关，把 i 的维度去掉
    val dp = IntArray(size = size + 1, init = { 0 })
    for (i in size - 1 downTo 0) {
        for (j in 0..i) {
            dp[j] = Math.min(dp[j], dp[j + 1]) + triangle[i][j]
        }
    }
    return dp[0]
}