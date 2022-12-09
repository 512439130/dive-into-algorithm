package leetcode

import java.util.*

fun main() {

}

/**
 *  102. 二叉树的层序遍历
 *  深度优先遍历思路（递归）
 * */
fun levelOrder(root: TreeNode?): List<List<Int>> {
    val result = arrayListOf<ArrayList<Int>>()
    // 二叉树的层序遍历：二叉树的广度优先遍历
    levelOrderRecurrence(root, result, 0)
    return result
}

fun levelOrderRecurrence(root: TreeNode?, list: ArrayList<ArrayList<Int>>, layer: Int) {
    if (root == null) return
    if (list.size == layer) {
        list.add(ArrayList())
    }
    // layer: 层数标识, 方便层序遍历时每一层的结点值都保存在一个数组区域内
    list[layer].add(root.`val`)
    levelOrderRecurrence(root.left, list, layer + 1)
    levelOrderRecurrence(root.right, list, layer + 1)
}

/**
 *  102. 二叉树的层序遍历
 *  迭代解法-队列缓存
 * */
fun levelOrder2(root: TreeNode?): List<List<Int>> {
    val result = arrayListOf<ArrayList<Int>>()
    if (root == null) return result
    // 迭代解法，通过 LinkedList（队列） 做辅助缓存
    val queue = LinkedList<TreeNode>()
    queue.offer(root)
    // queue.size: 每层结点个数
    while (queue.size > 0) {
        val temp = arrayListOf<Int>()
        for (i in 0 until queue.size) {
            // 遍历队列，当前层结点出队
            val node = queue.poll() ?: continue
            temp.add(node.`val`)
            // 下一层结点入队（当前结点的左右子树若不为空，则入队）
            if (node.left != null) queue.offer(node.left)
            if (node.right != null) queue.offer(node.right)
        }
        // 保存结果
        result.add(temp)
    }
    return result
}

