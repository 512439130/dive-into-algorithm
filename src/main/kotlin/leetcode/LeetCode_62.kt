package leetcode

import log.log

fun main() {
    log(uniquePaths(3, 7))
}

/** 62. 不同路径 */
fun uniquePaths(m: Int, n: Int): Int {
    // 1  1  1  1
    // 1  2  3  4
    // 1  3  6  10
    // 1  4  10 20
    // 动态规划: dp[i][j] = dp[i][j - 1] + dp[i - 1][j]
    // 先全部初始化为 1
    val dp = Array(m, init = { IntArray(size = n, init = { 1 }) })
    for (i in 1 until m) {
        for (j in 1 until n) {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        }
    }
    return dp[m - 1][n - 1]
}

/** 62. 不同路径 */
fun uniquePaths2(m: Int, n: Int): Int {
    // 1  1  1  1
    // 1  2  3  4
    // 1  3  6  10
    // 1  4  10 20
    // 优化空间复杂度: 二维数组 转化为 一维数组 O(n)
    // dp[0] [1] [2] [3]
    //    1   1   1   1
    //    1   2   3   4
    //    1   3   6   10
    //    1   4   10  20
    //    1   5   15  35
    val dp = IntArray(m * n, init = { 1 })
    for (i in 1 until m) {
        for (j in 1 until n) {
            // cur[j] = pre[j] + cur[j-1]
            // dp[j] = dp[j] + dp[j - 1]
            dp[j] += dp[j - 1]
        }
    }
    return dp[n - 1]
}
