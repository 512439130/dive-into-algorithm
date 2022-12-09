package leetcode

import log.log

fun main() {
    log(totalNQueens(4))
    log(totalNQueens2(4))
}

/**
 * 52. N皇后 II
 */
fun totalNQueens(n: Int): Int {
    val rowSet = hashSetOf<Int>()
    val diagonalLeftSet = hashSetOf<Int>()
    val diagonalRightSet = hashSetOf<Int>()
    val initRow = 0
    // 回溯解法
    return backTrackNQueens(n, initRow, rowSet, diagonalLeftSet, diagonalRightSet)
}

fun backTrackNQueens(
    n: Int,
    row: Int,
    columnSet: HashSet<Int>,
    diagonalLeftSet: HashSet<Int>,
    diagonalRightSet: HashSet<Int>
): Int {
    // 回溯结束条件
    if (row == n) return 1
    var count = 0
    for (index in 0 until n) {
        // 记录每一列是否有皇后
        if (columnSet.contains(index)) continue
        // 左斜线是否有皇后（行坐标 + 列坐标相等的所有元素）
        val diagonalLeft = row + index
        if (diagonalLeftSet.contains(diagonalLeft)) continue
        // 右斜线是否有皇后（行坐标 - 列坐标相等的所有元素）
        val diagonalRight = row - index
        if (diagonalRightSet.contains(diagonalRight)) continue

        // 记录合法的皇后位置
        columnSet.add(index)
        // 记录合法的左斜线位置
        diagonalLeftSet.add(diagonalLeft)
        // 记录合法的右斜线位置
        diagonalRightSet.add(diagonalRight)

        count += backTrackNQueens(n, row + 1, columnSet, diagonalLeftSet, diagonalRightSet)
        // 回溯操作，回退结果，继续遍历一下一个 index
        columnSet.remove(index)
        diagonalLeftSet.remove(diagonalLeft)
        diagonalRightSet.remove(diagonalRight)
    }
    return count
}


fun totalNQueens2(n: Int): Int {
    // 回溯解法
    return backTrackNQueens2(n, 0, 0, 0, 0)
}

fun backTrackNQueens2(
    n: Int,
    row: Int,
    columns: Int,
    leftDiagonals: Int,
    rightDiagonals: Int
): Int {
    // 回溯结束条件
    if (row == n) return 1
    var count = 0
    // Int.and(Int) 按位或: &
    // Int.or(Int) 按位或: |
    // Int.inv(Int) 按位非(取反): ~
    // Int.xor(Int) 按位异或: ^
    // Int.shl(Int) 左移: <<
    // Int.shr(Int) 右移: >>
    // Int.ushr(Int) 无符号右移: >>>
    // 不能放置皇后的位置(二进制表示)
    val mergeProhibitRule = columns or leftDiagonals or rightDiagonals
    // 皇后的合法位置
    // ~0 = ~00000000000000000000000000000000 ==>> 11111111111111111111111111111111 ==>> -1
    val mergeAvailableRule = mergeProhibitRule.inv()
    // 皇后的合法位置(去除高位)
    // ((1 shl n) - 1) and x: 保证取反后 n 皇后列数不变
    // n = 4 1 << n = 00010000 = 16 - 1 = 00001111
    // 00001111 & 11111111111111111111111111111111 = 00001111
    var availablePositions = ((1 shl n) - 1) and mergeAvailableRule
    // 1: 皇后位置合法
    // 0: 皇后位置不合法
    while (availablePositions != 0) {
        // (x & -x): 保留最后一位1，而其他位清零
        // -x: (~x + 1)
        // x & -x = x & (~x + 1)
        // 例1:
        // x = 10110011
        // ~x + 1 = 01001100 + 1 = 01001101
        // x & -x = 00000001
        // 例2:
        // 00110110 ==>> 11001001 + 1 = 11001010
        // 00110110 & 11001010 = 00000010(注意是最后一位，不是最小的二进制位)

        // x & (x - 1): 最低位清零
        // 例:
        // x = 10110011
        // x - 1 = 10110011 + 1 = 10110010
        // x & (x - 1) = 10110010
        val position = availablePositions.and(-availablePositions)
        count += backTrackNQueens2(
            n,
            row + 1,
            columns or position,
            (leftDiagonals or position) shl 1,
            (rightDiagonals or position) shr 1
        )
        // 回溯操作
        availablePositions = availablePositions.and(availablePositions - 1)
    }
    return count
}