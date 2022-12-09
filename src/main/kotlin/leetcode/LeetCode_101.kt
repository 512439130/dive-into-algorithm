package leetcode

import java.util.*

fun main() {

}

/** 101. 对称二叉树 */
fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) return true
    if (p == null || q == null) return false
    if (p.`val` != q.`val`) return false
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
}

fun isSymmetric(root: TreeNode?): Boolean {
    if (root == null) return true
    // 递归解法
    // 根节点作为对称轴，无需对比
    // 对比根节点左子树和右子树
    return compareTree(root.left, root.right)
}

fun compareTree(node1: TreeNode?, node2: TreeNode?): Boolean {
    // 2 个结点都为空，结果对称
    if (node1 == null && node2 == null) return true
    // 2 个结点只有一个空，结果不对称
    if (node1 == null || node2 == null) return false
    // 2 个结点值不同，结果不对称
    if (node1.`val` != node2.`val`) return false
    // 继续递归遍历每个结点的叶子结点是否对称
    // 需要注意对称比较规则: compare(node1.left, node2.right) && compare(node1.right, node2.left)
    return compareTree(node1.left, node2.right) && compareTree(node1.right, node2.left)
}

fun isSymmetric2(root: TreeNode?): Boolean {
    if (root == null) return true
    val queue = LinkedList<TreeNode>()
    queue.offer(root)
    queue.offer(root)
    // 迭代解法
    while (queue.isNotEmpty()) {
        val node1 = queue.poll()
        val node2 = queue.poll()
        // 2 个结点都为空，结果对称
        if (node1 == null && node2 == null) continue
        // 2 个结点只有一个空，结果不对称
        if (node1 == null || node2 == null) return false
        // 2 个结点值不同，结果不对称
        if (node1.`val` != node2.`val`) return false
        // 入队下一层
        queue.offer(node1.left);
        queue.offer(node2.right);
        queue.offer(node1.right);
        queue.offer(node2.left);
    }
    return true
}

