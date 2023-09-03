package vote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VoteTest {

    // test strategy
    //��������
    //getVoteValue
    //	����һ��voteItem��
    //	������getVoteValue�Ƿ����趨����ͬ
    //	�����ͬ�����
    //	�����ͬ�����
    //getCandidate
    //	����һ��voteItem��
    //	������getCandidate�Ƿ����趨����ͬ
    //	�����ͬ�����
    //	�����ͬ�����


    Vote<String> vote;

    @BeforeEach
    public void initAVote() {

        //VoteType<String> voteType = new VoteType();
        Set<VoteItem<String>> set = new HashSet<>();
        VoteItem<String> voteItem1 = new VoteItem<>("a", "֧��");
        VoteItem<String> voteItem2 = new VoteItem<>("b", "֧��");
        VoteItem<String> voteItem3 = new VoteItem<>("c", "����");
        set.add(voteItem1);
        set.add(voteItem2);
        set.add(voteItem3);
        vote = new Vote<>(set);
    }

    @Test
    void getVoteItems() {
        Set<VoteItem<String>> set = new HashSet<>();
        VoteItem<String> voteItem1 = new VoteItem<>("a", "֧��");
        VoteItem<String> voteItem2 = new VoteItem<>("b", "֧��");
        VoteItem<String> voteItem3 = new VoteItem<>("c", "����");
        set.add(voteItem1);
        set.add(voteItem2);
        set.add(voteItem3);
        //�����ͬ����
        assertEquals(set, vote.getVoteItems());
        //��ⲻͬ������
        set.add(new VoteItem<String>("d", "��Ȩ"));
        assertNotEquals(set, vote.getVoteItems());
    }

    @Test
    void candidateIncluded() {
        //������������
        assertTrue(vote.candidateIncluded("a"));
        assertTrue(vote.candidateIncluded("b"));
        assertTrue(vote.candidateIncluded("c"));
        //��鲻����������
        assertFalse(vote.candidateIncluded("d"));
    }
}
