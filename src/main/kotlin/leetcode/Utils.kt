package leetcode

/** 原地交换数组中的元素 */
fun swap(array: IntArray, left: Int, right: Int) {
    val temp = array[left]
    array[left] = array[right]
    array[right] = temp
}