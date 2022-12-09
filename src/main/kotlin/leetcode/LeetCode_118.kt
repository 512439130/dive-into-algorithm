package leetcode

import log.log

/** 118. 杨辉三角 */
fun main() {
    log(generate(5).toString())
    log(generate(10).toString())
}

fun generate(numRows: Int): List<List<Int>> {
    val result = arrayListOf<ArrayList<Int>>()
    // 行
    for (i in 0 until numRows) {
        val column = arrayListOf<Int>()
        // 列
        for (j in 0 .. i) {
            if(j == 0 || j == i) {
                column.add(1)
                continue
            }
            column.add(result[i - 1][j - 1] + result[i - 1][j])
        }
        result.add(column)
    }
    return result
}