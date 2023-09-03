package poll;

import auxiliary.Voter;
import pattern.LegitimacyChecker;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import visitor.Visitor;
import vote.IVote;
import vote.VoteType;

import java.util.*;

public class GeneralPollImpl<C> implements Poll<C> {

    // ͶƱ�������
    private String name;
    // ͶƱ������ʱ��
    private Calendar date;
    // ��ѡ���󼯺�
    private List<C> candidates;
    // ͶƱ�˼��ϣ�keyΪͶƱ�ˣ�valueΪ���ڱ���ͶƱ����ռȨ��
    private Map<Voter, Double> voters;
    // ��ѡ���ĺ�ѡ�����������
    private int quantity;
    // ����ͶƱ����õ�ͶƱ���ͣ��Ϸ�ѡ����Զ�Ӧ�ķ�����
    private VoteType voteType;
    // ����ѡƱ����
    protected Map<IVote<C>, Boolean> votes;
    protected LegitimacyChecker legitimacyChecker;
    // ��Ʊ�����keyΪ��ѡ����valueΪ��÷�
    protected Map<C, Double> statistics;
    // ��ѡ�����keyΪ��ѡ����valueΪ������λ��
    protected Map<C, Double> results;

    // Rep Invariants
    // name!=null date!=null
    // ��candidates��Ϊnull���������ظ�Ԫ�أ�candidates>=quantity
    // ��voters��Ϊnullʱ��voters��key��value����Ϊnull
    // quantity>=1
    // voteType��Ϊnull
    // Abstract Function
    //	// ͶƱ�������
    //	String name;
    //	// ͶƱ������ʱ��
    //	Calendar date;
    //	// ��ѡ���󼯺�
    //	List<C> candidates;
    //	// ͶƱ�˼��ϣ�keyΪͶƱ�ˣ�valueΪ���ڱ���ͶƱ����ռȨ��
    //	Map<Voter, Double> voters;
    //	// ��ѡ���ĺ�ѡ�����������
    //	int quantity;
    //	// ����ͶƱ����õ�ͶƱ���ͣ��Ϸ�ѡ����Զ�Ӧ�ķ�����
    //	VoteType voteType;
    //	// ����ѡƱ����
    //	Map<Vote<C>,Boolean> votes;
    //	//�Ϸ��Լ����
    //	LegitimacyChecker legitimacyChecker;
    //	// ��Ʊ�����keyΪ��ѡ����valueΪ��÷�
    //	Map<C, Double> statistics;
    //	// ��ѡ�����keyΪ��ѡ����valueΪ������λ��
    //	Map<C, Double> results;
    // Safety from Rep Exposure
    //

    protected boolean checkRep() {
        // name!=null date!=null
        // quantity>=1��quantity<=candidates
        // voteType��Ϊnull
        // ��candidates��Ϊnull���������ظ�Ԫ��,candidates>=1
        // ��voters��Ϊnullʱ��voters��key��value����Ϊnull
        checkCandidates();
        checkVoters();
        checkInfo();
        return true;
    }

    protected void checkInfo() {
        if (name == null || date == null || quantity < 1 || voteType == null) assert false;
        if (candidates.size() < quantity) assert false;
    }

    protected void checkCandidates() {
        if (candidates.size() < 1) assert false;
        for (int i = 0; i < candidates.size(); i++) {
            for (int j = i + 1; j < candidates.size(); j++) {
                assert !candidates.get(i).equals(candidates.get(j));
            }
        }
    }

    protected void checkVoters() {
        if (voters.size() != 0) {
            for (Voter voter :
                    voters.keySet()) {
                if (voter == null) assert false;
                if (voters.get(voter) == null) assert false;
            }
        }

    }

    /**
     * ���캯��
     */
    public GeneralPollImpl() {
        candidates = new ArrayList<>();
        voters = new HashMap<>();
        votes = new HashMap<>();
        statistics = new HashMap<>();
        results = new HashMap<>();
    }

    @Override
    public void setInfo(String name, Calendar date, VoteType type, int quantity) {
        if (name == null || date == null || type == null || quantity < 1) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.date = new GregorianCalendar();
        this.date.setTime(date.getTime());
        this.voteType = type;
        this.quantity = quantity;
        checkInfo();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Calendar getDate() {
        Calendar newDate = new GregorianCalendar();
        newDate.setTime(date.getTime());
        return newDate;
    }

    @Override
    public VoteType getType() {
        return this.voteType;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void addVoters(Map<Voter, Double> voters) {
        if (voters == null) {
            throw new IllegalArgumentException();
        }
        this.voters.putAll(voters);
        checkVoters();
    }

    @Override
    public Map<Voter, Double> getVoters() {
        return new HashMap<>(this.voters);
    }

    @Override
    public void addCandidates(List<C> candidates) {
        if (candidates == null) {
            throw new IllegalArgumentException();
        }
        for (C item :
                candidates) {
            if (!this.candidates.contains(item)) this.candidates.add(item);
        }
        checkCandidates();
    }

    @Override
    public List<C> getCandidates() {
        return new ArrayList<>(this.candidates);
    }

    @Override
    public void addVote(IVote<C> vote) {
        // �˴�Ӧ���ȼ���ѡƱ�ĺϷ��Բ���ǣ�Ϊ������չ���޸�rep
        if (legitimacyChecker == null) throw new RuntimeException("����������Ҫ�ĺϷ��Լ����");
        votes.put(vote, legitimacyChecker.checkLegitimacy(vote, candidates, voteType));
        checkRep();
    }


    @Override
    public Map<IVote<C>, Boolean> getVotes() {
        return new HashMap<>(votes);
    }

    @Override
    public void setLegitimacyChecker(LegitimacyChecker legitimacyChecker) {
        this.legitimacyChecker = legitimacyChecker;
    }

    @Override
    public void statistics(StatisticsStrategy ss) {
        // �˴�Ӧ���ȼ�鵱ǰ��ӵ�е�ѡƱ�ĺϷ���
        legitimacyChecker.checkLegitimacy(votes, voters);
        //ͳ��ѡƱ
        statistics.putAll(ss.statistics(candidates, votes, voters, voteType));
        checkRep();
    }

    @Override
    public Map<C, Double> getStatistics() {
        checkRep();
        return new HashMap<>(statistics);
    }

    @Override
    public void selection(SelectionStrategy ss) {
        results.putAll(ss.select(statistics, quantity));
        checkRep();
    }


    @Override
    public String result() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Map.Entry<C, Double>> list = new ArrayList<>(results.entrySet());

        list.sort(new Comparator<Map.Entry<C, Double>>() {
            //��������
            public int compare(Map.Entry<C, Double> o1,
                               Map.Entry<C, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });
        int i = 1;
        for (Map.Entry<C, Double> o :
                list) {
            stringBuilder.append(o.getKey().toString()).append("\t").append(i++).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
