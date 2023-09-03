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
     * ����ͶƱ�������δ�趨�κ����ԣ�Ҳδ���κ�ͶƱ����
     *
     * @return һ��Poll<C>����
     */
    public static <C> Poll<C> create() {
        return new GeneralPollImpl<>();
    }

    /**
     * �趨ͶƱ��Ļ�������
     *
     * @param name     ͶƱ�������
     * @param date     ͶƱ��������
     * @param type     ͶƱ���ͣ�������ͶƱѡ���Լ���Ӧ�ķ���
     * @param quantity ��ѡ��������
     */
    public void setInfo(String name, Calendar date, VoteType type, int quantity);

    public String getName();

    public Calendar getDate();

    public VoteType getType();

    public int getQuantity();

    /**
     * �趨��ѡ��
     * ��һ����ѡ���󼯺ϼ��� Poll<C>����
     * �ͻ��˿��Զ�ε��ø÷�����Ӷ�κ�ѡ�ˡ�
     *
     * @param candidates ��ѡ���嵥
     */
    public void addCandidates(List<C> candidates);

    public List<C> getCandidates();

    /**
     * �趨ͶƱ�˼���Ȩ��
     * ��ͶƱ�˼��ϼ�����Ȩ�ش���Poll<C>����
     * �ͻ��˿��Զ�ε��ø÷�����Ӷ��ͶƱ�ˡ�
     *
     * @param voters KeyΪͶƱ�ˣ�ValueΪͶƱ�˵�Ȩ��
     */
    public void addVoters(Map<Voter, Double> voters);

    public Map<Voter, Double> getVoters();

    /**
     * ����һ��ͶƱ�˶�ȫ���ѡ�����ͶƱ
     * �� Vote ������ Poll<C>����
     *
     * @param vote һ��ͶƱ�˶�ȫ���ѡ�����ͶƱ��¼����
     */
    public void addVote(IVote<C> vote);

    public Map<IVote<C>, Boolean> getVotes();

    public void setLegitimacyChecker(LegitimacyChecker legitimacyChecker);

    /**
     * �������Ʊ
     * �����ж�ѡƱ�ĺϷ��ԣ�Ȼ����ڸ����Ϸ�ѡƱ��
     * ������ͶƱ��Ĺ�����м�Ʊ������ÿ����ѡ�˵ĵ÷֡�
     *
     * @param ss ����ȡ�ļ�Ʊ���������
     */
    public void statistics(StatisticsStrategy ss);

    /**
     * ���ػ�Ʊ���
     *
     * @return ��Ʊ���������CΪ��ѡ�ߡ�DoubleΪ��Ӧ����
     */
    public Map<C, Double> getStatistics();

    /**
     * ��������ѡ
     * ���ڼ�Ʊ�����������ͶƱ��Ĺ���
     * ������ѡ������ѡ��?����ѡ����Ϊ���ս����
     *
     * @param ss ����ȡ����ѡ���������
     */
    public void selection(SelectionStrategy ss);

    /**
     * �����ѡ���
     * ��ͶƱ���תΪһ���ַ��������ڿͻ��˶�����֡�
     *
     * @return һ����ʾ��ѡ������ַ�����ÿ�а�������ѡ����ID������
     */
    public String result();

    /**
     * ����Visitor���ģʽ��Ϊ��������չ��Ҫ���½ӿ�
     *
     * @param visitor ��Ҫ��չ���ܵ�ʵ�����ʵ������
     * @return ���ؽ��
     */
    public String accept(Visitor visitor);

}
