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
 * 常规不记名投票合法性检查器
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
            //一张选票中包含了不在本次投票活动中的候选人
            if (!candidates.contains(voteItem.getCandidate())) {
                return false;
            }
            //一张选票中出现了本次投票不允许的选项值
            if (!voteType.checkLegality(voteItem.getVoteValue())) {
                return false;
            }
            //一张选票中有对同一个候选对象的多次投票
            if (test.contains(voteItem.getCandidate())) {
                return false;
            } else test.add(voteItem.getCandidate());
        }
        //一张选票中没有包含本次投票活动中的所有候选人
        if (!test.equals(new HashSet<>(candidates))) return false;

        return true;
    }

    @Override
    public <C> void checkLegitimacy(Map<IVote<C>, Boolean> votes, Map<Voter, Double> voters) {
        //若尚有投票人还未投票，则不能开始计票；
        if (votes.size() < voters.size()) throw new RuntimeException("还有投票人未投票");
        if (votes.size() > voters.size()) throw new RuntimeException("有人投了多次选票");

    }
}