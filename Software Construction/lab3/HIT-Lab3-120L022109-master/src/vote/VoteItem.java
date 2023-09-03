package vote;

//immutable
public class VoteItem<C> {

    // ��ͶƱ������Եĺ�ѡ����
    private C candidate;
    // �Ժ�ѡ����ľ���ͶƱѡ����硰֧�֡��������ԡ���
    // ���豣�������ֵ����Ҫʱ���Դ�ͶƱ���VoteType�����в�ѯ�õ�
    private String value;

    // Rep Invariants
    // candidates��value����Ϊnull
    // Abstract Function
    // candidate��ͶƱ������Եĺ�ѡ����
    // value�Ժ�ѡ����ľ���ͶƱѡ����硰֧�֡��������ԡ���
    // Safety from Rep Exposure
    // getter�������ز��ɱ�Ķ��󣬲�������ڴ�й¶

    private boolean checkRep() {
        if (candidate == null) assert false;
        if (value == null) assert false;
        return true;
    }

    /**
     * ����һ��ͶƱ����� ���磺��Ժ�ѡ������������ͶƱѡ���ǡ�֧�֡�
     *
     * @param candidate ����Եĺ�ѡ����
     * @param value     ��������ͶƱѡ��
     */
    public VoteItem(C candidate, String value) {
        this.candidate = candidate;
        this.value = value;
        checkRep();
    }

    /**
     * �õ���ͶƱѡ��ľ���ͶƱ��
     *
     * @return ��ͶƱѡ��ľ���ͶƱ��
     */
    public String getVoteValue() {
        checkRep();
        return this.value;
    }

    /**
     * �õ���ͶƱѡ������Ӧ�ĺ�ѡ��
     *
     * @return ͶƱѡ������Ӧ�ĺ�ѡ��
     */
    public C getCandidate() {
        checkRep();
        return this.candidate;
    }


}
