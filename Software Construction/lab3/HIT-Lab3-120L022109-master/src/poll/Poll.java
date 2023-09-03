package poll;

import auxiliary.Voter;
import pattern.LegitimacyChecker;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import visitor.Visitor;
import vote.IVote;
import vote.VoteType;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface Poll<C> {

    /**
     * 创建投票活动，但尚未设定任何属性，也未有任何投票数据
     *
     * @return 一个Poll<C>对象
     */
    public static <C> Poll<C> create() {
        return new GeneralPollImpl<>();
    }

    /**
     * 设定投票活动的基本属性
     *
     * @param name     投票活动的名称
     * @param date     投票创建日期
     * @param type     投票类型，包含各投票选项以及相应的分数
     * @param quantity 拟选出的数量
     */
    public void setInfo(String name, Calendar date, VoteType type, int quantity);

    public String getName();

    public Calendar getDate();

    public VoteType getType();

    public int getQuantity();

    /**
     * 设定候选人
     * 将一个候选对象集合加入 Poll<C>对象。
     * 客户端可以多次调用该方法添加多次候选人。
     *
     * @param candidates 候选人清单
     */
    public void addCandidates(List<C> candidates);

    public List<C> getCandidates();

    /**
     * 设定投票人及其权重
     * 将投票人集合及各自权重传入Poll<C>对象。
     * 客户端可以多次调用该方法添加多次投票人。
     *
     * @param voters Key为投票人，Value为投票人的权重
     */
    public void addVoters(Map<Voter, Double> voters);

    public Map<Voter, Double> getVoters();

    /**
     * 接收一个投票人对全体候选对象的投票
     * 将 Vote 对象传入 Poll<C>对象。
     *
     * @param vote 一个投票人对全体候选对象的投票记录集合
     */
    public void addVote(IVote<C> vote);

    public Map<IVote<C>, Boolean> getVotes();

    public void setLegitimacyChecker(LegitimacyChecker legitimacyChecker);

    /**
     * 按规则计票
     * 首先判定选票的合法性，然后基于各个合法选票，
     * 按本次投票活动的规则进行计票，计算每个候选人的得分。
     *
     * @param ss 所采取的计票规则策略类
     */
    public void statistics(StatisticsStrategy ss);

    /**
     * 返回机票结果
     *
     * @return 计票结果，其中C为候选者。Double为对应分数
     */
    public Map<C, Double> getStatistics();

    /**
     * 按规则遴选
     * 基于计票结果，按本次投票活动的规则
     * 进行遴选，从中选出?个候选人作为最终结果。
     *
     * @param ss 所采取的遴选规则策略类
     */
    public void selection(SelectionStrategy ss);

    /**
     * 输出遴选结果
     * 将投票结果转为一个字符串，便于客户端对外呈现。
     *
     * @return 一个表示遴选结果的字符串，每行包含：候选对象ID、排序
     */
    public String result();

    /**
     * 利用Visitor设计模式，为将来的扩展需要留下接口
     *
     * @param visitor 将要扩展功能的实现类的实例对象
     * @return 返回结果
     */
    public String accept(Visitor visitor);

}
