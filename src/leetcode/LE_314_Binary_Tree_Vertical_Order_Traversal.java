package leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yuank on 5/3/18.
 */
public class LE_314_Binary_Tree_Vertical_Order_Traversal {
    /**
     * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
     *
     * If two nodes are in the same row and column, the order should be from left to right.
     *
     * Examples 1:
     *
     * Input: [3,9,20,null,null,15,7]
     *
     *    3
     *   /\
     *  /  \
     *  9  20
     *     /\
     *    /  \
     *   15   7
     *
     * Output:
     *
     * [
     *   [9],
     *   [3,15],
     *   [20],
     *   [7]
     * ]
     * Examples 2:
     *
     * Input: [3,9,8,4,0,1,7]
     *
     *       3
     *      /\
     *     /  \
     *     9   8
     *    /\  /\
     *   /  \/  \
     *  4  01   7
     *
     * Output:
     *
     * [
     *   [4],
     *   [9],
     *   [3,0,1],
     *   [8],
     *   [7]
     * ]
     * Examples 3:
     *
     * Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
     *
     *      3
     *     /\
     *    /  \
     *    9   8
     *   /\  /\
     *  /  \/  \
     *  4  01   7
     *     /\
     *    /  \
     *    5   2
     *
     * Output:
     *
     * [
     *   [4],
     *   [9,5],
     *   [3,0,1],
     *   [8,2],
     *   [7]
     * ]
     *
     * Medium
     */

    /**
     * Similar to LE_655_Print_Binary_Tree, but not the same, can't use its solution for this problem directly,
     * because of the root position alignment.
     *
     * The similarity is that we need to first run a pass for the whole tree to get dimension of the tree.
     * Here, we start from root, get the width of the left subtree wl and right subtree wr, then we know how
     * many levels we need to process (wl + wr). For 655, we need to find depth of the tree, then we know the
     * max length of the list (2 ^ h - 1)
     *
     * Time and Space : O(n)
     **/
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        int[] range = new int[2];
        findRange(root, range, 0);

        //!!!
        for (int i = range[0]; i <= range[1]; i++) {
            res.add(new ArrayList<Integer>());
        }

        /**
         * In BFS, need both the node object and its col index
         * we can create a class Pair and use one queue, Or
         * we just use 2 queues and offer and poll elements from
         * those 2 queues at the same time
         */
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();

        nodes.offer(root);

        /**
             Once we find the min we create n number of lists starting from min till max. Now when doing BFS,
             we need to start adding the elements to the correct lists which will be between 0 to n.
             If you think about where the root will end up (which index in the cols list), it will be in the index at Math.abs(min).
             If min = -3, that means we have 3 columns before the root node (0,1,2 will be occupied by nodes of the root's left subtree),
             hence root will be in the |min| index.

             So -range[0] is simply a shift of index positions so all nodes end up in their corresponding lists.
         **/
        cols.offer(-range[0]);

        while (!nodes.isEmpty()) {
            TreeNode curNode = nodes.poll();
            int curCol = cols.poll();
            res.get(curCol).add(curNode.val);

            /**
             * !!!
             * "curl - 1" for left, and "cur + 1" for right
             */
            if (curNode.left != null) {
                nodes.offer(curNode.left);
                cols.offer(curCol - 1);
            }

            if (curNode.right != null) {
                nodes.offer(curNode.right);
                cols.offer(curCol + 1);
            }
        }

        return res;
    }

    /**
     * !!!
     * range[0] : offset from root location to the left most child (negative number)
     * range[1] : offset from root location fo the right most child (positive number)
     *
     * example:
     * 5 vertical levels in range[] and its index
     *
     * 0  1  2 3  4
     * -2,-1,0 1, 2
     *      3            list Idx [2]
     *     /\
     *    /  \
     *    9   8                  [1, 3]
     *   /\  /\
     *  /  \/  \
     *  4  01   7            [0, 2] [2, 4]
     *     /\
     *    /  \
     *    5   2                 [1] [3]
     *
     *  answer:
     * [
     *   [4],
     *   [9,5],
     *   [3,0,1],
     *   [8,2],
     *   [7]
     * ]
     *
     */
    public void findRange(TreeNode root, int[] range, int col) {
        if (root == null) return;

        range[0] = Math.min(col, range[0]);
        range[1] = Math.max(col, range[1]);

        findRange(root.left, range, col - 1);
        findRange(root.right, range, col + 1);
    }
}
