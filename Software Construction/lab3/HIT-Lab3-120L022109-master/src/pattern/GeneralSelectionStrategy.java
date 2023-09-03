package pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ���治����ͶƱ��ѡ��
 * ֱ��ѡ��ǰk����������ͬʱ���������
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-06-22 13:21
 */
public class GeneralSelectionStrategy implements SelectionStrategy {
    @Override
    public <C> Map<C, Double> select(Map<C, Double> statistics, int quantity) {
        Map<C, Double> result = new HashMap<>();
        List<Map.Entry<C, Double>> list = new ArrayList<>(statistics.entrySet());
        //��������
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        for (int i = 0; i < quantity; i++) {
            result.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return result;
    }
}