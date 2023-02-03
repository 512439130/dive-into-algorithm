package leetcode.greedy

import log.log


fun main() {
    log(canJump(intArrayOf(2, 2, 2, 1, 1, 0)))
    log(canJump(intArrayOf(2, 3, 1, 1, 4)))
    log(canJump(intArrayOf(3, 2, 1, 0, 4)))
    log(canJump(intArrayOf(2, 2, 0, 0, 1)))
}

/**
 *  55. 跳跃游戏
给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
判断你是否能够到达最后一个下标。

示例 1：
输入：nums = [2,3,1,1,4]
输出：true
解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。

示例 2：
输入：nums = [3,2,1,0,4]
输出：false
解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。

提示：
1 <= nums.length <= 3 * 104
0 <= nums[ i ] <= 105
 */

fun canJump(nums: IntArray): Boolean {
    // 只有一个元素就是最后一个下标位置
    if (nums.size == 1) return true
    // 最远可到达位置
    var cover = 0
    var i = 0
    while (true) {
        // 不在可达覆盖位置
        if (i > cover) break
        // i + nums[i] : 当前位置 + 最大跳跃数量
        cover = Math.max(cover, i + nums[i])
        // 当最远可达位置 >= 最后一个下标位置 ==>> 则最远可到达位置覆盖终点
        if (cover >= nums.size - 1) return true
        i++
    }
    return false
}