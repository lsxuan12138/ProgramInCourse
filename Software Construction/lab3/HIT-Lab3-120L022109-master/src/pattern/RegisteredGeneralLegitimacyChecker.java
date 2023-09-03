package pattern;

import auxiliary.Voter;
import vote.IVote;
import vote.RealNameDecorator;
import vote.RealNameVote;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * �������ͶƱ�Ϸ��Լ����
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-06-05 20:43
 */
public class RegisteredGeneralLegitimacyChecker extends AnonymousGeneralLegitimacyChecker {
    @Override
    public <C> void checkLegitimacy(Map<IVote<C>, Boolean> votes, Map<Voter, Double> voters) {
        //������ͶƱ�˻�δͶƱ�����ܿ�ʼ��Ʊ
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
                } else throw new RuntimeException("ͶƱδ����");
            }
            if (!flag) {
                throw new RuntimeException("����ͶƱ��δͶƱ");
            }
        }
        //��һ��ͶƱ���ύ�˶��ѡƱ�������Ǿ�Ϊ�Ƿ�����Ʊʱ���������ڡ�
        Set<Voter> test = new HashSet<>();
        for (IVote<C> vote :
                votes.keySet()) {
            //�����ǲ��Ϸ�ͶƱ
            if (vote instanceof RealNameVote) {
                RealNameVote<C> realNameVote = (RealNameVote<C>) vote;
                //��test���Ѿ�����ͶƱ��
                if (!test.contains(realNameVote.getVoter())) {
                    test.add(realNameVote.getVoter());
                } else {
                    test.remove(realNameVote.getVoter());
                    //�����и�ͶƱ�ߵ�Ʊ��Ϊ���Ϸ�
                    for (IVote<C> vote1 :
                            votes.keySet()) {
                        if (((RealNameVote<C>) vote1).getVoter().equals(((RealNameVote<C>) vote).getVoter())) {
                            votes.put(vote1, false);
                        }
                    }
                }
            } else if (vote.getClass().equals(RealNameDecorator.class)) {
                RealNameDecorator<C> realNameVote = (RealNameDecorator<C>) vote;
                //��test���Ѿ�����ͶƱ��
                if (!test.contains(realNameVote.getVoter())) {
                    test.add(realNameVote.getVoter());
                } else {
                    test.remove(realNameVote.getVoter());
                    //�����и�ͶƱ�ߵ�Ʊ��Ϊ���Ϸ�
                    for (IVote<C> vote1 :
                            votes.keySet()) {
                        if (((RealNameVote<C>) vote1).getVoter().equals(((RealNameDecorator<C>) vote).getVoter())) {
                            votes.put(vote1, false);
                        }
                    }
                }
            } else throw new RuntimeException("ͶƱδ����");

        }
    }
}