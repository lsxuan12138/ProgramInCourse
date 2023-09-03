package app;

import auxiliary.Dish;
import auxiliary.Voter;
import pattern.GeneralSelectionStrategy;
import pattern.RegisteredGeneralLegitimacyChecker;
import pattern.RegisteredGeneralStatisticsStrategy;
import poll.DinnerOrder;
import poll.Poll;
import vote.*;

import java.util.*;

public class DinnerOrderApp {

    public static void main(String[] args) throws Exception {
        // ����6��ͶƱ��
        Voter vrGF = new Voter("үү");
        Voter vrF = new Voter("�ְ�");
        Voter vrM = new Voter("����");
        Voter vrS = new Voter("����");

        // �趨6��ͶƱ�˵�Ȩ��
        Map<Voter, Double> weightedVoters = new HashMap<>();
        weightedVoters.put(vrGF, 4.0);
        weightedVoters.put(vrF, 1.0);
        weightedVoters.put(vrM, 2.0);
        weightedVoters.put(vrS, 2.0);


        // �趨ͶƱ����
        Map<String, Integer> types = new HashMap<>();
        types.put("ϲ��", 2);
        types.put("��ϲ��", 0);
        types.put("����ν", 1);
        VoteType vt = new VoteType(types);

        // ������ѡ���󣺺�ѡ��
        Dish d1 = new Dish("A", 30);
        Dish d2 = new Dish("B", 25);
        Dish d3 = new Dish("C", 33);
        Dish d4 = new Dish("D", 37);
        Dish d5 = new Dish("E", 40);
        Dish d6 = new Dish("F", 20);
        List<Dish> candidates = new ArrayList<>();
        candidates.add(d1);
        candidates.add(d2);
        candidates.add(d3);
        candidates.add(d4);
        candidates.add(d5);
        candidates.add(d6);

        // ����ͶƱ��
        VoteItem<Dish> vi11 = new VoteItem<>(d1, "ϲ��");
        VoteItem<Dish> vi12 = new VoteItem<>(d2, "ϲ��");
        VoteItem<Dish> vi13 = new VoteItem<>(d3, "����ν");
        VoteItem<Dish> vi14 = new VoteItem<>(d4, "����ν");
        VoteItem<Dish> vi15 = new VoteItem<>(d5, "��ϲ��");
        VoteItem<Dish> vi16 = new VoteItem<>(d6, "��ϲ��");
        Set<VoteItem<Dish>> vis1 = new HashSet<>();
        vis1.add(vi11);
        vis1.add(vi12);
        vis1.add(vi13);
        vis1.add(vi14);
        vis1.add(vi15);
        vis1.add(vi16);
        VoteItem<Dish> vi21 = new VoteItem<>(d1, "����ν");
        VoteItem<Dish> vi22 = new VoteItem<>(d2, "ϲ��");
        VoteItem<Dish> vi23 = new VoteItem<>(d3, "ϲ��");
        VoteItem<Dish> vi24 = new VoteItem<>(d4, "ϲ��");
        VoteItem<Dish> vi25 = new VoteItem<>(d5, "��ϲ��");
        VoteItem<Dish> vi26 = new VoteItem<>(d6, "ϲ��");
        Set<VoteItem<Dish>> vis2 = new HashSet<>();
        vis2.add(vi21);
        vis2.add(vi22);
        vis2.add(vi23);
        vis2.add(vi24);
        vis2.add(vi25);
        vis2.add(vi26);
        VoteItem<Dish> vi31 = new VoteItem<>(d1, "ϲ��");
        VoteItem<Dish> vi32 = new VoteItem<>(d2, "��ϲ��");
        VoteItem<Dish> vi33 = new VoteItem<>(d3, "��ϲ��");
        VoteItem<Dish> vi34 = new VoteItem<>(d4, "��ϲ��");
        VoteItem<Dish> vi35 = new VoteItem<>(d5, "ϲ��");
        VoteItem<Dish> vi36 = new VoteItem<>(d6, "��ϲ��");
        Set<VoteItem<Dish>> vis3 = new HashSet<>();
        vis3.add(vi31);
        vis3.add(vi32);
        vis3.add(vi33);
        vis3.add(vi34);
        vis3.add(vi35);
        vis3.add(vi36);
        VoteItem<Dish> vi41 = new VoteItem<>(d1, "ϲ��");
        VoteItem<Dish> vi42 = new VoteItem<>(d2, "����ν");
        VoteItem<Dish> vi43 = new VoteItem<>(d3, "ϲ��");
        VoteItem<Dish> vi44 = new VoteItem<>(d4, "ϲ��");
        VoteItem<Dish> vi45 = new VoteItem<>(d5, "ϲ��");
        VoteItem<Dish> vi46 = new VoteItem<>(d6, "��ϲ��");
        Set<VoteItem<Dish>> vis4 = new HashSet<>();
        vis4.add(vi41);
        vis4.add(vi42);
        vis4.add(vi43);
        vis4.add(vi44);
        vis4.add(vi45);
        vis4.add(vi46);


        // ����6��ͶƱ�˵�ѡƱ
        IVote<Dish> rv1 = new RealNameDecorator<>(vrGF, new Vote<>(vis1));
        IVote<Dish> rv2 = new RealNameDecorator<>(vrF, new Vote<>(vis2));
        IVote<Dish> rv3 = new RealNameDecorator<>(vrM, new Vote<>(vis3));
        IVote<Dish> rv4 = new RealNameDecorator<>(vrS, new Vote<>(vis4));

        // ����ͶƱ�
        //Poll<Dish> poll = Poll.create();
        Poll<Dish> poll = new DinnerOrder();
        // �趨ͶƱ������Ϣ�����ơ����ڡ�ͶƱ���͡�ѡ��������
        poll.addCandidates(candidates);
        poll.setInfo("���", new GregorianCalendar(), vt, 4);
        // ����ͶƱ�˼���Ȩ��
        poll.addVoters(weightedVoters);
        poll.setLegitimacyChecker(new RegisteredGeneralLegitimacyChecker());
        // ����6��ͶƱ�˵�ѡƱ
        poll.addVote(rv1);
        poll.addVote(rv2);
        poll.addVote(rv3);
        poll.addVote(rv4);
        // �������Ʊ
        poll.statistics(new RegisteredGeneralStatisticsStrategy());
        // ��������ѡ
        poll.selection(new GeneralSelectionStrategy());
        // �����ѡ���
        System.out.println(poll.result());
    }
}
