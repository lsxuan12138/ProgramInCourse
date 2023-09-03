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

    // 投票活动的名称
    private String name;
    // 投票活动发起的时间
    private Calendar date;
    // 候选对象集合
    private List<C> candidates;
    // 投票人集合，key为投票人，value为其在本次投票中所占权重
    private Map<Voter, Double> voters;
    // 拟选出的候选对象最大数量
    private int quantity;
    // 本次投票拟采用的投票类型（合法选项及各自对应的分数）
    private VoteType voteType;
    // 所有选票集合
    protected Map<IVote<C>, Boolean> votes;
    protected LegitimacyChecker legitimacyChecker;
    // 计票结果，key为候选对象，value为其得分
    protected Map<C, Double> statistics;
    // 遴选结果，key为候选对象，value为其排序位次
    protected Map<C, Double> results;

    // Rep Invariants
    // name!=null date!=null
    // 当candidates不为null则其中无重复元素，candidates>=quantity
    // 当voters不为null时，voters中key和value都不为null
    // quantity>=1
    // voteType不为null
    // Abstract Function
    //	// 投票活动的名称
    //	String name;
    //	// 投票活动发起的时间
    //	Calendar date;
    //	// 候选对象集合
    //	List<C> candidates;
    //	// 投票人集合，key为投票人，value为其在本次投票中所占权重
    //	Map<Voter, Double> voters;
    //	// 拟选出的候选对象最大数量
    //	int quantity;
    //	// 本次投票拟采用的投票类型（合法选项及各自对应的分数）
    //	VoteType voteType;
    //	// 所有选票集合
    //	Map<Vote<C>,Boolean> votes;
    //	//合法性检查器
    //	LegitimacyChecker legitimacyChecker;
    //	// 计票结果，key为候选对象，value为其得分
    //	Map<C, Double> statistics;
    //	// 遴选结果，key为候选对象，value为其排序位次
    //	Map<C, Double> results;
    // Safety from Rep Exposure
    //

    protected boolean checkRep() {
        // name!=null date!=null
        // quantity>=1，quantity<=candidates
        // voteType不为null
        // 当candidates不为null则其中无重复元素,candidates>=1
        // 当voters不为null时，voters中key和value都不为null
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
     * 构造函数
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
        // 此处应首先检查该选票的合法性并标记，为此需扩展或修改rep
        if (legitimacyChecker == null) throw new RuntimeException("请先设置需要的合法性检查器");
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
        // 此处应首先检查当前所拥有的选票的合法性
        legitimacyChecker.checkLegitimacy(votes, voters);
        //统计选票
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
            //降序排序
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
