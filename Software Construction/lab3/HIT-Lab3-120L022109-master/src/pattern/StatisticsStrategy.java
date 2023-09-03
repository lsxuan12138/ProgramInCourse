package pattern;

import auxiliary.Voter;
import vote.IVote;
import vote.VoteType;

import java.util.List;
import java.util.Map;

public interface StatisticsStrategy {
    <C> Map<C, Double> statistics(List<C> candidates, Map<IVote<C>, Boolean> votes, Map<Voter, Double> voters, VoteType voteType);

}
