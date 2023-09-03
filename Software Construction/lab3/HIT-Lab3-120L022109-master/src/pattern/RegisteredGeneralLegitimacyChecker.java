package pattern;

import auxiliary.Voter;
import vote.IVote;
import vote.RealNameDecorator;
import vote.RealNameVote;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 常规记名投票合法性检查器
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-06-05 20:43
 */
public class RegisteredGeneralLegitimacyChecker extends AnonymousGeneralLegitimacyChecker {
    @Override
    public <C> void checkLegitimacy(Map<IVote<C>, Boolean> votes, Map<Voter, Double> voters) {
        //若尚有投票人还未投票，则不能开始计票
        boolean flag;
        for (Voter voter :
                voters.keySet()) {
            flag = false;
            for (IVote<C> vote :
                    votes.keySet()) {
                if (vote instanceof RealNameVote) {
                    if (voter.equals(((RealNameVote<C>) vote).getVoter())) flag = true;
                } else if (vote.getClass().equals(RealNameDecorator.class)) {
                    if (voter.equals(((RealNameDecorator<C>) vote).getVoter())) flag = true;
                } else throw new RuntimeException("投票未记名");
            }
            if (!flag) {
                throw new RuntimeException("还有投票人未投票");
            }
        }
        //若一个投票人提交了多次选票，则它们均为非法，计票时不计算在内。
        Set<Voter> test = new HashSet<>();
        for (IVote<C> vote :
                votes.keySet()) {
            //不考虑不合法投票
            if (vote instanceof RealNameVote) {
                RealNameVote<C> realNameVote = (RealNameVote<C>) vote;
                //若test中已经包含投票者
                if (!test.contains(realNameVote.getVoter())) {
                    test.add(realNameVote.getVoter());
                } else {
                    test.remove(realNameVote.getVoter());
                    //将所有该投票者的票记为不合法
                    for (IVote<C> vote1 :
                            votes.keySet()) {
                        if (((RealNameVote<C>) vote1).getVoter().equals(((RealNameVote<C>) vote).getVoter())) {
                            votes.put(vote1, false);
                        }
                    }
                }
            } else if (vote.getClass().equals(RealNameDecorator.class)) {
                RealNameDecorator<C> realNameVote = (RealNameDecorator<C>) vote;
                //若test中已经包含投票者
                if (!test.contains(realNameVote.getVoter())) {
                    test.add(realNameVote.getVoter());
                } else {
                    test.remove(realNameVote.getVoter());
                    //将所有该投票者的票记为不合法
                    for (IVote<C> vote1 :
                            votes.keySet()) {
                        if (((RealNameVote<C>) vote1).getVoter().equals(((RealNameDecorator<C>) vote).getVoter())) {
                            votes.put(vote1, false);
                        }
                    }
                }
            } else throw new RuntimeException("投票未记名");

        }
    }
}