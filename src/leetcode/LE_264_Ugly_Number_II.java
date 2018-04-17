package leetcode;

/**
 * Created by yuank on 4/13/18.
 */
public class LE_264_Ugly_Number_II {
    /**
         Write a program to find the n-th ugly number.

         Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
         For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

         Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
     */

    /**
     分别记录上一个和2,3,5相乘得到合法ugly number的nums元素的下标，分别乘以2,3,5,取最小的数为当前的ugly number, 然后看是哪一个下标，加一。
     Time and Space : O(n)
     * @param n
     * @return
     */

    public int nthUglyNumber(int n) {
        int[] nums = new int[n];
        int idx2 = 0, idx3 = 0, idx5 = 0;
        nums[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.min(Math.min(nums[idx2] * 2, nums[idx3] * 3), nums[idx5] * 5);

            if (nums[i] == nums[idx2] * 2) idx2++;
            if (nums[i] == nums[idx3] * 3) idx3++;
            if (nums[i] == nums[idx5] * 5) idx5++;
        }

        return nums[n - 1];
    }
}
