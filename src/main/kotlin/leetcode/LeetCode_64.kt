package leetcode

import log.log

fun main() {
    val array = arrayOf(
        intArrayOf(1, 3, 1),
        intArrayOf(1, 5, 1),
        intArrayOf(4, 2, 1),
    )
    log(minPathSum(array))
}

/** 64. 最小路径和 */
fun minPathSum(grid: Array<IntArray>): Int {
    // 动态规划: 通过循环遍历二维数组，计算走到每个位置的最短路径，缓存在 dp 中
    // dp[i][j]: 走到 (i,j) 位置的最小路径和
    // 网格 m * n 中, dp[m][n]：右下角终点最小路径和
    val m = grid.size - 1
    if (m == -1) return 0
    val n = grid[0].size - 1
    // 不重新创建 dp, 直接使用 grid 原地替换
    for (i in 0..m) {
        for (j in 0..n) {
            // 路径起点
            if (i == 0 && j == 0) continue
            // 左边界处理
            if (i == 0) {
                grid[i][j] = grid[i][j - 1] + grid[i][j]
            }
            // 上边界处理
            if (j == 0) {
                grid[i][j] = grid[i - 1][j] + grid[i][j]
            }
            // 非边界处理
            if (i != 0 && j != 0) {
                // 向右走 或 向下走，取最小值
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j]
            }
        }
    }
    return grid[m][n]
}
