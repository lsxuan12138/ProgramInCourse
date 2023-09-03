package poll;

import auxiliary.Proposal;
import vote.VoteType;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class BusinessVoting extends GeneralPollImpl<Proposal> implements Poll<Proposal> {
    //RI
    // candidates.size()=1
    // quantity=1

    public BusinessVoting() {
        super();
    }

    @Override
    public void setInfo(String name, Calendar date, VoteType type, int quantity) {
        if (quantity != 1) throw new IllegalArgumentException("quantity!=1");
        super.setInfo(name, date, type, quantity);
    }

    @Override
    public void addCandidates(List<Proposal> candidates) {
        if (candidates.size() != 1) {
            throw new IllegalArgumentException();
        }
        super.addCandidates(candidates);
    }

    @Override
    protected void checkInfo() {
        super.checkInfo();
        if (super.getQuantity() != 1) assert false;
    }

    @Override
    protected void checkCandidates() {
        super.checkCandidates();
        if (super.getCandidates().size() > 1) assert false;
    }

    @Override
    public String result() {
        Map<Proposal, Double> results1 = super.results;
        if (results1.size() != 1) return "表决未通过";
        else return "表决通过，占比" + results1.values().iterator().next() * 100 + "%";
    }
}