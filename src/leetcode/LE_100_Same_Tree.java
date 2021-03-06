package leetcode;

import common.TreeNode;

/**
 * Created by yuank on 3/9/18.
 */
public class LE_100_Same_Tree {
    /**
        Given two binary trees, write a function to check if they are the same or not.

        Two binary trees are considered the same if they are structurally identical and the nodes have the same value.


        Example 1:

        Input:     1         1
                  / \       / \
                 2   3     2   3

                [1,2,3],   [1,2,3]

        Output: true
        Example 2:

        Input:     1         1
                  /           \
                 2             2

                [1,2],     [1,null,2]

        Output: false
        Example 3:

        Input:     1         1
                  / \       / \
                 2   1     1   2

                [1,2,1],   [1,1,2]

        Output: false
        Seen this question in a real intervie
     */

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        //!!!Those 2 if can not exchange places
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
