package poll;

import auxiliary.Dish;
import org.junit.jupiter.api.Test;
import vote.VoteType;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DinnerOrderTest {

    // test strategy
    // TODO

    @Test
    void test() {
        Poll<Dish> dishPoll = new DinnerOrder();
        List<Dish> candidates = new ArrayList<>();
        candidates.add(new Dish("������˿", 34.0));
        candidates.add(new Dish("������", 50.0));
        candidates.add(new Dish("��������", 26.0));
        candidates.add(new Dish("���ּ�", 30.0));
        candidates.add(new Dish("��ϲ����", 40.0));
        candidates.add(new Dish("ʨ��ͷ", 52.0));
        dishPoll.addCandidates(candidates);
        VoteType voteType = new VoteType("��ϲ����(2)|����ϲ����(0)|������ν��(1)");
        dishPoll.setInfo("���", new GregorianCalendar(), voteType, 3);
        assertThrows(AssertionError.class, () -> {
            dishPoll.setInfo("���", new GregorianCalendar(), voteType, 7);
        });
    }

}
