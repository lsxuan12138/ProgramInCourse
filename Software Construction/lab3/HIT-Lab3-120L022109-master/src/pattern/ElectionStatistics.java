package pattern;

import auxiliary.Voter;
import vote.IVote;
import vote.VoteItem;
import vote.VoteType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-06-23 11:47
 */
public class ElectionStatistics implements StatisticsStrategy {
    @Override
    public <C> Map<C, Double> statistics(List<C> candidates, Map<IVote<C>, Boolean> votes, Map<Voter, Double> voters, VoteType voteType) {
        String validValue = "Ö§³Ö";
        Map<C, Double> result = new HashMap<>();
        for (C candidate :
                candidates) {
            result.put(candidate, 0.0);
        }
        for (IVote<C> vote :
                votes.keySet()) {
            for (VoteItem<C> voteItem :
                    vote.getVoteItems()) {
                if (voteItem.getVoteValue().equals(validValue)) {
                    result.put(voteItem.getCandidate(), result.get(voteItem.getCandidate()) +
                            voteType.getScoreByOption(validValue));
                }
            }

        }
        return result;
    }
}