package util;

import java.io.Serializable;

/**
 * CacheUnit
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-10-03 20:51
 */
public class CacheUnit implements Serializable {
    private static final long serialVersionUID = 2333333333333333L;
    private String requestLine;

    private StringBuilder content;

    public CacheUnit(String requestLine) {
        this.requestLine = requestLine;
        content = new StringBuilder();
    }

    public CacheUnit(String requestLine, StringBuilder content) {
        this.requestLine = requestLine;
        this.content = content;
    }

    public CacheUnit copy() {
        return new CacheUnit(this.requestLine, this.content);
    }


    public String getRequestLine() {
        return requestLine;
    }

    public void setRequestLine(String requestLine) {
        this.requestLine = requestLine;
    }

    public StringBuilder getContent() {
        return content;
    }

    public void setContent(StringBuilder content) {
        this.content = content;
    }

    public String getCacheContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(requestLine).append("\r\n");

        if (!("".equals(content) || content == null)) sb.append(content);
        return sb.toString();
    }

    public String[] getLines() {
        return content.toString().split("\r\n");
    }
}