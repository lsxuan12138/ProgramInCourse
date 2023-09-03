package pattern;

import auxiliary.Voter;
import vote.IVote;
import vote.VoteType;

import java.util.List;
import java.util.Map;

/**
 * �Ϸ��Լ��
 */
public interface LegitimacyChecker {
    /**
     * ���һ��ѡƱ�ĺϷ���
     *
     * @param vote       Ҫ����ѡƱ����ӦΪnull
     * @param candidates ���еĺ�ѡ�ˣ������жϺϷ��ԣ���ӦΪnull
     * @param voteType   ѡƱ���ͣ������жϺϷ��ԣ���ӦΪnull
     * @param <C>        ���Ͳ���
     * @return ����vote�ĺϷ��ԡ��Ϸ�����true����֮Ϊfalse
     */
    <C> boolean checkLegitimacy(IVote<C> vote, List<C> candidates, VoteType voteType);

    /**
     * ���ȫ��ѡƱ�Ϸ���
     *
     * @param votes ����ѡƱ���������ֱ�ӱ��������
     * @param <C>   ���Ͳ���
     */
    <C> void checkLegitimacy(Map<IVote<C>, Boolean> votes, Map<Voter, Double> voters);
}
