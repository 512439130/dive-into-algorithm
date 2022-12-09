package leetcode

import log.log

/** 119. 杨辉三角 II */
fun main() {
    log(getRow(3).toString())
    log(getRow(10).toString())
    log(getRow2(3).toString())
    log(getRow2(10).toString())
    log(getRow3(3).toString())
    log(getRow3(10).toString())
}

fun getRow(rowIndex: Int): List<Int> {
    val result = arrayListOf<ArrayList<Int>>()
    // 构造杨辉三角，目标行数为最大遍历构造行数
    for (i in 0..rowIndex) {
        val column = arrayListOf<Int>()
        for (j in 0..i) {
            if (j == 0 || j == i) {
                column.add(1)
                continue
            }
            column.add(result[i - 1][j - 1] + result[i - 1][j])
        }
        result.add(column)
    }
    return result[rowIndex]
}

fun getRow2(rowIndex: Int): List<Int> {
    var pre = arrayListOf<Int>()
    // 滑动数组思想优化，仅使用一个数组不断更新记录 行数据
    for (i in 0..rowIndex) {
        val cur = arrayListOf<Int>()
        for (j in 0..i) {
            if (j == 0 || j == i) {
                cur.add(1)
                continue
            }
            cur.add(pre[j - 1] + pre[j])
        }
        pre = cur
    }
    return pre
}

fun getRow3(rowIndex: Int): List<Int> {
    // 优化为 1 个数组
    val result = ArrayList<Int>(rowIndex + 1)
    for (i in 0..rowIndex) {
        result.add(1)
        // for 循环的范围：每行中间范围（除首尾的其他数）
        for (j in i - 1 downTo 1) {
            // 规律：当前行当前列 = 上一行的上一列 + 上一行的当前列
            // j 遍历方向为从大到小，刚好满足记录上一行的规律
            // 还未更新时，result[index] 记录是上一行的数据
            result[j] = result[j] + result[j - 1]
        }
    }
    return result
}