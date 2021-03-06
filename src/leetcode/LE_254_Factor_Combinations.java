package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuank on 4/10/18.
 */
public class LE_254_Factor_Combinations {
    /**
         Numbers can be regarded as product of its factors. For example,

         8 = 2 x 2 x 2;
         = 2 x 4.
         Write a function that takes an integer n and return all possible combinations of its factors.

         Note:
         You may assume that n is always positive.
         Factors should be greater than 1 and less than n.
         Examples:
         input: 1
         output:
         []

         input: 37
         output:
         []

         input: 12
         output:
         [
         [2, 6],
         [2, 2, 3],
         [3, 4]
         ]

         input: 32
         output:
         [
         [2, 16],
         [2, 2, 8],
         [2, 2, 2, 4],
         [2, 2, 2, 2, 2],
         [2, 4, 4],
         [4, 8]
         ]
     */

    /**
     * Variation :
     * All_Ways_To_Multiple_Smaller_Numbers
     *
     * Difference is that "1" also counts as a factor.
     *
     * 119 ms, TLE on lintcode
     */
    class Solution1 {
        public List<List<Integer>> getFactors(int n) {
            List<List<Integer>> res = new ArrayList<>();
            //!!! 1不是因子，所以必须从2开始
            helper(res, new ArrayList<>(), 2, n);
            return res;
        }

        public void helper(List<List<Integer>> res, List<Integer> list, int start, int n) {
            /**
             * !!!
             *  "> 1".排除因子中只有n本身，如，n=12, 如果用"> 0",则[12]会出现在res中。
             *  **/
            if (n == 1 && list.size() > 1) {
                res.add(new ArrayList<>(list));
                //!!! 必须return
                return;
            }

            for (int i = start; i <= n; i++) {
                if (n % i == 0) {
                    list.add(i);
                    /**!!!
                     * 同一因子可以重复出现，所以继续用i作为start.
                     *
                     * !!!
                     * "n / i"
                     **/
                    helper(res, list, i, n / i);
                    list.remove(list.size() - 1);
                }
            }

        }
    }

    /**
     * Much faster
     *
     * 1 ms
     */
    public class Solution_JiuZhang {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> getFactors(int n) {
            if (n <= 1) return ans;

            dfs(2, n);
            return ans;
        }

        void dfs(int start, int remain) {
            if (remain == 1) {
                if (path.size() != 1) {
                    /**
                     * !!!
                     * Deep copy
                     */
                    ans.add(new ArrayList<>(path));
                }
                return;
            }

            for (int i = start; i <= remain; i++) {
                /**
                 * same effect as "for (int i = start; i <= (int) Math.sqrt(n); i++) "
                 */
                if (i > remain / i) {
                    break;
                }
                if (remain % i == 0) {
                    path.add(i);                  //进栈
                    dfs(i, remain / i);
                    path.remove(path.size() - 1); //出栈
                }
            }

            /**
             * n = 26
             * factor 2, remain 13, when i = 6, break, come here, add 13 to path
             */
            path.add(remain);
            dfs(remain, 1);
            path.remove(path.size() - 1);
        }
    }
}
