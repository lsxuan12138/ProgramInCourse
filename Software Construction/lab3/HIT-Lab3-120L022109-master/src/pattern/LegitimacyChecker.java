package pattern;

import auxiliary.Voter;
import vote.IVote;
import vote.VoteType;

import java.util.List;
import java.util.Map;

/**
 * 合法性检查
 */
public interface LegitimacyChecker {
    /**
     * 检查一张选票的合法性
     *
     * @param vote       要检查的选票，不应为null
     * @param candidates 所有的候选人，用来判断合法性，不应为null
     * @param voteType   选票类型，用来判断合法性，不应为null
     * @param <C>        泛型参数
     * @return 返回vote的合法性。合法返回true，反之为false
     */
    <C> boolean checkLegitimacy(IVote<C> vote, List<C> candidates, VoteType voteType);

    /**
     * 检查全部选票合法性
     *
     * @param votes 检查的选票集，结果将直接标记在其中
     * @param <C>   泛型参数
     */
    <C> void checkLegitimacy(Map<IVote<C>, Boolean> votes, Map<Voter, Double> voters);
}
