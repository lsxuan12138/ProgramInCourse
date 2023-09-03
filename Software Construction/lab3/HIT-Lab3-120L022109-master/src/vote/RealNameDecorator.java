package vote;

import auxiliary.Voter;

import java.util.Set;

/**
 * TODO
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-06-22 18:28
 */
public class RealNameDecorator<C> implements IVote<C> {
    private IVote<C> vote;
    private Voter voter;

    public RealNameDecorator(Voter voter, IVote<C> vote) {
        this.voter = voter;
        this.vote = vote;
    }

    @Override
    public Set<VoteItem<C>> getVoteItems() {
        return vote.getVoteItems();
    }

    @Override
    public boolean candidateIncluded(C candidate) {
        return vote.candidateIncluded(candidate);
    }

    public Voter getVoter() {
        return voter;
    }
}