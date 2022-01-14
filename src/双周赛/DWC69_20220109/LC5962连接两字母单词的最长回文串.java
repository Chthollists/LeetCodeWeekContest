package 双周赛.DWC69_20220109;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Chthollists email:
 * @create 2022-01-09 16:19
 * <p>
 * 给你一个字符串数组 words 。words 中每个元素都是一个包含 两个 小写英文字母的单词。
 * 请你从 words 中选择一些元素并按 任意顺序 连接它们，并得到一个 尽可能长的回文串 。每个元素 至多 只能使用一次。
 * 请你返回你能得到的最长回文串的 长度 。如果没办法得到任何一个回文串，请你返回 0 。
 * 回文串 指的是从前往后和从后往前读一样的字符串。
 * 输入：words = ["lc","cl","gg"]
 * 输出：6
 */
public class LC5962连接两字母单词的最长回文串 {
    public static void main(String[] args) {
        String[] s = new String[]{"lc", "cl", "gg"};
        int res = new LC5962连接两字母单词的最长回文串().longestPalindrome(s);
        System.out.println(res);
    }

    public int longestPalindrome(String[] words) {
        if (words == null || words.length == 0) return 0;
        Map<String, Integer> wordMap = new HashMap<>(); // 存放不是回文串的word
        Map<String, Integer> palindromeMap = new HashMap<>(); // 存放是回文串的word
        int palindromeLen = 0;

        String reverse = "";
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            reverse = sb.append(word).reverse().toString();
            // 分类讨论，本身就是回文串的word，直接添加到map中，后面在进行分析
            if (word.equals(reverse)) { //  if (word.charAt(0) == word.charAt(1))
                palindromeMap.put(word, palindromeMap.getOrDefault(word, 0) + 1);
            } else {
                // 本身不是回文串的word，去匹配对应的reverse结构
                if (wordMap.get(reverse) != null && wordMap.get(reverse) > 0) {
                    // 匹配成功，可以添加到最长回文串的两端，len + 2 * len(word)
                    palindromeLen += word.length() << 1;
                    wordMap.put(reverse, wordMap.get(reverse) - 1);
                } else {
                    // 匹配失败，将当前word加入wordMap
                    wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                }
            }
        }

        // 分析回文word的情况，对应palindromeMap
        int maxSingleLen = 0;
        Set<Map.Entry<String, Integer>> wordEntries = palindromeMap.entrySet();
        for (Map.Entry<String, Integer> wordEntry : wordEntries) {
            // 偶数个回文word，直接全部计入最长回文串长度中
            if ((wordEntry.getValue() & 1) == 0) {
                palindromeLen += wordEntry.getKey().length() * wordEntry.getValue();
            } else {
                // 奇数个回文word，只能直接将(count - 1)个word计入最长回文串长度中，最后一个与其他单独的word进行长度比较，选择最长的
                palindromeLen += wordEntry.getKey().length() * (wordEntry.getValue() - 1);
                maxSingleLen = Math.max(maxSingleLen, wordEntry.getKey().length());
            }
        }
        // 将选择的最长的单个回文word计入最长回文串长度中
        palindromeLen += maxSingleLen;
        return palindromeLen;
    }


    public int longestPalindrome2(String[] words) {
        if (words == null || words.length == 0) return 0;
        Map<String, Integer> wordMap = new HashMap<>(); // 存放不是回文串的word
        int[] hash = new int[26];
        int palindromeLen = 0;
        String reverse = "";
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            reverse = sb.append(word).reverse().toString();
            // 分类讨论，本身就是回文串的word，直接计入最长回文串
            if (word.equals(reverse)) { //  if (word.charAt(0) == word.charAt(1))
                hash[word.charAt(0) - 'a']++;
            } else {
                // 本身不是回文串的word，去匹配对应的reverse结构
                if (wordMap.get(reverse) != null && wordMap.get(reverse) > 0) {
                    // 匹配成功，可以添加到最长回文串的两端，len + 2 * len(word)
                    palindromeLen += 4;
                    wordMap.put(reverse, wordMap.get(reverse) - 1);
                } else {
                    // 匹配失败，将当前word加入wordMap
                    wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                }
            }
        }
        boolean last = false;
        for (int count : hash) {
            if ((count & 1) == 0) {
                palindromeLen += 2 * count; // word.length()
            } else {
                palindromeLen += 2 * (count - 1); // word.length()
                last = true;
            }
        }
        if(last) palindromeLen += 2;
        return palindromeLen;
    }
}
