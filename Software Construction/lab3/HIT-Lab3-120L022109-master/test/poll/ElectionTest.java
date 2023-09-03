package poll;

import auxiliary.Person;
import org.junit.jupiter.api.Test;
import pattern.AnonymousGeneralLegitimacyChecker;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElectionTest {

    // test strategy
    // TODO

    @Test
    void test() {
        Map<String, Integer> types = new HashMap<>();
        types.put("支持", 1);
        types.put("反对", -1);
        types.put("弃权", 0);
        VoteType vt = new VoteType(types);

        Person p1 = new Person("A", 19);
        Person p2 = new Person("B", 20);
        Person p3 = new Person("C", 19);
        Person p4 = new Person("D", 19);
        Person p5 = new Person("E", 21);
        List<Person> candidates = new ArrayList<>();
        candidates.add(p1);
        candidates.add(p2);
        candidates.add(p3);
        candidates.add(p4);
        candidates.add(p5);
        VoteItem<Person> vi51 = new VoteItem<>(p1, "反对");
        VoteItem<Person> vi52 = new VoteItem<>(p2, "反对");
        VoteItem<Person> vi53 = new VoteItem<>(p3, "支持");
        VoteItem<Person> vi54 = new VoteItem<>(p4, "支持");
        VoteItem<Person> vi55 = new VoteItem<>(p5, "支持");
        Set<VoteItem<Person>> vis5 = new HashSet<>();
        vis5.add(vi51);
        vis5.add(vi52);
        vis5.add(vi53);
        vis5.add(vi54);
        vis5.add(vi55);
        VoteItem<Person> vi31 = new VoteItem<>(p1, "弃权");
        VoteItem<Person> vi32 = new VoteItem<>(p2, "支持");
        VoteItem<Person> vi33 = new VoteItem<>(p3, "支持");
        VoteItem<Person> vi34 = new VoteItem<>(p4, "支持");
        VoteItem<Person> vi35 = new VoteItem<>(p5, "支持");
        Set<VoteItem<Person>> vis3 = new HashSet<>();
        vis3.add(vi31);
        vis3.add(vi32);
        vis3.add(vi33);
        vis3.add(vi34);
        vis3.add(vi35);
        Poll<Person> poll = new Election();
        Vote<Person> vote1 = new Vote<>(vis5);
        Vote<Person> vote2 = new Vote<>(vis3);
        poll.addCandidates(candidates);
        poll.setInfo("班干选举", new GregorianCalendar(), vt, 3);
        poll.setLegitimacyChecker(new AnonymousGeneralLegitimacyChecker());
        poll.addVote(vote1);
        poll.addVote(vote2);
        Map<Vote<Person>, Boolean> test = new HashMap<>();
        test.put(vote1, true);
        test.put(vote2, false);

        assertEquals(test, poll.getVotes());

    }

}
