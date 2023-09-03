package app;

import auxiliary.Person;
import auxiliary.Voter;
import pattern.AnonymousGeneralLegitimacyChecker;
import pattern.ElectionSelection;
import pattern.ElectionStatistics;
import poll.Election;
import poll.Poll;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

public class ElectionApp {

    public static void main(String[] args) {

        // 创建6个投票人
        Voter vr1 = new Voter("v1");
        Voter vr2 = new Voter("v2");
        Voter vr3 = new Voter("v3");
        Voter vr4 = new Voter("v4");
        Voter vr5 = new Voter("v5");
        Voter vr6 = new Voter("v6");

        // 设定6个投票人的权重
        Map<Voter, Double> weightedVoters = new HashMap<>();
        weightedVoters.put(vr1, 1.0);
        weightedVoters.put(vr2, 1.0);
        weightedVoters.put(vr3, 1.0);
        weightedVoters.put(vr4, 1.0);
        weightedVoters.put(vr5, 1.0);
        weightedVoters.put(vr6, 1.0);

        // 设定投票类型
        Map<String, Integer> types = new HashMap<>();
        types.put("支持", 1);
        types.put("反对", -1);
        types.put("弃权", 0);
        VoteType vt = new VoteType(types);

        // 创建候选对象：候选人
        Person p1 = new Person("A", 19);
        Person p2 = new Person("B", 20);
        Person p3 = new Person("C", 19);
        Person p4 = new Person("D", 19);
        Person p5 = new Person("E", 21);
        List<Person> candidates = new ArrayList<>();
        candidates.add(p1);
        candidates.add(p2);
        candidates.add(p3);
        candidates.add(p4);
        candidates.add(p5);

        // 创建投票项，前三个是投票人vr1对三个候选对象的投票项，后三个是vr2的投票项
        VoteItem<Person> vi11 = new VoteItem<>(p1, "支持");
        VoteItem<Person> vi12 = new VoteItem<>(p2, "反对");
        VoteItem<Person> vi13 = new VoteItem<>(p3, "支持");
        VoteItem<Person> vi14 = new VoteItem<>(p4, "反对");
        VoteItem<Person> vi15 = new VoteItem<>(p5, "弃权");
        Set<VoteItem<Person>> vis1 = new HashSet<>();
        vis1.add(vi11);
        vis1.add(vi12);
        vis1.add(vi13);
        vis1.add(vi14);
        vis1.add(vi15);
        VoteItem<Person> vi21 = new VoteItem<>(p1, "反对");
        VoteItem<Person> vi22 = new VoteItem<>(p2, "反对");
        VoteItem<Person> vi23 = new VoteItem<>(p3, "支持");
        VoteItem<Person> vi24 = new VoteItem<>(p4, "支持");
        VoteItem<Person> vi25 = new VoteItem<>(p5, "支持");
        Set<VoteItem<Person>> vis2 = new HashSet<>();
        vis2.add(vi21);
        vis2.add(vi22);
        vis2.add(vi23);
        vis2.add(vi24);
        vis2.add(vi25);
        VoteItem<Person> vi31 = new VoteItem<>(p1, "弃权");
        VoteItem<Person> vi32 = new VoteItem<>(p2, "支持");
        VoteItem<Person> vi33 = new VoteItem<>(p3, "支持");
        VoteItem<Person> vi34 = new VoteItem<>(p4, "支持");
        VoteItem<Person> vi35 = new VoteItem<>(p5, "支持");
        Set<VoteItem<Person>> vis3 = new HashSet<>();
        vis3.add(vi31);
        vis3.add(vi32);
        vis3.add(vi33);
        vis3.add(vi34);
        vis3.add(vi35);
        VoteItem<Person> vi41 = new VoteItem<>(p1, "反对");
        VoteItem<Person> vi42 = new VoteItem<>(p2, "反对");
        VoteItem<Person> vi43 = new VoteItem<>(p3, "反对");
        VoteItem<Person> vi44 = new VoteItem<>(p4, "反对");
        VoteItem<Person> vi45 = new VoteItem<>(p5, "反对");
        Set<VoteItem<Person>> vis4 = new HashSet<>();
        vis4.add(vi41);
        vis4.add(vi42);
        vis4.add(vi43);
        vis4.add(vi44);
        vis4.add(vi45);
        VoteItem<Person> vi51 = new VoteItem<>(p1, "反对");
        VoteItem<Person> vi52 = new VoteItem<>(p2, "反对");
        VoteItem<Person> vi53 = new VoteItem<>(p3, "支持");
        VoteItem<Person> vi54 = new VoteItem<>(p4, "支持");
        VoteItem<Person> vi55 = new VoteItem<>(p5, "支持");
        Set<VoteItem<Person>> vis5 = new HashSet<>();
        vis5.add(vi51);
        vis5.add(vi52);
        vis5.add(vi53);
        vis5.add(vi54);
        vis5.add(vi55);
        VoteItem<Person> vi61 = new VoteItem<>(p1, "弃权");
        VoteItem<Person> vi62 = new VoteItem<>(p2, "支持");
        VoteItem<Person> vi63 = new VoteItem<>(p3, "支持");
        VoteItem<Person> vi64 = new VoteItem<>(p4, "反对");
        VoteItem<Person> vi65 = new VoteItem<>(p5, "弃权");
        Set<VoteItem<Person>> vis6 = new HashSet<>();
        vis6.add(vi61);
        vis6.add(vi62);
        vis6.add(vi63);
        vis6.add(vi64);
        vis6.add(vi65);

        // 创建6个投票人的选票
        Vote<Person> rv1 = new Vote<Person>(vis1);
        Vote<Person> rv2 = new Vote<Person>(vis2);
        Vote<Person> rv3 = new Vote<Person>(vis3);
        Vote<Person> rv4 = new Vote<Person>(vis4);
        Vote<Person> rv5 = new Vote<Person>(vis5);
        Vote<Person> rv6 = new Vote<Person>(vis6);

        // 创建投票活动
        //Poll<Person> poll = Poll.create();
        Poll<Person> poll = new Election();
        // 设定投票基本信息：名称、日期、投票类型、选出的数量
        poll.addCandidates(candidates);
        poll.setInfo("班干选举", new GregorianCalendar(), vt, 3);
        // 增加投票人及其权重
        poll.addVoters(weightedVoters);
        poll.setLegitimacyChecker(new AnonymousGeneralLegitimacyChecker());
        // 增加6个投票人的选票
        poll.addVote(rv1);
        poll.addVote(rv2);
        poll.addVote(rv3);
        poll.addVote(rv4);
        poll.addVote(rv5);
        poll.addVote(rv6);
        // 按规则计票
        poll.statistics(new ElectionStatistics());
        // 按规则遴选
        poll.selection(new ElectionSelection());
        // 输出遴选结果
        System.out.println(poll.result());
    }

}
