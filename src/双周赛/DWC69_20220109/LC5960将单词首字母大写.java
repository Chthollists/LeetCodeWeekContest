package 双周赛.DWC69_20220109;

/**
 * @author Chthollists email:
 * @create 2022-01-09 15:51
 *
 * 给你一个字符串 title ，它由单个空格连接一个或多个单词组成，每个单词都只包含英文字母。请你按以下规则将每个单词的首字母 大写 ：
 * 如果单词的长度为 1 或者 2 ，所有字母变成小写。
 * 否则，将单词首字母大写，剩余字母变成小写。
 * 请你返回 大写后 的 title
 * 输入：title = "capiTalIze tHe titLe"
 * 输出："Capitalize The Title"
 */
public class LC5960将单词首字母大写 {

    public String capitalizeTitle(String title) {
        if(title == null || title.length() == 0) return null;
        String[] words = title.split(" ");
        StringBuilder sb = new StringBuilder();
        String lowerWord = "";
        for(String word : words) {
            if(word.length() == 0) continue;
            if(word.length() <= 2) {
                sb.append(word.toLowerCase()).append(" ");
            }else {
                lowerWord = word.toLowerCase();
                sb.append((char) (lowerWord.charAt(0) + 'A' - 'a')).append(lowerWord.substring(1)).append(" ");
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
