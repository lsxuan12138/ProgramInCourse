package vote;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VoteTypeTest {

    // test strategy
    // checkLegality
    //	* ���ԺϷ�����
    //	* ���Բ��Ϸ�����
    //getScoreByOption
    //	* ���԰�����type
    //	 * ���Բ�������type

    /**
     * ����������ʽ�Ĺ��췽��
     */
    @Test
    void testRegex() {
        //���ϱ��ʽ��֧�֡�(-1)|�����ԡ�(0)
        //ֻ��һ��
        VoteType voteType1 = new VoteType("��֧�֡�(1)");
        Map<String, Integer> test1 = new HashMap<String, Integer>(3);
        test1.put("֧��", 1);
        assertEquals(test1, voteType1.getOptions());
        //����
        test1.put("����", -1);
        test1.put("��Ȩ", 0);
        VoteType voteType2 = new VoteType("��֧�֡�(1)|�����ԡ�(-1)|����Ȩ��(0)");
        assertEquals(test1, voteType2.getOptions());
        //���ϡ�֧�֡�|�����ԡ�
        VoteType voteType3 = new VoteType("��֧�֡�");
        Map<String, Integer> test2 = new HashMap<>();
        test2.put("֧��", 1);
        assertEquals(test2, voteType3.getOptions());
        test2.put("����", 1);
        test2.put("��Ȩ", 1);
        VoteType voteType4 = new VoteType("��֧�֡�|�����ԡ�|����Ȩ��");
        assertEquals(test2, voteType4.getOptions());
    }

    /**
     * ���Բ��Ϸ�����
     * ���ԺϷ�����
     * ���Բ�����������
     */
    @Test
    void checkLegality() {
        Map<String, Integer> test = new HashMap<String, Integer>(3);
        test.put("֧��", 1);
        test.put("����", -1);
        test.put("��Ȩ", 0);
        VoteType voteType = new VoteType(test);

        // ���Բ��Ϸ�����
        assertThrows(IllegalArgumentException.class, () -> {
            voteType.checkLegality(null);
        });
        //���ԺϷ�����
        assertTrue(voteType.checkLegality("֧��"));
        assertTrue(voteType.checkLegality("����"));
        assertTrue(voteType.checkLegality("��Ȩ"));
        //���Բ�����������
        assertFalse(voteType.checkLegality("11111"));

    }

    /**
     * ���Բ��Ϸ�����
     * ���԰�����type
     * ���Բ�������type
     */
    @Test
    void getScoreByOption() {
        Map<String, Integer> test = new HashMap<String, Integer>(3);
        test.put("֧��", 1);
        test.put("����", -1);
        test.put("��Ȩ", 0);
        VoteType voteType = new VoteType(test);
        //���Բ��Ϸ�����
        assertThrows(IllegalArgumentException.class, () -> {
            voteType.getScoreByOption(null);
        });
        //���԰�����type
        assertEquals(1, voteType.getScoreByOption("֧��"));
        assertEquals(-1, voteType.getScoreByOption("����"));
        assertEquals(0, voteType.getScoreByOption("��Ȩ"));
        //���Բ�������type
        assertThrows(IllegalArgumentException.class, () -> {
            voteType.getScoreByOption("233");
        });
    }
}
