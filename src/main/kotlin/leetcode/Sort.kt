package leetcode

import log.logArray

fun main() {
//    val array = intArrayOf(3, 6, 1, 7, 2, 5, 8)
//    log.logArray(sortArray(array))
//
//    val array = intArrayOf(1, 2, 3, 4, 5)
//    val listNode = leetcode.arrayToListNode(array)
//    log.logArray(rotateRight(listNode, 2))

    val array = intArrayOf(2, 1, 4, 7, 4, 8, 3, 6, 4, 8)
    logArray(plusOne2(array))
}

fun sortArray(nums: IntArray): IntArray {
//    bubbleSort(nums)
//    bubbleSortOptimized(nums)
//    quickSort(nums, 0, nums.size - 1)
//    selectSort(nums)
    insertSort(nums)
//    shellSort(nums)
//    mergeSort(nums)
//    heapSort(nums)
    return nums
}

/** 冒泡排序 */
fun bubbleSort(nums: IntArray) {
    for (i in 0 until nums.size - 1) {
        for (j in 0 until nums.size - 1 - i) {
            // 规则：由大到小整理顺序
            if (nums[j] > nums[j + 1]) {
                val temp = nums[j]
                nums[j] = nums[j + 1]
                nums[j + 1] = temp
            }
        }
    }
}

/** 冒泡排序（优化） */
fun bubbleSortOptimized(nums: IntArray) {
    var needPosition = nums.size
    for (i in 0 until nums.size - 1) {
        // 如一次冒泡调整数组并未变化，则证明该数组已经是有序数组
        var changeFlag = false
        for (j in 0 until needPosition - 1) {
            // 规则：由大到小整理顺序
            if (nums[j] > nums[j + 1]) {
                val temp = nums[j]
                nums[j] = nums[j + 1]
                nums[j + 1] = temp
                // 更新排序遍历边界：已排序位置
                needPosition = j + 1
                changeFlag = true
            }
        }
        if (!changeFlag) break
    }
}

/** 快速排序 */
fun quickSort(nums: IntArray, left: Int, right: Int) {
    // 规则（升序）：每趟递归独立分割为左右2个部分，左边部分全部小于右边部分，子数组的不断递归，逐渐将大数组排序转化为子数组的排序
    // T(n) = NLogN
    // S(n) = logN

    // 递归结束条件 1（空数组，只有一个数字则不需排序）
    if (nums.size == 1) return
    // 递归结束条件 2（left >= right）
    if (left >= right) return
    // 获取切分点元素下标
    val partition = doPartition(nums, left, right)
    // 递归调用左半数组-排序
    quickSort(nums, left, partition - 1)
    // 递归调用右半数组-排序
    quickSort(nums, partition + 1, right)
}

/**
 * 切分为 3 段（左边，中间，右边）并获取切分点数组下标，提供递归边界控制
 * 基准数据在左边（首部），则先遍历右指针
 * 基准数据在右边（尾部），则先遍历左指针zt
 */
fun doPartition(nums: IntArray, left: Int, right: Int): Int {
    // 定义切分点为数组的首元素
    val pivot = nums[left]
    var leftPoint = left
    var rightPoint = right
    while (leftPoint < rightPoint) {
        // 每轮右指针先走
        // 右指针遍历(找到首个比基点大的值)
        while ((leftPoint < rightPoint) && nums[rightPoint] >= pivot) rightPoint--
        // 左指针遍历(找到首个比基点小的值)
        while ((leftPoint < rightPoint) && nums[leftPoint] <= pivot) leftPoint++
        // 交换左指针与右指针值
        if (leftPoint < rightPoint) swap(nums, leftPoint, rightPoint)
    }
    // 基准元素与左指针元素交换
    swap(nums, left, leftPoint)
    return leftPoint
}

fun selectSort(nums: IntArray) {
    for (i in nums.indices) {
        var min = i
        for (j in i + 1 until nums.size) {
            if (nums[j] < nums[min]) {
                // 每一趟循环找出当前数组范围（待排序的部分数组）中最小的数并记录
                // 规则（min-升序）：数组的左侧为已调整顺序
                min = j
            }
        }
        // 若最小值更新，则交换当前 i 与 min 的位置
        if (min != i) swap(nums, i, min)
    }
}

fun insertSort(nums: IntArray) {
    for (i in nums.indices) {
        // 规则（min-升序）: 初始化左侧第 1 个数字为已排序数组，将已排序的下 1 个数字插入到有序数组中去
        // 特征: 数组左侧不断有序化
        // 3, 6, 1, 7, 2, 5, 8
        // [3] 插入 6 ==>> [3,6]
        // [3,6] 插入 1 ==>> [3,1,6] ==>> [1,3,6]
        // [1,3,6] 插入 7 ==>> [1,3,6,7]
        // [1,3,6,7] 插入 2 ==>> [1,3,6,2,7] ==>> [1,3,2,6,7] ==>> [1,2,3,6,7]
        // [1,2,3,6,7] 插入 5 ==>> [1,2,3,6,5,7] ==>> [1,2,3,5,6,7]
        // [1,2,3,5,6,7] 插入 8 ==>> [1,2,3,5,6,7,8]

        // 倒叙遍历已排序数组查找插入位置
        for (j in i downTo 1) {
            if (nums[j] > nums[j - 1]) break
            if (nums[j] < nums[j - 1]) swap(nums, j, j - 1)
        }
    }
}

fun shellSort(nums: IntArray) {

}

fun mergeSort(nums: IntArray) {

}

fun heapSort(nums: IntArray) {

}