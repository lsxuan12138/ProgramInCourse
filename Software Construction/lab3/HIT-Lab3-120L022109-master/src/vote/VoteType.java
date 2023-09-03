package vote;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

//immutable
public class VoteType {

    // key为选项名、value为选项名对应的分数
    private Map<String, Integer> options = new HashMap<>();

    // Rep Invariants
    // options的size大于0
    // options中的key和value都不为null
    // Abstract Function
    // key为选项名、value为选项名对应的分数
    // Safety from Rep Exposure
    // 没有可以修改或者获取rep的方法

    private boolean checkRep() {
        if (options.isEmpty()) assert false;
        for (String key :
                options.keySet()) {
            if (key == null) assert false;
            if (options.get(key) == null) assert false;
        }
        return true;
    }

    public Map<String, Integer> getOptions() {
        return new HashMap<>(options);
    }

    /**
     * 创建一个投票类型对象
     * <p>
     * 可以自行设计该方法所采用的参数
     */
    public VoteType(Map<String, Integer> map) {
        options.putAll(map);
    }

    /**
     * 根据满足特定语法规则的字符串，创建一个投票类型对象
     *
     * @param regex 遵循特定语法的、包含投票类型信息的字符串（待任务12再考虑）
     */
    public VoteType(String regex) {
        Pattern pattern1 = Pattern.compile("“[\\u4e00-\\u9fa5]{1,5}”(\\|“[\\u4e00-\\u9fa5]{1,5}”)*");
        //Pattern pattern2 = Pattern.compile("(”[\\u4e00-\\u9fa5]{1,5}“\\((0|((-)?[1-9]\\d*))\\))(\\|””[\\u4e00-\\u9fa5]{1,5}“\\((0|((-)?[1-9]\\d*))\\))+");
        Pattern pattern2 = Pattern.compile("“[\\u4e00-\\u9fa5]{1,5}”\\((0|(-?[1-9]\\d*))\\)(\\|“[\\u4e00-\\u9fa5]{1,5}”\\((0|(-?[1-9]\\d*))\\))*");

        if (pattern1.matcher(regex).matches()) {
            for (String str :
                    regex.split("\\|")) {
                options.put(str.substring(1, str.length() - 1), 1);
            }
            return;
        }
        if (pattern2.matcher(regex).matches()) {
            for (String str :
                    regex.split("\\|")) {
                String[] keyValue = str.split("\\(");
                String key = keyValue[0].substring(1, keyValue[0].length() - 1);
                String value = keyValue[1].substring(0, keyValue[1].length() - 1);
                options.put(key, Integer.parseInt(value));
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    /**
     * 判断一个投票选项是否合法（用于Poll中对选票的合法性检查）
     * <p>
     * 例如，投票人给出的投票选项是“Strongly reject”，但options中不包含这个选项，因此它是非法的
     * <p>
     * 不能改动该方法的参数
     *
     * @param option 一个投票选项
     * @return 合法则true，否则false
     */
    public boolean checkLegality(String option) {
        if (option == null) throw new IllegalArgumentException();
        if (options.containsKey(option)) {
            return true;
        }
        checkRep();
        return false;
    }

    /**
     * 根据一个投票选项，得到其对应的分数
     * <p>
     * 例如，投票人给出的投票选项是“支持”，查询得到其对应的分数是1
     * <p>
     * 不能改动该方法的参数
     *
     * @param option 一个投票项取值
     * @return 该选项所对应的分数
     */
    public int getScoreByOption(String option) {
        if (option == null) throw new IllegalArgumentException();
        if (!checkLegality(option)) {
            throw new IllegalArgumentException();
        }
        checkRep();
        return options.get(option);
    }

    @Override
    public int hashCode() {
        return options.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteType voteType = (VoteType) o;
        return options.equals(voteType.options);
    }
}
