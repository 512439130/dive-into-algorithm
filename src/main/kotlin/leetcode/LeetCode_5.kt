package leetcode

import log.log

/** 5. 最长回文子串 */
fun main() {
    log(longestPalindrome("abcb"))
}

fun longestPalindrome(s: String): String {
    // 单个字符组成的回文串
    if (s.length == 1) return s
    // 动态规划解法
    var max = 0
    var start = 0
    // 1. dp 确定 和 dp 初始化 ==>> dp[i][j]表示区间范围是 [i,j] 的子串是否回文子串, 初始化为 false
    val dp = Array(size = s.length, init = { BooleanArray(size = s.length, init = { false }) })

    // 2. 递推公式
    //  一. s[i] = s[j]
    //      1. i = j 一个字符 例如 "y" 单个字符组成的回文子串
    //      2. j - i = 1 下标相差1 例如 "yy" 重复字符组成的回文子串
    //      3. j - i > 1 因为 s[i] = s[j] 所以 判断 dp[i][j] 是否回文子串 转换为 dp[i+1][j-1] ?= true
    //      例: s = "abcba" i = 0 j = size -1  s[i] = s[j] 只需要判断 "bcb" 是否回文串即可
    // 二. s[i] != s[j] 不是回文串：默认值 false

    // 3. 模拟 dp[i][j] i: 列 j: 行
    // [0][0] [0][1] [0][2] [0][3] [0][4]
    // [1][0] [1][1] [1][2] [1][3] [1][4]
    // [2][0] [2][1] [2][2] [2][3] [2][4]
    // [3][0] [3][1] [3][2] [3][3] [3][4]
    // [4][0] [4][1] [4][2] [4][3] [4][4]
    // 保证 dp[i + 1][j - 1] 的结果比 dp[i][j] 早计算，所以 [4][0] 要比 [3][1] 早计算出结果

    // 4. 遍历顺序：从下向上，从左到右（左下角 -> 右上角） ==>> i[size-1..0] j[0..size-1]
    for (i in dp.size - 1 downTo 0) {
        // 只计算有意义部分，[0][0] 和 [4][4] 形成对角线上半部分（包含对角线）j >= i
        for (j in i until dp[i].size) {
            if (s[i] == s[j]) {
                if ((j - i) <= 1) { // 情况 1, 2
                    dp[i][j] = true
                } else if ((j - i) > 1 && dp[i + 1][j - 1]) { // 情况 3
                    dp[i][j] = true
                }
            }
            if (dp[i][j] && max < j - i + 1) {
                max = j - i + 1
                start = i
            }
        }
    }
    return s.substring(start, start + max)
}