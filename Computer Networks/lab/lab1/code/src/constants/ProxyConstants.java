package constants;

/**
 * ProxyConstants
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-10-03 20:19
 */
public class ProxyConstants {
    public static final String DEFAULT_CACHE_PATH = "default_cache.cah";
    public static final int TIMEOUT = 10000;//response time out upper bound
    public static final int RETRIEVE = 5;//retry connection 5 times
    public static final int CONNECT_PAUSE = 5000;//waiting for connection
    public static final int PROXY_PORT = 8888;

    public static final String ILLEGAL_REQUEST = "Illegal Request";
    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String NOT_MODIFIED = "Not Modified";
}