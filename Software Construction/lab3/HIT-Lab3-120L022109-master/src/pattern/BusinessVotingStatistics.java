package pattern;

import auxiliary.Voter;
import vote.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 适用于商业表决的选票统计器
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-06-05 21:39
 */
public class BusinessVotingStatistics implements StatisticsStrategy {
    @Override
    public <C> Map<C, Double> statistics(List<C> candidates, Map<IVote<C>, Boolean> votes, Map<Voter, Double> voters, VoteType voteType) {
        String validValue = "支持";
        Map<C, Double> result = new HashMap<>();
        for (C candidate :
                candidates) {
            result.put(candidate, 0.0);
        }
        for (IVote<C> vote :
                votes.keySet()) {

            if (vote instanceof RealNameVote) {
                RealNameVote<C> realNameVote = (RealNameVote<C>) vote;
                for (VoteItem<C> voteItem :
                        realNameVote.getVoteItems()) {
                    if (voteItem.getVoteValue().equals(validValue)) {
                        result.put(voteItem.getCandidate(), result.get(voteItem.getCandidate()) +
                                voteType.getScoreByOption(validValue) * voters.get(realNameVote.getVoter()));
                    }
                }
            } else if (vote.getClass().equals(RealNameDecorator.class)) {
                RealNameDecorator<C> realNameVote = (RealNameDecorator<C>) vote;
                for (VoteItem<C> voteItem :
                        realNameVote.getVoteItems()) {
                    if (voteItem.getVoteValue().equals(validValue)) {
                        result.put(voteItem.getCandidate(), result.get(voteItem.getCandidate()) +
                                voteType.getScoreByOption(validValue) * voters.get(realNameVote.getVoter()));
                    }
                }
            } else throw new RuntimeException("投票未记名");
        }
        return result;
    }
}