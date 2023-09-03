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
        // ����2��ͶƱ��
        Voter A = new Voter("a");
        Voter B = new Voter("b");
        Voter C = new Voter("c");
        Voter D = new Voter("d");
        Voter E = new Voter("e");
        // �趨2��ͶƱ�˵�Ȩ��
        Map<Voter, Double> weightedVoters = new HashMap<>();
        weightedVoters.put(A, 0.05);
        weightedVoters.put(B, 0.51);
        weightedVoters.put(C, 0.1);
        weightedVoters.put(D, 0.14);
        weightedVoters.put(E, 0.2);

        // �趨ͶƱ����
        Map<String, Integer> types = new HashMap<>();
        types.put("֧��", 1);
        types.put("����", -1);
        types.put("��Ȩ", 0);
        VoteType vt = new VoteType(types);

        // ������ѡ
        Proposal proposal = new Proposal("����XX��Ŀ���᰸", new GregorianCalendar());
        List<Proposal> proposals = new ArrayList<>();
        proposals.add(proposal);

        // ����ͶƱ�ǰ������ͶƱ��vr1��������ѡ�����ͶƱ���������vr2��ͶƱ��
        VoteItem<Proposal> v1 = new VoteItem<>(proposal, "����");
        VoteItem<Proposal> v2 = new VoteItem<>(proposal, "֧��");
        VoteItem<Proposal> v3 = new VoteItem<>(proposal, "֧��");
        VoteItem<Proposal> v4 = new VoteItem<>(proposal, "����");
        VoteItem<Proposal> v5 = new VoteItem<>(proposal, "��Ȩ");

        // ����2��ͶƱ��vr1��vr2��ѡƱ
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
        // ����ͶƱ�
        //Poll<Proposal> poll = Poll.create();
        Poll<Proposal> poll = new BusinessVoting();
        poll.addCandidates(proposals);
        // �趨ͶƱ������Ϣ�����ơ����ڡ�ͶƱ���͡�ѡ��������
        poll.setInfo("��ҵ���", new GregorianCalendar(), vt, 1);
        // ����ͶƱ�˼���Ȩ��
        poll.addVoters(weightedVoters);
        //������Ҫ�ĺϷ��Լ����
        poll.setLegitimacyChecker(new RegisteredGeneralLegitimacyChecker());
        // ����ͶƱ�˵�ѡƱ
        poll.addVote(vote1);
        poll.addVote(vote2);
        poll.addVote(vote3);
        poll.addVote(vote4);
        poll.addVote(vote5);

        // �������Ʊ
        poll.statistics(new BusinessVotingStatistics());
        // ��������ѡ
        poll.selection(new BusinessVotingSelection());
        // �����ѡ���
        System.out.println(poll.result());
    }
}
