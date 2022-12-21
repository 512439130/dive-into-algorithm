package leetcode

import log.log
import java.util.*

fun main() {
    val arrayG = intArrayOf(1, 3, 5, 9)
    val arrayS = intArrayOf(1, 2, 8, 10)
    log(findContentChildren(arrayG, arrayS))
    log(findContentChildren2(arrayG, arrayS))
}

/** 455. 分发饼干 */
fun findContentChildren(g: IntArray, s: IntArray): Int {
    // 贪心算法解法
    // 局部最优：大饼干先喂饱大胃口
    // 整体最优：喂饱尽可能多的孩子

    // 排序：孩子胃口值
    Arrays.sort(g)
    // 排序：饼干的尺寸
    Arrays.sort(s)
    // 饼干数组下标, 从大饼干开始发放
    var index = s.size - 1
    var result = 0
    for (i in g.size - 1 downTo 0) {
        if (index < 0) break
        if (s[index] >= g[i]) {
            index--
            result++
        }
    }
    return result
}

fun findContentChildren2(g: IntArray, s: IntArray): Int {
    // 贪心算法解法
    // 局部最优：先满足小胃口孩子
    // 整体最优：喂饱尽可能多的孩子
    // 排序：孩子胃口值
    Arrays.sort(g)
    // 排序：饼干的尺寸
    Arrays.sort(s)
    // 胃口值下标, 先满足小胃口孩子
    var index = 0
    for (i in s.indices) {
        if (index >= g.size) break
        if (g[index] <= s[i]) {
            index++
        }
    }
    return index
}