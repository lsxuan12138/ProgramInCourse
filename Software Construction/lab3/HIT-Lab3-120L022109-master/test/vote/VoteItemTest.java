package vote;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class VoteItemTest {

    // test strategy
    //getVoteValue
    //	创建一个voteItem，
    //	测试其getVoteValue是否与设定的相同
    //	检查相同的情况
    //	检查相同的情况
    //getCandidate
    //	创建一个voteItem，
    //	测试其getCandidate是否与设定的相同
    //	检查相同的情况
    @Test
    void getVoteValue() {
        VoteItem<String> voteItem = new VoteItem<>("233", "支持");
        assertEquals("支持", voteItem.getVoteValue());
        assertNotEquals("23333", voteItem.getVoteValue());
    }

    @Test
    void getCandidate() {
        VoteItem<String> voteItem = new VoteItem<>("333", "反对");
        assertEquals("333", voteItem.getCandidate());
        assertNotEquals("23333", voteItem.getCandidate());
    }


}
