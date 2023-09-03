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
        proposalList.add(new Proposal("提案1", new GregorianCalendar()));
        proposalPoll.addCandidates(proposalList);
        proposalPoll.setInfo("商业提案", new GregorianCalendar(), new VoteType("“支持”(1)|“反对”(-1)|“弃权”(0)"), 1);
        assertThrows(IllegalArgumentException.class, () -> {
            proposalPoll.setInfo("商业提案", new GregorianCalendar(), new VoteType("“支持”(1)|“反对”(-1)|“弃权”(0)"), 2);
        });
        proposalList.add(new Proposal("提案2", new GregorianCalendar()));
        assertThrows(IllegalArgumentException.class, () -> {
            proposalPoll.addCandidates(proposalList);
        });
    }

}
