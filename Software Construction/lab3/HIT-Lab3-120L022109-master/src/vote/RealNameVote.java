package vote;

import auxiliary.Voter;

import java.util.Objects;
import java.util.Set;

/**
 * ����ͶƱ��ѡƱ
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-06-05 20:49
 */
public class RealNameVote<C> extends Vote<C> {
    //ͶƱ��
    private Voter voter;

    /**
     * ����һ��ѡƱ����
     * <p>
     * ����������Ƹ÷��������õĲ���
     *
     * @param set
     */
    public RealNameVote(Voter voter, Set<VoteItem<C>> set) {
        super(set);
        this.voter = voter;
    }

    public Voter getVoter() {
        return voter;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RealNameVote<?> that = (RealNameVote<?>) o;
        return voter.equals(that.voter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), voter);
    }
}