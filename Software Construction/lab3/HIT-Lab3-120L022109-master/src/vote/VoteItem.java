package vote;

//immutable
public class VoteItem<C> {

    // 本投票项所针对的候选对象
    private C candidate;
    // 对候选对象的具体投票选项，例如“支持”、“反对”等
    // 无需保存具体数值，需要时可以从投票活动的VoteType对象中查询得到
    private String value;

    // Rep Invariants
    // candidates和value都不为null
    // Abstract Function
    // candidate本投票项所针对的候选对象
    // value对候选对象的具体投票选项，例如“支持”、“反对”等
    // Safety from Rep Exposure
    // getter方法返回不可变的对象，不会造成内存泄露

    private boolean checkRep() {
        if (candidate == null) assert false;
        if (value == null) assert false;
        return true;
    }

    /**
     * 创建一个投票项对象 例如：针对候选对象“张三”，投票选项是“支持”
     *
     * @param candidate 所针对的候选对象
     * @param value     所给出的投票选项
     */
    public VoteItem(C candidate, String value) {
        this.candidate = candidate;
        this.value = value;
        checkRep();
    }

    /**
     * 得到该投票选项的具体投票项
     *
     * @return 该投票选项的具体投票项
     */
    public String getVoteValue() {
        checkRep();
        return this.value;
    }

    /**
     * 得到该投票选项所对应的候选人
     *
     * @return 投票选项所对应的候选人
     */
    public C getCandidate() {
        checkRep();
        return this.candidate;
    }


}
