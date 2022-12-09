package leetcode

fun main() {
    val array = intArrayOf(1, 2, 3)

//    leetcode.hasPathSum()
}

fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    // 到达底部
    if (root == null) return false
    // 当 targetSum == node.val 时，该节点向上的路径和满足条件
    if (root.left == null && root.right == null) return targetSum == root.`val`
    // 逆向：target - node.val，递归深度遍历根节点的左右子树
    return hasPathSum(root.left, targetSum - root.`val`) || hasPathSum(root.right, targetSum - root.`val`)
}