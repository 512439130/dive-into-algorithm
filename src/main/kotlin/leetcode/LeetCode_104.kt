package leetcode

import java.util.*

fun main() {

}

/**
 * 104. 二叉树的最大深度
 * 递归解法
 */
fun maxDepth(root: TreeNode?): Int {
    if (root == null) return 0
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1
}

/**
 * 104. 二叉树的最大深度
 * 迭代解法
 */
fun maxDepth2(root: TreeNode?): Int {
    if (root == null) return 0
    var depth = 0
    // 迭代解法-队列辅助缓存
    val queue = LinkedList<TreeNode>()
    queue.offer(root)
    while (queue.isNotEmpty()) {
        for (i in 0 until queue.size) {
            val node = queue.poll() ?: continue
            // 是否存在左右孩子，存在则入队
            if (node.left != null) queue.offer(node.left)
            if (node.right != null) queue.offer(node.right)
        }
        depth++
    }
    return depth
}

