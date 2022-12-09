package leetcode

import log.log

fun main() {
    log(isMatch("abcd", ".*"))
}

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s 的，而不是部分字符串。
 *
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s  只包含从 a-z 的小写字母。
 * p  只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符  * 时，前面都匹配到有效的字符
 */
fun isMatch(s: String, p: String): Boolean {
    val m = s.length
    val n = p.length
    // 动态规划解法
    // 1. 构造 dp
    // dp[i][j]：s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配

    val dp = Array(m + 1, init = { BooleanArray(n + 1) })


    // 1. 空的 s
    // 1.1 空的 s 可以匹配 空的 p
    // 1.2 空的 s 可能会匹配 非空的 p（比如 .* 可以匹配空串）
    // 2. 空的 p
    // 2.1 空的 p 可以匹配 空的 s
    // 2.2 空的 p 完全不匹配 非空 s
    // 1.1,2.1 ==>> dp[0][0] = true

    // 2. dp 初始化
    dp[0][0] = true
    for (i in 0..m) {
        // j 从 1 开始：j == 0 无意义
        for (j in 1..n) {
            // i 从 0 开始：.* 可以匹配空串
            if (p[j - 1] == '*') {
                // p[0] 不会出现 '*'
                // x + *
                // * 前必须有字符配合使用
                // 当 p[j - 1] == '*' 时，则需要匹配 p[j - 2] 和 s[i - 1]
                if (j >= 2 && i >= 1 && ((p[j - 2] == s[i - 1]) || (p[j - 2] == '.'))) {
                    // j 不动，i 前移一位
                    dp[i][j] = dp[i - 1][j]
                }
                // 除去 x + * 后，剩下的 p[j - 2] 和 s[i] 匹配
                if (j >= 2) {
                    dp[i][j] = dp[i][j] or dp[i][j - 2]
                }
                continue
            }
            // 匹配单个字符 || .
            if (i >= 1 && ((s[i - 1] == p[j - 1]) || (p[j - 1] == '.'))) {
                // 字符串 s[i] 和 模式串 p[j] 是否匹配，需要看 s[i - 1] 和 p[j - 1] 是否匹配
                dp[i][j] = dp[i - 1][j - 1]
            }
        }
    }
    return dp[m][n]
}