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
    // ����addCandidates
    //	����һ���������ӵ�Ԫ�������ظ���
    //�����ظ�Ԫ��
    //����addVoters
    //	����һ�����
    //	�����ظ����ͬһ��Ԫ��
    //����setInfo
    //	����observer���������趨��ֵ���Ƿ����趨����ͬ
    //����addVote
    //	������ӺϷ���ѡƱ
    //	������Ӳ��Ϸ���ѡƱ
    //����statistics
    //	��������ͶƱ���ͳ�ƽ����Ԥ���Ƿ���ͬ
    //����selection��result
    //	���Ծ�����ѡ�Ľ����ʹ��result�������������������Ԥ���Ƿ���ͬ


    @Test
    void test() {
        Poll<String> poll = Poll.create();
        //-----------------����addCandidates----------------
        List<String> testCandidates = new ArrayList<>();
        List<String> candidates = new ArrayList<>();
        testCandidates.add("a");
        candidates.add("a");
        testCandidates.add("b");
        candidates.add("b");
        //����һ�����
        poll.addCandidates(candidates);
        assertEquals(testCandidates, poll.getCandidates());
        //���������ظ�Ԫ�أ����Խ��
        candidates.add("b");
        poll.addCandidates(candidates);
        assertEquals(testCandidates, poll.getCandidates());
        //-------------����addVoters-------------
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
        //����һ�����
        assertEquals(testVoters, poll.getVoters());
        //�����ظ����
        poll.addVoters(voters);
        assertEquals(testVoters, poll.getVoters());
        //----------------------����setInfo-------------------------------
        Calendar calendar = Calendar.getInstance();
        Map<String, Integer> type = new HashMap<String, Integer>(3);
        type.put("֧��", 1);
        type.put("����", -1);
        type.put("��Ȩ", 0);
        VoteType voteType = new VoteType(type);
        poll.setInfo("ͶƱ", calendar, voteType, 1);
        assertEquals("ͶƱ", poll.getName());
        assertEquals(calendar, poll.getDate());
        assertEquals(voteType, poll.getType());
        assertEquals(1, poll.getQuantity());

        //------------����addVote----------
        LegitimacyChecker legitimacyChecker = new AnonymousGeneralLegitimacyChecker();
        poll.setLegitimacyChecker(legitimacyChecker);
        Set<VoteItem<String>> set = new HashSet<>();
        VoteItem<String> voteItem1 = new VoteItem<>("a", "֧��");
        VoteItem<String> voteItem2 = new VoteItem<>("b", "֧��");
        //��������ѡƱ
        set.add(voteItem1);
        set.add(voteItem2);
        Vote<String> vote = new Vote<>(set);
        poll.addVote(vote);
        Map<Vote<String>, Boolean> testVotes = new HashMap<>();
        testVotes.put(vote, true);
        assertEquals(testVotes, poll.getVotes());
        VoteItem<String> voteItem4 = new VoteItem<>("a", "����");
        VoteItem<String> voteItem5 = new VoteItem<>("b", "֧��");
        Set<VoteItem<String>> set1 = new HashSet<>();
        set1.add(voteItem4);
        set1.add(voteItem5);
        Vote<String> vote2 = new Vote<>(set1);
        poll.addVote(vote2);
        testVotes.put(vote2, true);
        assertEquals(testVotes, poll.getVotes());
        //���Բ��Ϸ�ѡƱ
        VoteItem<String> voteItem3 = new VoteItem<>("c", "����");
        set.add(voteItem3);
        Vote<String> vote1 = new Vote<>(set);
        poll.addVote(vote1);
        testVotes.put(vote1, false);
        assertEquals(testVotes, poll.getVotes());
        //---------����statistics-------------------
        StatisticsStrategy statisticsStrategy = new AnonymousGeneralStatisticsStrategy();
        poll.statistics(statisticsStrategy);
        Map<String, Double> testStatistics = new HashMap<>();
        testStatistics.put("a", 0.0);
        testStatistics.put("b", 2.0);
        assertEquals(testStatistics, poll.getStatistics());
        //----------����selection��result---------
        String testResult = "b\t1\n";
        SelectionStrategy selectionStrategy = new GeneralSelectionStrategy();
        poll.selection(selectionStrategy);
        assertEquals(testResult, poll.result());
    }
}
