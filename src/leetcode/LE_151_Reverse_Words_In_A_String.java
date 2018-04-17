package leetcode;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by yuank on 3/19/18.
 */
public class LE_151_Reverse_Words_In_A_String {
    /*
        Given an input string, reverse the string word by word.

        For example,
        Given s = "the sky is blue",
        return "blue is sky the".
     */

    //Time : O(n), Space : O(n)
    public String reverseWords1(String s) {
        if (s == null || s.length() == 0) return s;

        String[] tokens = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = tokens.length - 1; i >= 0; i--) {
            sb.append(tokens[i]).append(" ");
        }

        return sb.toString().trim();
    }

    //Time : O(n), Space : O(n)
    public String reverseWords2(String s) {
         String[] words = s.trim().split(" +");
         Collections.reverse(Arrays.asList(words));
         return String.join(" ", words);
     }

    //Time : O(n), Space : O(1)
    public String reverseWords3(String s) {
        if (null == s || s.length() == 0) return "";
        int n = s.length();

        char[] ch = s.toCharArray();
        reverse(ch, 0, n - 1);

        int i = 0, j = 0;

        while (i < n) {
            //!!!notice "i<j" and "j<i", a clever way of moving both pointers forward
            while (i < j || i < n && ch[i] == ' ') i++;
            while (j < i || j < n && ch[j] != ' ') j++;
            reverse(ch, i, j - 1);
        }

        return cleanup(ch);
    }

    private String cleanup(char[] ch) {
        int i = 0, j = 0;
        int n = ch.length;

        while (j < n) {
            while (j < n && ch[j] == ' ') j++;
            while (j < n && ch[j] != ' ') ch[i++] = ch[j++];
            while (j < n && ch[j] == ' ') j++;
            //!!!
            if (j < n) ch[i++] = ' ';
        }

        return new String(ch).substring(0, i);
    }

    private void reverse(char[] ch, int i, int j) {
        while (i < j) {
            char temp = ch[i];
            ch[i++] = ch[j];
            ch[j--] = temp;
        }
    }


}
