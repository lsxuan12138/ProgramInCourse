package visitor;

import poll.Poll;
import vote.IVote;

import java.util.Map;

/**
 * ��չ���ܣ�����Ϸ�ѡƱ������ѡƱ����ռ����
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-06-23 11:26
 */
public class CalculateRatioVisitor implements Visitor {
    @Override
    public <C> String visit(Poll<C> poll) {
        Map<IVote<C>, Boolean> votes = poll.getVotes();
        int count = 0;
        for (IVote<C> vote :
                votes.keySet()) {
            if (votes.get(vote)) count++;
        }
        double ratio = ((double) count) / votes.size();
        return "�Ϸ�ѡƱ������ѡƱ����ռ����Ϊ��" + ratio;
    }
}