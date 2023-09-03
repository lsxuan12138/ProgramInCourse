package poll;

import auxiliary.Voter;
import org.junit.jupiter.api.Test;
import pattern.*;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PollTest {

    // test strategy
    // 测试addCandidates
    //	测试一般情况（添加的元素中无重复）
    //测试重复元素
    //测试addVoters
    //	测试一般情况
    //	测试重复添加同一个元素
    //测试setInfo
    //	利用observer方法测试设定的值，是否与设定的相同
    //测试addVote
    //	测试添加合法的选票
    //	测试添加不合法的选票
    //测试statistics
    //	测试现有投票活动的统计结果与预期是否相同
    //测试selection和result
    //	测试经过遴选的结果，使用result方法输出结果，检查其与预期是否相同


    @Test
    void test() {
        Poll<String> poll = Poll.create();
        //-----------------测试addCandidates----------------
        List<String> testCandidates = new ArrayList<>();
        List<String> candidates = new ArrayList<>();
        testCandidates.add("a");
        candidates.add("a");
        testCandidates.add("b");
        candidates.add("b");
        //测试一般情况
        poll.addCandidates(candidates);
        assertEquals(testCandidates, poll.getCandidates());
        //若其中有重复元素，测试结果
        candidates.add("b");
        poll.addCandidates(candidates);
        assertEquals(testCandidates, poll.getCandidates());
        //-------------测试addVoters-------------
        Map<Voter, Double> testVoters = new HashMap<>();
        Map<Voter, Double> voters = new HashMap<>();
        Voter c = new Voter("c");
        Voter d = new Voter("d");
        Voter e = new Voter("e");
        testVoters.put(c, 0.5);
        voters.put(c, 0.5);
        testVoters.put(d, 0.8);
        voters.put(d, 0.8);
        testVoters.put(e, 0.4);
        voters.put(e, 0.4);
        poll.addVoters(voters);
        //测试一般情况
        assertEquals(testVoters, poll.getVoters());
        //测试重复添加
        poll.addVoters(voters);
        assertEquals(testVoters, poll.getVoters());
        //----------------------测试setInfo-------------------------------
        Calendar calendar = Calendar.getInstance();
        Map<String, Integer> type = new HashMap<String, Integer>(3);
        type.put("支持", 1);
        type.put("反对", -1);
        type.put("弃权", 0);
        VoteType voteType = new VoteType(type);
        poll.setInfo("投票", calendar, voteType, 1);
        assertEquals("投票", poll.getName());
        assertEquals(calendar, poll.getDate());
        assertEquals(voteType, poll.getType());
        assertEquals(1, poll.getQuantity());

        //------------测试addVote----------
        LegitimacyChecker legitimacyChecker = new AnonymousGeneralLegitimacyChecker();
        poll.setLegitimacyChecker(legitimacyChecker);
        Set<VoteItem<String>> set = new HashSet<>();
        VoteItem<String> voteItem1 = new VoteItem<>("a", "支持");
        VoteItem<String> voteItem2 = new VoteItem<>("b", "支持");
        //测试正常选票
        set.add(voteItem1);
        set.add(voteItem2);
        Vote<String> vote = new Vote<>(set);
        poll.addVote(vote);
        Map<Vote<String>, Boolean> testVotes = new HashMap<>();
        testVotes.put(vote, true);
        assertEquals(testVotes, poll.getVotes());
        VoteItem<String> voteItem4 = new VoteItem<>("a", "反对");
        VoteItem<String> voteItem5 = new VoteItem<>("b", "支持");
        Set<VoteItem<String>> set1 = new HashSet<>();
        set1.add(voteItem4);
        set1.add(voteItem5);
        Vote<String> vote2 = new Vote<>(set1);
        poll.addVote(vote2);
        testVotes.put(vote2, true);
        assertEquals(testVotes, poll.getVotes());
        //测试不合法选票
        VoteItem<String> voteItem3 = new VoteItem<>("c", "反对");
        set.add(voteItem3);
        Vote<String> vote1 = new Vote<>(set);
        poll.addVote(vote1);
        testVotes.put(vote1, false);
        assertEquals(testVotes, poll.getVotes());
        //---------测试statistics-------------------
        StatisticsStrategy statisticsStrategy = new AnonymousGeneralStatisticsStrategy();
        poll.statistics(statisticsStrategy);
        Map<String, Double> testStatistics = new HashMap<>();
        testStatistics.put("a", 0.0);
        testStatistics.put("b", 2.0);
        assertEquals(testStatistics, poll.getStatistics());
        //----------测试selection和result---------
        String testResult = "b\t1\n";
        SelectionStrategy selectionStrategy = new GeneralSelectionStrategy();
        poll.selection(selectionStrategy);
        assertEquals(testResult, poll.result());
    }
}
