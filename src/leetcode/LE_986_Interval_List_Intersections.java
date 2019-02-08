package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LE_986_Interval_List_Intersections {
    /**
     * Given two lists of closed intervals, each list of intervals is pairwise
     * disjoint and in sorted order.
     *
     * Return the intersection of these two interval lists.
     *
     * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real
     * numbers x with a <= x <= b.  The intersection of two closed intervals is a
     * set of real numbers that is either empty, or can be represented as a closed
     * interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
     *
     * Example 1:
     *
     * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
     * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     * Reminder: The inputs and the desired output are lists of Interval objects,
     *           and not arrays or lists.
     *
     *
     * Note:
     * 0 <= A.length < 1000
     * 0 <= B.length < 1000
     * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
     *
     * Medium
     */

    /**
     * Time  : O(n)
     * Space : O(1) (not consider space of return value)
     */
    class Solution {
        public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
            List<Interval> res = new ArrayList<>();
            if (A == null || A.length == 0 || B == null || B.length == 0) return new Interval[]{};

            int i = 0;
            int j = 0;

            /**
             * !!!
             * "&&", NOT "||", otherwise will be out of boundary
             */
            while(i < A.length && j < B.length) {
                Interval a = A[i];
                Interval b = B[j];

                int start = Math.max(a.start, b.start);
                int end = Math.min(a.end, b.end);

                /**
                 * !!!
                 * "<="
                 */
                if (start <= end) {
                    res.add(new Interval(start, end));
                }

                /**
                 * !!!
                 * "<:
                 */
                if (a.end < b.end) {
                    i++;
                } else {
                    j++;
                }
            }

            /**
             * Convert list to array
             */
            return res.toArray(new Interval[res.size()]);
        }
    }
}