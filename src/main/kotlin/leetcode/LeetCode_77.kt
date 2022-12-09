package leetcode

import log.logDoubleIntList
import java.util.ArrayList

fun main() {
    logDoubleIntList(combine(4, 2))
}

/** 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * */
fun combine(n: Int, k: Int): List<List<Int>> {
    val result = arrayListOf<List<Int>>()
    val temp = arrayListOf<Int>()
    backtrackingCombineOptimize(result, temp, n, k, 1)
    return result
}

fun backtrackingCombineOptimize(
    result: ArrayList<List<Int>>,
    temp: ArrayList<Int>,
    n: Int,
    k: Int,
    startIndex: Int
) {
    // 结束条件
    if (temp.size == k) {
        result.add(ArrayList(temp))
        return
    }
    // 搜索结果需要 k 的长度，如果当前 startIndex 后组合的大小无法满足 k 时，则结果无意义，剪枝优化
    // temp 未来还需添加的个数：k - temp.size
    // n = 搜索起点的最大值 + (temp 未来还需添加的个数) - 1
    // 搜索起点的最大值 = n - (temp 未来还需添加的个数) + 1
    // 搜索起点的最大值 = n - (k - temp.size) + 1
    val need = n - (k - temp.size) + 1
    for (i in startIndex..need) {
        temp.add(i)
        // 递归下一层搜索，startIndex = index + 1
        backtrackingCombine(result, temp, n, k, i + 1)
        // 回溯父结点位置
        temp.removeAt(temp.size - 1)
    }
}

fun backtrackingCombine(
    result: ArrayList<List<Int>>,
    temp: ArrayList<Int>,
    n: Int,
    k: Int,
    startIndex: Int
) {
    // 结束条件
    if (temp.size == k) {
        result.add(ArrayList(temp))
        return
    }
    for (i in startIndex..n) {
        temp.add(i)
        // 递归下一层搜索，startIndex = index + 1
        backtrackingCombine(result, temp, n, k, i + 1)
        // 回溯父结点位置
        temp.removeAt(temp.size - 1)
    }
}