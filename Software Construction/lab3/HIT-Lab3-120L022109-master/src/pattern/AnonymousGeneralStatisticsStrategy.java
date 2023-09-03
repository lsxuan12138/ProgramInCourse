package pattern;

import auxiliary.Voter;
import vote.IVote;
import vote.VoteItem;
import vote.VoteType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 常规不记名投票选票统计
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-06-22 13:16
 */
public class AnonymousGeneralStatisticsStrategy implements StatisticsStrategy {

    @Override
    public <C> Map<C, Double> statistics(List<C> candidates, Map<IVote<C>, Boolean> votes, Map<Voter, Double> voters, VoteType voteType) {
        Map<C, Double> result = new HashMap<>();
        for (C candidate :
                candidates) {
            result.put(candidate, 0.0);
        }
        for (IVote<C> vote :
                votes.keySet()) {
            if (!votes.get(vote)) continue;
            for (VoteItem<C> voteItem :
                    vote.getVoteItems()) {
                result.put(voteItem.getCandidate(), result.get(voteItem.getCandidate()) +
                        voteType.getScoreByOption(voteItem.getVoteValue()));
            }
        }

        return result;
    }
}