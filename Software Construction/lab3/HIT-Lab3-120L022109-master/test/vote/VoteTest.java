package vote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VoteTest {

    // test strategy
    //方法测试
    //getVoteValue
    //	创建一个voteItem，
    //	测试其getVoteValue是否与设定的相同
    //	检查相同的情况
    //	检查相同的情况
    //getCandidate
    //	创建一个voteItem，
    //	测试其getCandidate是否与设定的相同
    //	检查相同的情况
    //	检查相同的情况


    Vote<String> vote;

    @BeforeEach
    public void initAVote() {

        //VoteType<String> voteType = new VoteType();
        Set<VoteItem<String>> set = new HashSet<>();
        VoteItem<String> voteItem1 = new VoteItem<>("a", "支持");
        VoteItem<String> voteItem2 = new VoteItem<>("b", "支持");
        VoteItem<String> voteItem3 = new VoteItem<>("c", "反对");
        set.add(voteItem1);
        set.add(voteItem2);
        set.add(voteItem3);
        vote = new Vote<>(set);
    }

    @Test
    void getVoteItems() {
        Set<VoteItem<String>> set = new HashSet<>();
        VoteItem<String> voteItem1 = new VoteItem<>("a", "支持");
        VoteItem<String> voteItem2 = new VoteItem<>("b", "支持");
        VoteItem<String> voteItem3 = new VoteItem<>("c", "反对");
        set.add(voteItem1);
        set.add(voteItem2);
        set.add(voteItem3);
        //检测相同输入
        assertEquals(set, vote.getVoteItems());
        //检测不同的输入
        set.add(new VoteItem<String>("d", "弃权"));
        assertNotEquals(set, vote.getVoteItems());
    }

    @Test
    void candidateIncluded() {
        //检查包含的输入
        assertTrue(vote.candidateIncluded("a"));
        assertTrue(vote.candidateIncluded("b"));
        assertTrue(vote.candidateIncluded("c"));
        //检查不包含的输入
        assertFalse(vote.candidateIncluded("d"));
    }
}
