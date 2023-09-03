package poll;

import auxiliary.Dish;

public class DinnerOrder extends GeneralPollImpl<Dish> implements Poll<Dish> {
    //RI
    // voters.size()<=quantity.size()<=voters.size()+5<=candidates.size()


    @Override
    protected void checkInfo() {
        super.checkInfo();
        if (!(super.getVoters().size() <= super.getQuantity() && super.getQuantity() <= super.getVoters().size() + 5))
            throw new IllegalArgumentException();
    }

    @Override
    protected void checkCandidates() {
        super.checkCandidates();
        if (!(super.getVoters().size() + 5 <= super.getCandidates().size())) assert false;
    }
}
