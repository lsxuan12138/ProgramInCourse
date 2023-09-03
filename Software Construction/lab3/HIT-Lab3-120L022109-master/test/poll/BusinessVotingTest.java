package poll;

import auxiliary.Proposal;
import org.junit.jupiter.api.Test;
import vote.VoteType;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BusinessVotingTest {

    // test strategy
    // TODO

    @Test
    void test() {
        Poll<Proposal> proposalPoll = new BusinessVoting();
        List<Proposal> proposalList = new ArrayList<>();
        proposalList.add(new Proposal("�᰸1", new GregorianCalendar()));
        proposalPoll.addCandidates(proposalList);
        proposalPoll.setInfo("��ҵ�᰸", new GregorianCalendar(), new VoteType("��֧�֡�(1)|�����ԡ�(-1)|����Ȩ��(0)"), 1);
        assertThrows(IllegalArgumentException.class, () -> {
            proposalPoll.setInfo("��ҵ�᰸", new GregorianCalendar(), new VoteType("��֧�֡�(1)|�����ԡ�(-1)|����Ȩ��(0)"), 2);
        });
        proposalList.add(new Proposal("�᰸2", new GregorianCalendar()));
        assertThrows(IllegalArgumentException.class, () -> {
            proposalPoll.addCandidates(proposalList);
        });
    }

}
