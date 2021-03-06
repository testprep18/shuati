package leetcode;

import java.util.TreeMap;

public class LE_1146_Snapshot_Array {
    /**
     * Implement a SnapshotArray that supports the following interface:
     *
     * SnapshotArray(int length) initializes an array-like data structure with the given length.
     * Initially, each element equals 0.
     * void set(index, val) sets the element at the given index to be equal to val.
     * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
     * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
     *
     *
     * Example 1:
     *
     * Input: ["SnapshotArray","set","snap","set","get"]
     * [[3],[0,5],[],[0,6],[0,0]]
     * Output: [null,null,0,null,5]
     * Explanation:
     * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
     * snapshotArr.set(0,5);  // Set array[0] = 5
     * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
     * snapshotArr.set(0,6);
     * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
     *
     *
     * Constraints:
     *
     * 1 <= length <= 50000
     * At most 50000 calls will be made to set, snap, and get.
     * 0 <= index < length
     * 0 <= snap_id < (the total number of times we call snap())
     * 0 <= val <= 10^9
     *
     * Medium
     */

    /**
     * Array of TreeMaps
     * key : snapId
     * value : value set by "set()"
     *
     * https://leetcode.com/problems/snapshot-array/discuss/350562/JavaPython-Binary-Search
     *
     * Intuition
     * Instead of copy the whole array,
     * we can only record the changes of set.
     *
     *
     * Explanation
     * The idea is, the whole array can be large,
     * and we may take the snap tons of times.
     * (Like you may always ctrl + S twice)
     *
     * Instead of record the history of the whole array,
     * we will record the history of each cell.
     * And this is the minimum space that we need to record all information.
     *
     * For each A[i], we will record its history.
     * With a snap_id and a its value.
     *
     * When we want to get the value in history, just binary search the time point.
     *
     *
     * Complexity
     * Time O(logS)
     * Space O(S)
     * where S is the number of set called.
     *
     * SnapshotArray(int length) is O(N) time
     * set(int index, int val) is O(1) in Python and O(logSnap) in Java
     * snap() is O(1)
     * get(int index, int snap_id) is O(logSnap)
     */
    class SnapshotArray {
        TreeMap<Integer, Integer>[] A;
        int snap_id = 0;

        public SnapshotArray(int length) {
            A = new TreeMap[length];
            for (int i = 0; i < length; i++) {
                A[i] = new TreeMap<Integer, Integer>();
                A[i].put(0, 0);
            }
        }

        public void set(int index, int val) {
            A[index].put(snap_id, val);
        }

        public int snap() {
            return snap_id++;
        }

        public int get(int index, int snap_id) {
            return A[index].floorEntry(snap_id).getValue();
        }
    }
}
