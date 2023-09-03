package vote;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VoteTypeTest {

    // test strategy
    // checkLegality
    //	* 测试合法输入
    //	* 测试不合法输入
    //getScoreByOption
    //	* 测试包含的type
    //	 * 测试不包含的type

    /**
     * 测试正则表达式的构造方法
     */
    @Test
    void testRegex() {
        //符合表达式“支持”(-1)|“反对”(0)
        //只有一项
        VoteType voteType1 = new VoteType("“支持”(1)");
        Map<String, Integer> test1 = new HashMap<String, Integer>(3);
        test1.put("支持", 1);
        assertEquals(test1, voteType1.getOptions());
        //多项
        test1.put("反对", -1);
        test1.put("弃权", 0);
        VoteType voteType2 = new VoteType("“支持”(1)|“反对”(-1)|“弃权”(0)");
        assertEquals(test1, voteType2.getOptions());
        //符合“支持”|“反对”
        VoteType voteType3 = new VoteType("“支持”");
        Map<String, Integer> test2 = new HashMap<>();
        test2.put("支持", 1);
        assertEquals(test2, voteType3.getOptions());
        test2.put("反对", 1);
        test2.put("弃权", 1);
        VoteType voteType4 = new VoteType("“支持”|“反对”|“弃权”");
        assertEquals(test2, voteType4.getOptions());
    }

    /**
     * 测试不合法输入
     * 测试合法输入
     * 测试不包含的输入
     */
    @Test
    void checkLegality() {
        Map<String, Integer> test = new HashMap<String, Integer>(3);
        test.put("支持", 1);
        test.put("反对", -1);
        test.put("弃权", 0);
        VoteType voteType = new VoteType(test);

        // 测试不合法输入
        assertThrows(IllegalArgumentException.class, () -> {
            voteType.checkLegality(null);
        });
        //测试合法输入
        assertTrue(voteType.checkLegality("支持"));
        assertTrue(voteType.checkLegality("反对"));
        assertTrue(voteType.checkLegality("弃权"));
        //测试不包含的输入
        assertFalse(voteType.checkLegality("11111"));

    }

    /**
     * 测试不合法输入
     * 测试包含的type
     * 测试不包含的type
     */
    @Test
    void getScoreByOption() {
        Map<String, Integer> test = new HashMap<String, Integer>(3);
        test.put("支持", 1);
        test.put("反对", -1);
        test.put("弃权", 0);
        VoteType voteType = new VoteType(test);
        //测试不合法输入
        assertThrows(IllegalArgumentException.class, () -> {
            voteType.getScoreByOption(null);
        });
        //测试包含的type
        assertEquals(1, voteType.getScoreByOption("支持"));
        assertEquals(-1, voteType.getScoreByOption("反对"));
        assertEquals(0, voteType.getScoreByOption("弃权"));
        //测试不包含的type
        assertThrows(IllegalArgumentException.class, () -> {
            voteType.getScoreByOption("233");
        });
    }
}
