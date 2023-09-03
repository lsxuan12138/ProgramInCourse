package pattern;

import auxiliary.Voter;
import vote.IVote;
import vote.VoteItem;
import vote.VoteType;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ���治����ͶƱ�Ϸ��Լ����
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-06-04 21:54
 */
public class AnonymousGeneralLegitimacyChecker implements LegitimacyChecker {
    @Override
    public <C> boolean checkLegitimacy(IVote<C> vote, List<C> candidates, VoteType voteType) {
        Set<VoteItem<C>> voteItems = vote.getVoteItems();
        Set<C> test = new HashSet<>();
        for (VoteItem<C> voteItem :
                voteItems) {
            //һ��ѡƱ�а����˲��ڱ���ͶƱ��еĺ�ѡ��
            if (!candidates.contains(voteItem.getCandidate())) {
                return false;
            }
            //һ��ѡƱ�г����˱���ͶƱ�������ѡ��ֵ
            if (!voteType.checkLegality(voteItem.getVoteValue())) {
                return false;
            }
            //һ��ѡƱ���ж�ͬһ����ѡ����Ķ��ͶƱ
            if (test.contains(voteItem.getCandidate())) {
                return false;
            } else test.add(voteItem.getCandidate());
        }
        //һ��ѡƱ��û�а�������ͶƱ��е����к�ѡ��
        if (!test.equals(new HashSet<>(candidates))) return false;

        return true;
    }

    @Override
    public <C> void checkLegitimacy(Map<IVote<C>, Boolean> votes, Map<Voter, Double> voters) {
        //������ͶƱ�˻�δͶƱ�����ܿ�ʼ��Ʊ��
        if (votes.size() < voters.size()) throw new RuntimeException("����ͶƱ��δͶƱ");
        if (votes.size() > voters.size()) throw new RuntimeException("����Ͷ�˶��ѡƱ");

    }
}