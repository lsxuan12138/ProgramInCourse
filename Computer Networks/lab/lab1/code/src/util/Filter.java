package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filter
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-10-03 21:46
 */
public class Filter {

    private static final Map<String, String> MAP;

    static {
        MAP = new HashMap<>();
        MAP.put("http://www.tsinghua.edu.cn/", "http://www.hit.edu.cn/");
        MAP.put("today.hit.edu.cn", "jwts.hit.edu.cn");
    }
private static final List<String> filterList;
    static {
        filterList = new ArrayList<>();
        filterList.add("CONNECT");
        filterList.add("www.4399.com");
    }
    /**
     * 过滤某些请求
     * @param requestLine
     * @return
     */
    public static boolean filter(String requestLine) {
        if (requestLine == null) return false;
        for (String str:filterList
             ) {
            if(requestLine.contains(str))return false;
        }
        return true;
    }

    /**
     * 获取钓鱼映射
     * @param requestLine
     * @return
     */
    public static String map(String requestLine) {
        return MAP.get(requestLine);
    }

    /**
     * 需要过滤的用户ip
     */
    public static final List<String> ipList;
    static {
        ipList = new ArrayList<>();
        ipList.add("127.0.0.1");
    }
}