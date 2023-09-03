package pattern;

import java.util.*;

/**
 * 适用于代表选举的遴选器
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-06-22 23:46
 */
public class ElectionSelection implements SelectionStrategy {
    @Override
    public <C> Map<C, Double> select(Map<C, Double> statistics, int quantity) {
        Map<C, Double> result = new HashMap<>();
        List<Map.Entry<C, Double>> list = new ArrayList<>(statistics.entrySet());
        //降序排序
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        int i;

        for (i = 0; i < quantity; i++) {
            result.put(list.get(i).getKey(), list.get(i).getValue());

        }
        if (list.get(i) == list.get(i - 1)) {
            Iterator<C> iterator = result.keySet().iterator();
            while (iterator.hasNext()) {
                C c = iterator.next();
                if (result.get(c).equals(list.get(i).getValue())) {
                    result.remove(c);
                    iterator = result.keySet().iterator();
                }
            }
        }
        return result;
    }
}