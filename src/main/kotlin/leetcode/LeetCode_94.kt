package leetcode

import java.util.*
import kotlin.collections.ArrayList

fun main() {

}

/** 94. 二叉树的中序遍历 */
fun inorderTraversal(root: TreeNode?): List<Int> {
    val result = arrayListOf<Int>()
    recurrence(root, result)
    return result
}

/**
 * 递归解法
 * 入参 root：二叉树广度遍历构造
 */
fun recurrence(root: TreeNode?, list: ArrayList<Int>) {
    if (root == null) return
    recurrence(root.left, list)
    list.add(root.`val`)
    recurrence(root.right, list)
}

/**
 *  94. 二叉树的中序遍历
 *  迭代解法-Stack
 * */
fun inorderTraversal2(root: TreeNode?): List<Int> {
    val result = arrayListOf<Int>()
    // 使用 Stack 辅助缓存每层树的结点信息
    // 中序遍历规则：左-中-右
    //
    val stack = Stack<TreeNode>()
    var rootNode = root
    while (rootNode != null || stack.isNotEmpty()) {
        // 1. 循环遍历寻找当前结点最左子树的结点（左子树为 null 时）
        while (rootNode != null) {
            stack.push(rootNode)
            rootNode = rootNode.left
        }
        // 2. 取当前结点值
        rootNode = stack.pop()
        result.add(rootNode.`val`)
        // 3.  取当前结点右子树，继续下一趟遍历
        rootNode = rootNode.right
    }
    return result
}
