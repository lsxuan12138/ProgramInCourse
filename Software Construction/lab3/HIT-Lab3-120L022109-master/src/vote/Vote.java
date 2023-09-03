package vote;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//immutable
public class Vote<C> implements IVote<C> {

    // ȱʡΪ��������ͶƱ��������Ҫ����ͶƱ�˵���Ϣ

    // һ��ͶƱ�˶����к�ѡ�����ͶƱ���
    private Set<VoteItem<C>> voteItems = new HashSet<>();
    // ͶƱʱ��
    private Calendar date = Calendar.getInstance();

    // Rep Invariants
    // voteItems��û��null
    // Abstract Function
    // voteItemsһ��ͶƱ�˶����к�ѡ�����ͶƱ���
    // dateͶƱʱ��
    // Safety from Rep Exposure
    // �����Կ���

    private boolean checkRep() {
        for (VoteItem<C> voteItem :
                voteItems) {
            if (voteItem == null) assert false;
        }
        return true;
    }

    /**
     * ����һ��ѡƱ����
     * <p>
     * ����������Ƹ÷��������õĲ���
     */
    public Vote(Set<VoteItem<C>> set) {

        voteItems.addAll(set);
    }

    /**
     * ��ѯ��ѡƱ�а���������ͶƱ��
     *
     * @return ����ͶƱ��
     */
    public Set<VoteItem<C>> getVoteItems() {
        checkRep();
        return new HashSet<>(voteItems);
    }

    /**
     * һ���ض���ѡ���Ƿ������ѡƱ��
     *
     * @param candidate ����ѯ�ĺ�ѡ��
     * @return �������ú�ѡ�˵�ͶƱ��򷵻�true������false
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
