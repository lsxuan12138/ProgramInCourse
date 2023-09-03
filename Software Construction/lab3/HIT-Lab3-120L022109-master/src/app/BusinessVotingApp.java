package app;

import auxiliary.Proposal;
import auxiliary.Voter;
import pattern.BusinessVotingSelection;
import pattern.BusinessVotingStatistics;
import pattern.RegisteredGeneralLegitimacyChecker;
import poll.BusinessVoting;
import poll.Poll;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

public class BusinessVotingApp {

    public static void main(String[] args) throws Exception {
        // 创建2个投票人
        Voter A = new Voter("a");
        Voter B = new Voter("b");
        Voter C = new Voter("c");
        Voter D = new Voter("d");
        Voter E = new Voter("e");
        // 设定2个投票人的权重
        Map<Voter, Double> weightedVoters = new HashMap<>();
        weightedVoters.put(A, 0.05);
        weightedVoters.put(B, 0.51);
        weightedVoters.put(C, 0.1);
        weightedVoters.put(D, 0.14);
        weightedVoters.put(E, 0.2);

        // 设定投票类型
        Map<String, Integer> types = new HashMap<>();
        types.put("支持", 1);
        types.put("反对", -1);
        types.put("弃权", 0);
        VoteType vt = new VoteType(types);

        // 创建候选
        Proposal proposal = new Proposal("关于XX项目的提案", new GregorianCalendar());
        List<Proposal> proposals = new ArrayList<>();
        proposals.add(proposal);

        // 创建投票项，前三个是投票人vr1对三个候选对象的投票项，后三个是vr2的投票项
        VoteItem<Proposal> v1 = new VoteItem<>(proposal, "反对");
        VoteItem<Proposal> v2 = new VoteItem<>(proposal, "支持");
        VoteItem<Proposal> v3 = new VoteItem<>(proposal, "支持");
        VoteItem<Proposal> v4 = new VoteItem<>(proposal, "反对");
        VoteItem<Proposal> v5 = new VoteItem<>(proposal, "弃权");

        // 创建2个投票人vr1、vr2的选票
        Set<VoteItem<Proposal>> set1 = new HashSet<>();
        set1.add(v1);
        Set<VoteItem<Proposal>> set2 = new HashSet<>();
        set2.add(v2);
        Set<VoteItem<Proposal>> set3 = new HashSet<>();
        set3.add(v3);
        Set<VoteItem<Proposal>> set4 = new HashSet<>();
        set4.add(v4);
        Set<VoteItem<Proposal>> set5 = new HashSet<>();
        set5.add(v5);
        Vote<Proposal> vote1 = new RealNameVote<>(A, set1);
        Vote<Proposal> vote2 = new RealNameVote<>(B, set2);
        Vote<Proposal> vote3 = new RealNameVote<>(C, set3);
        Vote<Proposal> vote4 = new RealNameVote<>(D, set4);
        Vote<Proposal> vote5 = new RealNameVote<>(E, set5);
        // 创建投票活动
        //Poll<Proposal> poll = Poll.create();
        Poll<Proposal> poll = new BusinessVoting();
        poll.addCandidates(proposals);
        // 设定投票基本信息：名称、日期、投票类型、选出的数量
        poll.setInfo("商业表决", new GregorianCalendar(), vt, 1);
        // 增加投票人及其权重
        poll.addVoters(weightedVoters);
        //设置需要的合法性检查器
        poll.setLegitimacyChecker(new RegisteredGeneralLegitimacyChecker());
        // 增加投票人的选票
        poll.addVote(vote1);
        poll.addVote(vote2);
        poll.addVote(vote3);
        poll.addVote(vote4);
        poll.addVote(vote5);

        // 按规则计票
        poll.statistics(new BusinessVotingStatistics());
        // 按规则遴选
        poll.selection(new BusinessVotingSelection());
        // 输出遴选结果
        System.out.println(poll.result());
    }
}
