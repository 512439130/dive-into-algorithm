package leetcode

import log.log

fun main() {
    log(simplifyPath("/..hidden"))
    log(simplifyPath("/..hidden/"))
    log(simplifyPath("/hello../world"))
    log(simplifyPath("/a/../../b/../c//.//"))
    log(simplifyPath("/a//b////c/d//././/.."))
    log(simplifyPath("/a//b////c/d//././/."))
    log(simplifyPath("/."))
    log(simplifyPath("/.."))
    log(simplifyPath("/home/"))
    log(simplifyPath("/../"))
    log(simplifyPath("/home//foo/"))
    log(simplifyPath("/a/./b/../../c/"))

    log(simplifyPath("/a/./c/d/e"))
    log(simplifyPath("/a/../c/d/e"))
    log(simplifyPath("/a//e"))
    log(simplifyPath("/a//e/"))
    log(simplifyPath("/a/.../e/"))
    log(simplifyPath("/a/......../e/"))
}

/**
 * 71. 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。

示例 1：
输入：path = "/home/"
输出："/home"
解释：注意，最后一个目录名后面没有斜杠。

示例 2：
输入：path = "/../"
输出："/"
解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。

示例 3：
输入：path = "/home//foo/"
输出："/home/foo"
解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。

示例 4：
输入：path = "/a/./b/../../c/"
输出："/c"


提示：
1 <= path.length <= 3000
path 由英文字母，数字，'.'，'/' 或 '_' 组成。
path 是一个有效的 Unix 风格绝对路径。

请注意，返回的 规范路径 必须遵循下述格式：

1. 始终以斜杠 '/' 开头。
2. 两个目录名之间必须只有一个斜杠 '/' 。
3. 最后一个目录名（如果存在）不能 以 '/' 结尾。
4. 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。

 */
var count = 0
fun simplifyPath(path: String): String {

    var sb = handle(path)
    // 最后一个目录名（如果存在）不能 以 '/' 结尾
    sb = endCheckLogic(sb)
    if (sb.isEmpty()) sb.append('/')
    return sb.toString()
}

private fun handle(path: String): StringBuilder {
    var sb = StringBuilder()
    for (i in path.indices) {
        val cur = path[i]
        if (cur == '/') {
            if (count == 1) {
                sb = StringBuilder(sb.substring(0, sb.length - 2))
                count = 0
                sb.append(cur)
                continue
            }
            if (count == 2) {
                val curKey = sb.substring(0, sb.lastIndexOf('/'))
                sb = if (curKey.isEmpty()) {
                    StringBuilder(curKey)
                } else {
                    StringBuilder(sb.substring(0, curKey.lastIndexOf('/')))
                }
                sb.append(cur)
                count = 0
                continue
            }
        }
        count = countLogic(cur, sb, count)
        if (cur == '/' && sb.endsWith('/')) continue
        if (cur == '.' && i == path.length - 1) {
            if (count == 1) {
                sb = StringBuilder(sb.substring(0, sb.length - 1))
                continue
            }
            if (count == 2) {
                val curKey = sb.substring(0, sb.lastIndexOf('/'))
                sb = if (curKey.isEmpty()) {
                    StringBuilder(curKey)
                } else {
                    StringBuilder(sb.substring(0, curKey.lastIndexOf('/')))
                }
                continue
            }
        }
        sb.append(cur)
    }
    return sb
}

private fun countLogic(cur: Char, sb: StringBuilder, count: Int): Int {
    var count1 = count
    if (cur == '.' && ((sb.endsWith('/')) || (((sb.substring(0, sb.length - 1)).endsWith('/')) && (sb.endsWith('.'))))) {
        count1++
    } else {
        count1 = 0
    }
    return count1
}

private fun endCheckLogic(sb: StringBuilder): StringBuilder {
    var sb1 = sb
    if (sb1.length != 1 && sb1.endsWith('/')) {
        for (j in sb1.length - 1 downTo 0) {
            if (sb1[j] != '/') break
            sb1 = StringBuilder(sb1.substring(0, sb1.length - 1))
        }
    }
    return sb1
}