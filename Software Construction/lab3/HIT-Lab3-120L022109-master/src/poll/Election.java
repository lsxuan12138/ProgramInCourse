package poll;

import auxiliary.Person;
import vote.IVote;
import vote.VoteItem;

public class Election extends GeneralPollImpl<Person> implements Poll<Person> {

    //RI
    // quantity<=candidates.size()
    // candidates.size()>=1
    // RI�ڸ����������㣬����Ҫ��дcheckRep

    @Override
    public void addVote(IVote<Person> vote) {
        int count = 0;
        for (VoteItem<Person> voteItem :
                vote.getVoteItems()) {
            if (voteItem.getVoteValue().equals("֧��")) count++;
        }
        if (count > super.getQuantity()) {
            super.votes.put(vote, false);
            return;
        }
        super.addVote(vote);
    }
}
