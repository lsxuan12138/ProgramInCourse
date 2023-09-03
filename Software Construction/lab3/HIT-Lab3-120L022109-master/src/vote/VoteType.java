package vote;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

//immutable
public class VoteType {

    // keyΪѡ������valueΪѡ������Ӧ�ķ���
    private Map<String, Integer> options = new HashMap<>();

    // Rep Invariants
    // options��size����0
    // options�е�key��value����Ϊnull
    // Abstract Function
    // keyΪѡ������valueΪѡ������Ӧ�ķ���
    // Safety from Rep Exposure
    // û�п����޸Ļ��߻�ȡrep�ķ���

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
     * ����һ��ͶƱ���Ͷ���
     * <p>
     * ����������Ƹ÷��������õĲ���
     */
    public VoteType(Map<String, Integer> map) {
        options.putAll(map);
    }

    /**
     * ���������ض��﷨������ַ���������һ��ͶƱ���Ͷ���
     *
     * @param regex ��ѭ�ض��﷨�ġ�����ͶƱ������Ϣ���ַ�����������12�ٿ��ǣ�
     */
    public VoteType(String regex) {
        Pattern pattern1 = Pattern.compile("��[\\u4e00-\\u9fa5]{1,5}��(\\|��[\\u4e00-\\u9fa5]{1,5}��)*");
        //Pattern pattern2 = Pattern.compile("(��[\\u4e00-\\u9fa5]{1,5}��\\((0|((-)?[1-9]\\d*))\\))(\\|����[\\u4e00-\\u9fa5]{1,5}��\\((0|((-)?[1-9]\\d*))\\))+");
        Pattern pattern2 = Pattern.compile("��[\\u4e00-\\u9fa5]{1,5}��\\((0|(-?[1-9]\\d*))\\)(\\|��[\\u4e00-\\u9fa5]{1,5}��\\((0|(-?[1-9]\\d*))\\))*");

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
     * �ж�һ��ͶƱѡ���Ƿ�Ϸ�������Poll�ж�ѡƱ�ĺϷ��Լ�飩
     * <p>
     * ���磬ͶƱ�˸�����ͶƱѡ���ǡ�Strongly reject������options�в��������ѡ�������ǷǷ���
     * <p>
     * ���ܸĶ��÷����Ĳ���
     *
     * @param option һ��ͶƱѡ��
     * @return �Ϸ���true������false
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
     * ����һ��ͶƱѡ��õ����Ӧ�ķ���
     * <p>
     * ���磬ͶƱ�˸�����ͶƱѡ���ǡ�֧�֡�����ѯ�õ����Ӧ�ķ�����1
     * <p>
     * ���ܸĶ��÷����Ĳ���
     *
     * @param option һ��ͶƱ��ȡֵ
     * @return ��ѡ������Ӧ�ķ���
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
