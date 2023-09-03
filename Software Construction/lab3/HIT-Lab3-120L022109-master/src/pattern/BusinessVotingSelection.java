package pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 适用于商业表决的遴选器
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-06-22 22:44
 */
public class BusinessVotingSelection implements SelectionStrategy {
    @Override
    public <C> Map<C, Double> select(Map<C, Double> statistics, int quantity) {
        Map<C, Double> result = new HashMap<>();
        for (C c :
                statistics.keySet()) {
            if (statistics.get(c) * 3 >= 2) result.put(c, statistics.get(c));
        }
        return result;
    }
}