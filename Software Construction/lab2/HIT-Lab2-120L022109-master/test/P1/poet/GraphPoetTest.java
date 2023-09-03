/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //  输入文件并构造图，检查其是否如预期一样
    //  输入input，检查输出的poet是否一致
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    //  tests

    /**test Constructor
     *输入文件并构造图，检查其是否如预期一样
     */
    @Test
    public void testConstructor(){
        try {

            File text1 = new File("test/P1/poet/simpleTest.txt");
            GraphPoet graphPoet1 = new GraphPoet(text1);
            String[] strs1 = {"hello,","goodbye!"};
            Set<String> vertices1 = new HashSet<>(Arrays.asList(strs1));
            assertEquals(vertices1,graphPoet1.vertices());

            Map<String, Integer> source1 = new HashMap<>();
            source1.put("hello,",1);
            assertEquals(source1,graphPoet1.sources("goodbye!"));

            Map<String, Integer> target1 = new HashMap<>();
            target1.put("hello,",2);
            target1.put("goodbye!",1);
            assertEquals(target1,graphPoet1.targets("hello,"));

            //构造一个与给的例子一样的图
            File text = new File("test/P1/poet/seven-words.txt");
            GraphPoet graphPoet = new GraphPoet(text);

            String[] strs = {"to","explore","strange","seek","out","new","life","civilizations",
            "worlds","and"};
            Set<String> vertices = new HashSet<>(Arrays.asList(strs));
            assertEquals(vertices,graphPoet.vertices());

            Map<String, Integer> source = new HashMap<>();
            source.put("strange",1);
            source.put("out",1);
            source.put("and",1);
            assertEquals(source,graphPoet.sources("new"));

            Map<String, Integer> target = new HashMap<>();
            target.put("life",1);
            target.put("worlds",1);
            target.put("civilizations",1);
            assertEquals(target,graphPoet.targets("new"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**test poem
     * 输入input，检查输出的poet是否一致
     */
    @Test
    public void testPoem(){

        try {
            File text1 = new File("test/P1/poet/simpleTest1.txt");
            GraphPoet graphPoet1 = new GraphPoet(text1);
            assertEquals("Test of the system.",
                    graphPoet1.poem("Test the system."));
            //构造一个与给的例子一样的图
            File text = new File("test/P1/poet/seven-words.txt");
            GraphPoet graphPoet = new GraphPoet(text);

            assertEquals("Seek to explore strange new life and exciting synergies!",
                    graphPoet.poem("Seek to explore new and exciting synergies!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
