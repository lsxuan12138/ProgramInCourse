package vote;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//immutable
public class Vote<C> implements IVote<C> {

    // 缺省为“匿名”投票，即不需要管理投票人的信息

    // 一个投票人对所有候选对象的投票项集合
    private Set<VoteItem<C>> voteItems = new HashSet<>();
    // 投票时间
    private Calendar date = Calendar.getInstance();

    // Rep Invariants
    // voteItems中没有null
    // Abstract Function
    // voteItems一个投票人对所有候选对象的投票项集合
    // date投票时间
    // Safety from Rep Exposure
    // 防御性拷贝

    private boolean checkRep() {
        for (VoteItem<C> voteItem :
                voteItems) {
            if (voteItem == null) assert false;
        }
        return true;
    }

    /**
     * 创建一个选票对象
     * <p>
     * 可以自行设计该方法所采用的参数
     */
    public Vote(Set<VoteItem<C>> set) {

        voteItems.addAll(set);
    }

    /**
     * 查询该选票中包含的所有投票项
     *
     * @return 所有投票项
     */
    public Set<VoteItem<C>> getVoteItems() {
        checkRep();
        return new HashSet<>(voteItems);
    }

    /**
     * 一个特定候选人是否包含本选票中
     *
     * @param candidate 待查询的候选人
     * @return 若包含该候选人的投票项，则返回true，否则false
     */
    public boolean candidateIncluded(C candidate) {
        if (candidate == null) throw new IllegalArgumentException();
        for (VoteItem<C> voteItem :
                voteItems) {
            if (voteItem.getCandidate().equals(candidate)) {
                checkRep();
                return true;
            }
        }
        checkRep();
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote<?> vote = (Vote<?>) o;
        return voteItems.equals(vote.voteItems) && date.equals(vote.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteItems, date);
    }
}
