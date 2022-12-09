package leetcode

import log.logArray
import java.util.*

fun main() {
    val array = intArrayOf(1, 6, 4, 6, 8)
    logArray(dailyTemperatures(array))
}

/**
 * 739. 每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: result = [1,1,4,2,1,1,0,0]
 */

fun dailyTemperatures(temperatures: IntArray): IntArray {
    val result = IntArray(temperatures.size) { 0 }
    // 单调递增栈，栈记录元素数组下标
    // stack = [] i = 0, array[i] < stack.pop() ==>> stack.push(0) result = [0,0,0,0,0]
    // stack = [0] i = 1, while -> start array[i] > stack.pop() ==>> result = i - stack.pop() = 1 - 0 = 1 while -> end stack.push(1) ==>> result = [1,0,0,0,0]
    // stack = [1] i = 2, array[i] < stack.pop() ==>> stack.push(2)
    // stack = [2,1] i = 3, while -> start array[i] > stack.pop() ==>> result = 3 - 2 = 1 while -> next ==>> result = [1,0,1,0,0]
    // stack = [1] i = 3, while -> next array[i] == stack.pop() ==>> while -> end stack.push(3)
    // stack = [3,1] i = 4, while -> start array[i] > stack.pop() ==>> result = 4 - 3 = 1 while -> next ==>> result = [1,0,1,1,0]
    // stack = [1] i = 4, while -> next array[i] > stack.pop() ==>> result = 4 - 1 = 3 while -> end stack.push(4) ==>> result = [1,3,1,1,0]
    // result = [1,3,1,1,0]
    val stack = Stack<Int>()
    for (i in temperatures.indices) {
        while (stack.isNotEmpty() && temperatures[i] > temperatures[stack.peek()]) {
            result[stack.peek()] = i - stack.pop()
        }
        stack.push(i)
    }
    return result
}