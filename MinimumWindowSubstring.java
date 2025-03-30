import java.util.*;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        if (s.length() < t.length() || s.length() == 0) return "";

        Map<Character, Integer> targetFreq = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetFreq.put(c, targetFreq.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0, minLength = Integer.MAX_VALUE;
        int requiredChars = targetFreq.size();
        int formedChars = 0;
        int startIndex = 0;

        Map<Character, Integer> windowFreq = new HashMap<>();

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            windowFreq.put(rightChar, windowFreq.getOrDefault(rightChar, 0) + 1);

            if (targetFreq.containsKey(rightChar) && 
                windowFreq.get(rightChar).intValue() == targetFreq.get(rightChar).intValue()) {
                formedChars++;
            }

            while (left <= right && formedChars == requiredChars) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    startIndex = left;
                }

                char leftChar = s.charAt(left);
                windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);

                if (targetFreq.containsKey(leftChar) && 
                    windowFreq.get(leftChar) < targetFreq.get(leftChar)) {
                    formedChars--;
                }

                left++;
            }
            right++;
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLength);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println("Minimum Window Substring: " + minWindow(s, t));  
        // Output: "BANC"
    }
}
