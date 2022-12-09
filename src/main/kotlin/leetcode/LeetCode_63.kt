package leetcode

import log.log

fun main() {
    val array = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 1, 0),
        intArrayOf(0, 0, 0),
    )
    log(uniquePathsWithObstacles(array))
}

/** 63. 不同路径 II */
fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
    if (obstacleGrid.isEmpty() || obstacleGrid[0].isEmpty()) return 0
    val m = obstacleGrid.size
    val n = obstacleGrid[0].size
    // 如果起点或终点是障碍物, 则合法路径为 0
    if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) return 0
    val dp = Array(m, init = { IntArray(size = n, init = { 0 }) })
    // 路径边界赋值初始化
    for (index in 0 until m) {
        // 障碍物路不通, 结束循环
        if (obstacleGrid[index][0] != 0) break
        dp[index][0] = 1
    }
    for (index in 0 until n) {
        // 障碍物路不通, 结束循环
        if (obstacleGrid[0][index] != 0) break
        dp[0][index] = 1
    }
    // input
    // 0  1  0  0
    // 0  1  0  0
    // 0  1  0  0
    // 0  1  0  0

    // output
    // 1  0  0  0
    // 1  1  1  1
    // 1  2  3  4
    // 1  3  6  10
    for (i in 1 until m) {
        for (j in 1 until n) {
            // 遍历遇到障碍物, dp 缓存当前位置路径为 0
            dp[i][j] = if (obstacleGrid[i][j] == 0) dp[i - 1][j] + dp[i][j - 1] else 0
        }
    }
    return dp[m - 1][n - 1]
}

