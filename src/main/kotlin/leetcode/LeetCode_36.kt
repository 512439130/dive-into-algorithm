package leetcode

import log.log

fun main() {
    val board = arrayOf(
        charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
        charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
        charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
        charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
        charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
        charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
        charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
        charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
        charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9'),
    )
    log(isValidSudoku(board))
}

// [".",".",".",".","5",".",".","1","."],
// [".","4",".","3",".",".",".",".","."],
// [".",".",".",".",".","3",".",".","1"],
// ["8",".",".",".",".",".",".","2","."],
// [".",".","2",".","7",".",".",".","."],
// [".","1","5",".",".",".",".",".","."],
// [".",".",".",".",".","2",".",".","."],
// [".","2",".","9",".",".",".",".","."],
// [".",".","4",".",".",".",".",".","."]
/** 36. 有效的数独 */
fun isValidSudoku(board: Array<CharArray>): Boolean {
    val hashSetRow = HashMap<Int, HashSet<Int>>(9)
    val hashSetColumn = HashMap<Int, HashSet<Int>>(9)
    val hashSetThreeByThree = HashMap<Int, HashSet<Int>>(9)
    for (i in 0 until 9) {
        hashSetRow[i] = HashSet()
        hashSetColumn[i] = HashSet()
        hashSetThreeByThree[i] = HashSet()
    }
    // 固定 9 * 9 问题，计算量 和 存储空间 都不随数据而变化
    // T(n)：O(1)
    // S(n)：O(1)
    for (i in 0 until 9) {
        for (j in 0 until 9) {
            if (board[i][j] == '.') continue
            // 转换成数字
            val value: Int = board[i][j] - '0'
            //         第一大列  第二大列   第三大列
            // 第一大行   0         1         2
            // 第二大行   3         4         5
            // 第三大行   6         7         8
            // 计算出 9 个 3 * 3 小宫格区域的 index(0 ~ 8)
            // i / 3 * 3 计算出在哪一大行, j / 3 计算出在当前大行的哪一大列
            // board[7][1]：第 6 个区域
            // board[8][5]：第 7 个区域
            val index = (i / 3 * 3) + j / 3
            // 通过哈希表记录每行、每列、每个3 * 3区域的数字，当重复出现时，则为非法
            if (hashSetRow[i]?.contains(value) == true ||
                hashSetColumn[j]?.contains(value) == true ||
                hashSetThreeByThree[index]?.contains(value) == true
            ) {
                return false
            }
            hashSetRow[i]?.add(value)
            hashSetColumn[j]?.add(value)
            hashSetThreeByThree[index]?.add(value)
        }
    }
    return true
}
