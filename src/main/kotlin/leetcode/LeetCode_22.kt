package leetcode

import log.logStringList

fun main() {
    logStringList(generateParenthesis(3))
}

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * n = 3
 * ["((()))","(()())","(())()","()(())","()()()"]
 * n = 2
 * ["()()","(())"]
 */
fun generateParenthesis(n: Int): List<String> {
    val result = arrayListOf<String>()
    val temp = StringBuilder()
    backTrackingParenthesis(result, temp, 0, 0, n)
    return result
}

fun backTrackingParenthesis(
    result: ArrayList<String>,
    temp: StringBuilder,
    left: Int,
    right: Int,
    max: Int
) {
    if (temp.length == (max * 2)) {
        result.add(temp.toString())
        return
    }
    // 左括号数量小于最大括号组合数
    if (left < max) {
        temp.append("(")
        backTrackingParenthesis(result, temp, left + 1, right, max)
        temp.deleteCharAt(temp.length - 1)
    }
    // 右括号数量小于左括号数量
    if (right < left) {
        temp.append((")"))
        backTrackingParenthesis(result, temp, left, right + 1, max)
        temp.deleteCharAt(temp.length - 1)
    }
}