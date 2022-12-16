package leetcode

import log.log


fun main() {
    log(climbStairs(8))
    log(climbStairs2(8))
}

// 列举 n = 1,2,3,4,5,6,7... F(n) = ? 寻找规律
// f(0) = 0
// f(1) = 1
// f(2) = 2
// f(3) = f(2) + f(1) = 2 + 1 = 3
// f(4) = f(3) + f(2) = 3 + 2 = 5
// f(5) = f(4) + f(3) = 5 + 3 = 8
// f(6) = f(5) + f(4) = 8 + 5 = 12
// F(n) = F(n - 1) + F(n - 2)
fun climbStairs(n: Int): Int {
    if (n <= 2) return n
    // 动态规划解法
    val dp = IntArray(n + 1)
    dp[0] = 0
    dp[1] = 1
    dp[2] = 2
    for (i in 3..n) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }
    return dp[n]
}

fun climbStairs2(n: Int): Int {
    if (n <= 2) return n
    // 动态规划解法，空间复杂度优化为 O(1)
    var a = 1
    var b = 2
    var sum = 0
    // f(1) = 1
    // f(2) = 2
    // f(3) = f(2) + f(1) = 2 + 1 = 3
    // F(n) = F(n - 1) + F(n - 2)
    for (i in 3..n) {
        // sum = a + b
        // 更新 F(n - 1)
        // 更新 F(n - 2)
        sum = a + b
        a = b
        b = sum
    }
    return sum
}





