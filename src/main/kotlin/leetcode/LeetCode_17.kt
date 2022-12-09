package leetcode

import log.logStringList

fun main() {
    logStringList(letterCombinations("23"))
}

/** 17. 电话号码的字母组合 回溯法 */
fun letterCombinations(digits: String): List<String> {
    if (digits.isEmpty()) return listOf()
    val result = ArrayList<String>()
    val temp = StringBuilder()
    // only number 2 ~ 9
    val numKbd = hashMapOf(
        "2" to "abc",
        "3" to "def",
        "4" to "ghi",
        "5" to "jkl",
        "6" to "mno",
        "7" to "pqrs",
        "8" to "tuv",
        "9" to "wxyz"
    )
    backtrackingLetterCombinations(result, temp, numKbd, digits, 0)
    return result
}

fun backtrackingLetterCombinations(
    result: java.util.ArrayList<String>,
    temp: StringBuilder,
    numKbd: HashMap<String, String>,
    digits: String,
    index: Int
) {
    // index：表示树的深度
    if (digits.length == index) {
        // 收集结果并结束本层递归
        result.add(temp.toString())
        return
    }
    val keys = numKbd[digits[index].toString()] ?: ""
    // 横向遍历
    for (i in keys.indices) {
        // temp 拼接当前字符元素
        temp.append(keys[i])
        // 递归下一个数字对应的字符集合（遍历树的下一层）
        backtrackingLetterCombinations(result, temp, numKbd, digits, index + 1)
        // 回溯(temp 删除当前末尾元素，回溯上个父节点)
        temp.deleteCharAt(temp.length - 1)
    }
}