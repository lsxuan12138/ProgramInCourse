package vote;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class VoteItemTest {

    // test strategy
    //getVoteValue
    //	����һ��voteItem��
    //	������getVoteValue�Ƿ����趨����ͬ
    //	�����ͬ�����
    //	�����ͬ�����
    //getCandidate
    //	����һ��voteItem��
    //	������getCandidate�Ƿ����趨����ͬ
    //	�����ͬ�����
    @Test
    void getVoteValue() {
        VoteItem<String> voteItem = new VoteItem<>("233", "֧��");
        assertEquals("֧��", voteItem.getVoteValue());
        assertNotEquals("23333", voteItem.getVoteValue());
    }

    @Test
    void getCandidate() {
        VoteItem<String> voteItem = new VoteItem<>("333", "����");
        assertEquals("333", voteItem.getCandidate());
        assertNotEquals("23333", voteItem.getCandidate());
    }


}
