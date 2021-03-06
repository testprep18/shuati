package leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yuank on 3/8/18.
 */
public class LE_94_Binary_Tree_Inorder_Traversal {
    /**
        Given a binary tree, return the inorder traversal of its nodes' values.

        For example:
        Given binary tree [1,null,2,3],
           1
            \
             2
            /
           3
        return [1,3,2].
     */

    /**
        LE_94  Inorder
        LE_144 Preorder
        LE_145 Postorder
        LE_102 Levelorder
        LE_107 Levelorder II
        LE_103 Levelorder Zigzag
        LE_314 Verticalorder
     **/

    //Recursive version
    //Time and Space : O(n)
    class Solution_DFS {
        public List<Integer> inorderTraversal1(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;
            helper(root, res);
            return res;
        }

        public void helper(TreeNode root, List<Integer> res) {
            if (root == null) return;
            helper(root.left, res);
            res.add(root.val);
            helper(root.right, res);
        }
    }

    //Iterative Version
    //Time and Space : O(n)
    class Solution_BFS {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;

            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;

            //while 之前，不push
            //"||", NOT "&&"
            while (cur != null || !stack.isEmpty()) {
                while (cur != null) {
                    //push cur, not cur.left
                    stack.push(cur);
                    cur = cur.left;
                }
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }

            return res;
        }
    }
}
