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
        candidates.add(new Dish("ÓãÏãÈâË¿", 34.0));
        candidates.add(new Dish("ºìÉÕÈâ", 50.0));
        candidates.add(new Dish("ÓãÏãÇÑ×Ó", 26.0));
        candidates.add(new Dish("ÏãËÖ¼¦", 30.0));
        candidates.add(new Dish("ËÄÏ²Íè×Ó", 40.0));
        candidates.add(new Dish("Ê¨×ÓÍ·", 52.0));
        dishPoll.addCandidates(candidates);
        VoteType voteType = new VoteType("¡°Ï²»¶¡±(2)|¡°²»Ï²»¶¡±(0)|¡°ÎÞËùÎ½¡±(1)");
        dishPoll.setInfo("µã²Ë", new GregorianCalendar(), voteType, 3);
        assertThrows(AssertionError.class, () -> {
            dishPoll.setInfo("µã²Ë", new GregorianCalendar(), voteType, 7);
        });
    }

}
