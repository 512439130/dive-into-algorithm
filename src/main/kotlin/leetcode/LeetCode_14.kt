package leetcode

import log.log
import log.logDoubleIntList

fun main() {
    val arrayStr = Array(size = 2, init = { "" })
    arrayStr[0] = ""
    arrayStr[1] = "ca"
    arrayStr[2] = ""

    log(longestCommonPrefix(arrayStr))
    val array = intArrayOf(-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4)
    logDoubleIntList(threeSum(array))
}

/**
 * 14. 最长公共前缀
 */
fun longestCommonPrefix(strs: Array<String>): String {
    if (strs.isEmpty() || strs[0].isEmpty()) return ""
    if (strs.size == 1) return strs[0]
    var index = 1
    var key: String = strs[0].substring(0, index)
    while (true) {
        for (i in 1 until strs.size) {
            if (strs[i].isEmpty()) return ""
            if (index > strs[0].length || index > strs[i].length) return key.substring(0, index - 1)
            key = strs[0].substring(0, index)
            if (strs[i].substring(0, index) != key) return key.substring(0, key.length - 1)
        }
        index++
    }
}