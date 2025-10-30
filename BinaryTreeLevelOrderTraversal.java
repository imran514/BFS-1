import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 102: Binary Tree Level Order Traversal
 * <p>
 * Approaches implemented:
 * <p>
 * 1. BFS (Breadth-First Search) Approach (levelOrderBFS):
 * - Uses a queue to traverse the tree level by level.
 * - For each level, process all nodes, add their children to the queue, and collect their values.
 * - Time Complexity: O(N), where N is the number of nodes.
 * - Space Complexity: O(N) for the queue and result list.
 * <p>
 * 2. DFS (Depth-First Search) Approach (levelOrderDFS):
 * - Uses recursion to traverse the tree, passing the current depth.
 * - Adds node values to the corresponding depth list in the result.
 * - Time Complexity: O(N), where N is the number of nodes.
 * - Space Complexity: O(H), where H is the height of the tree (recursion stack).
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                list.add(node.val);
            }
            result.add(list);
        }
        return result;
    }

    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        helper(root, 0, result);
        return result;
    }

    private void helper(TreeNode node, int depth, List<List<Integer>> result) {
        //base
        if (node == null) return;

        //logic
        if (depth == result.size()) {
            result.add(new ArrayList<>());
        }
        result.get(depth).add(node.val);

        helper(node.left, depth + 1, result);
        helper(node.right, depth + 1, result);

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
