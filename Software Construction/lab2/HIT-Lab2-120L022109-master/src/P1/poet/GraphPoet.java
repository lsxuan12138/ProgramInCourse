/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import P1.graph.Graph;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * A graph-based poetry generator.
 *
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 *
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 *
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 *
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 *
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {

    private final Graph<String> graph = Graph.empty();

    // Abstraction function:
    //
    // Representation invariant:
    //   true
    // Safety from rep exposure:
    //   因为Graph是不可变的，不会改变泄露

    /**
     * Create a new poet with the graph from corpus (as described above).
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        Scanner scanner = new Scanner(corpus);
        String str = null,end=null;
        String[] line;
        while (scanner.hasNext()) {
            str = scanner.nextLine();
            line = str.trim().split(" ");

            for (int i = 0; i < line.length - 1; i++) {
                int beforeWeight = 0;
                if(end!=null&&i==0){

                    beforeWeight = graph.set(end.toLowerCase(), line[0].toLowerCase(), 1);
                    if (beforeWeight > 0) {
                        graph.set(end.toLowerCase(), line[0].toLowerCase(), beforeWeight + 1);
                    }
                }

                beforeWeight = graph.set(line[i].toLowerCase(), line[i + 1].toLowerCase(), 1);
                if (beforeWeight > 0) {
                    graph.set(line[i].toLowerCase(), line[i + 1].toLowerCase(), beforeWeight + 1);
                }
            }
            end = line[line.length-1];
        }
    }



    /**
     * Generate a poem.
     *
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        String[] words = input.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        int maxWeight = 0;
        String bridge = null;
        for (int i = 0; i < words.length - 1; i++) {
            result.append(words[i] + " ");
            Map<String, Integer> target = graph.targets(words[i].toLowerCase());
            Map<String, Integer> source = graph.sources(words[i + 1].toLowerCase());
            for (String str :
                    target.keySet()) {
                if (source.containsKey(str)) {
                    if (target.get(str) + source.get(str) > maxWeight) {
                        bridge = str;
                        maxWeight = target.get(str) + source.get(str);
                    }
                }
            }
            if (maxWeight != 0) {
                maxWeight = 0;
                result.append(bridge + " ");
            }
        }
        result.append(words[words.length - 1]);
        return result.toString();
    }

    //toString()

    @Override
    public String toString() {
        return graph.toString();
    }

    //增加几个用于测试的方法，因为Graph是不可变的，不会改变泄露

    public Set<String> vertices() {
        return graph.vertices();
    }

    public Map<String, Integer> sources(String target) {
        return graph.sources(target);
    }

    public Map<String, Integer> targets(String source) {
        return graph.targets(source);
    }
}
