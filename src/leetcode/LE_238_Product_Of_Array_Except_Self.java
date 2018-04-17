package leetcode;

/**
 * Created by yuank on 4/3/18.
 */
public class LE_238_Product_Of_Array_Except_Self {
    /*
        Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of num
         except nums[i].

        Solve it without division and in O(n).

        For example, given [1,2,3,4], return [24,12,8,6].

        Follow up:
        Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
     */

    /*
      nums[1,2,3,4]

      res [1,1,2,6]
      ----->
             <--------
      nums[1, 2, 3, 4]
      res [1, 1, 2, 6]
    right 24,24,12, 4, 1
      res 24,12, 8, 6
    */
    //Tim and Space : O(n)
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return nums;

        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int right = 1;
        for (int i = nums.length - 1 ; i >=0; i--) {
            res[i] *= right;
            right *= nums[i];
        }

        return res;
    }
}
