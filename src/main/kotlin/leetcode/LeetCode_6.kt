package leetcode

import log.log

fun main() {
    log(convert("ABCDEFGHIJK", 4))
    log(convert2("ABCDEFGHIJK", 4))
    log(convert3("ABCDEFGHIJK", 4))
}

/** 6. Z 字形变换 代码优化 */
fun convert(s: String, numRows: Int): String {
    val length = s.length
    if (numRows == 1 || length <= numRows) return s
    //  Z字变换过程的个数
    val zNum = numRows - 2
    // 一个完整周期字符个数 = 向下过程的个数 + Z字变换过程的个数（右上方向）numRows + (numRows - 2) = numRows * 2 - 2
    val oneTimeCycleNum = numRows + zNum
    // 每个周期会占用的列数： 1 + (numRows - 2) = numRows - 1
    val oneTimeCycleColumn = numRows - 1
    // 周期数(假设最后一个周期是完整周期)：字符长度 / 一个完整周期字符个数
    val timeCycleNum = (length + oneTimeCycleNum - 1) / oneTimeCycleNum
    // 构造所需矩阵的列数：周期数 * 每个周期会占用的列数 = ((length + oneTimeCycle - 1) / oneTimeCycle) * oneTimeCycleColumn
    // 构造所需矩阵的列数：numColumn = (length + oneTimeCycle - 1) / oneTimeCycle * oneTimeCycleColumn
    val numColumn = timeCycleNum * oneTimeCycleColumn
    val result = Array(size = numRows, init = { CharArray(size = numColumn, init = { '0' }) })
    var i = 0
    var j = 0
    for (index in s.indices) {
        result[i][j] = s[index]
        if (index % oneTimeCycleNum < numRows - 1) {
            //向下移动
            i++
        } else {
            //向右上移动
            i--
            j++
        }
    }
    // 横向遍历非空元素
    val sb = StringBuilder()
    for (x in 0 until numRows) {
        for (y in 0 until numColumn) {
            if ('0' != result[x][y]) {
                sb.append(result[x][y])
            }
        }
    }
    return sb.toString()
}

/** 6. Z 字形变换 数组压缩 */
fun convert2(s: String, numRows: Int): String {
    if (numRows == 1 || s.length <= numRows) return s
    // 一个完整周期字符个数 = 向下过程的个数 + Z字变换过程的个数（右上方向）numRows + (numRows - 2) = numRows * 2 - 2
    val oneTimeCycleNum = numRows * 2 - 2
    // 数组压缩：每一行的所有字符通过 StringBuilder 拼接
    // s = ABCDEFGH numRows = 3
    // A   E    rowSbArray[0] = 'AE'
    // B D F H  rowSbArray[1] = 'BDFH'
    // C   G    rowSbArray[2] = 'CG'
    // result = rowSbArray[0] + rowSbArray[1] + rowSbArray[2] = 'AEBDFHCG'
    val rowSbArray = Array(size = numRows, init = { StringBuilder() })
    var index = 0
    for (i in s.indices) {
        rowSbArray[index].append(s[i])
        // 字符当前位置判断下个元素的移动方向
        // 当前扫描元素下标 % 一个变换周期的元素个数 < 行数 - 1 = index++ (向下)
        // 当前扫描元素下标 % 一个变换周期的元素个数 >= 行数 - 1 = index++ (右上)
        if (i % oneTimeCycleNum < numRows - 1) {
            //向下移动
            index++
        } else {
            //向右上移动
            index--
        }
    }
    // 横向遍历非空元素
    val resultSb = StringBuilder()
    for (i in rowSbArray.indices) {
        resultSb.append(rowSbArray[i])
    }
    return resultSb.toString()
}


/** 6. Z 字形变换 直接构造 */
fun convert3(s: String, numRows: Int): String {
    if (numRows == 1 || s.length <= numRows) return s
    // 一个完整周期字符个数 = 向下过程的个数 + Z字变换过程的个数（右上方向）numRows + (numRows - 2) = numRows * 2 - 2
    val t = numRows * 2 - 2
    val result = StringBuilder()
    // 行循环
    for (i in 0 until numRows) {
        var j = 0
        // 每个周期的起始下标
        while ((i + j) < s.length) {
            // 每行当前周期的第一个元素
            result.append(s[i + j])
            // 每行当前周期的第 2 个元素(Z轨迹元素)
            // (t - i)是当前周期中斜线上的元素的字符下标，j 是周期偏移量
            // 全部周期中 Z 轨迹（右上方向）中的元素字符下标：(t - i) + j
            if ((i > 0 && i < numRows - 1) && ((t - i) + j) < s.length) {
                // 每行中的 Z 字变换部分
                result.append(s[(t - i) + j])
            }
            // 下个周期
            j += t
        }
    }
    return result.toString()
}
