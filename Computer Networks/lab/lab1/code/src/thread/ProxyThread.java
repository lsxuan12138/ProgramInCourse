package thread;

import constants.ProxyConstants;
import util.CacheUnit;
import main.ProxySever;
import util.Filter;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.StringTokenizer;

/**
 * ProxyThread
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-10-03 20:16
 */
public class ProxyThread implements Runnable {
    private Socket socket;

    public ProxyThread(Socket socket) {
        this.socket = socket;
        try {
            this.socket.setSoTimeout(ProxyConstants.TIMEOUT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String ip = socket.getLocalAddress().getHostAddress();
        String requestLine = null;
        BufferedReader clientBufferedReader=null;
        try {
//            if(Filter.ipList.contains(ip)){
//                System.out.println("---------用户已被屏蔽");return;
//            }
            clientBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("从浏览器读取第一行....");
            requestLine = clientBufferedReader.readLine();
            if(requestLine==null){
                System.out.println("错误请求");
                return;
            }

            if (!Filter.filter(requestLine)) {
                System.out.println("请求" + requestLine + "已被过滤");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if(clientBufferedReader!=null)clientBufferedReader.close();
                if(socket!=null)socket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        System.out.println(requestLine);
        String[] hostAndPort = getHostAndPort(requestLine);
        String targetHost = hostAndPort[0];
        String targetPort = hostAndPort[1];
        System.out.println("提取的主机名:" + targetHost + " 提取的端口号: " + targetPort);
        String replacement = Filter.map(targetHost);
        String oldHost = targetHost;
        if (replacement != null) {
            requestLine=requestLine.replace(targetHost,replacement);

            targetHost = replacement;
            System.out.println("请求已被重定向");
        }

        //尝试连接目标主机
        Socket accessSocket = null;
        int retry = ProxyConstants.RETRIEVE;
        try {
            while (retry-- != 0 && (targetHost != null)) {
                accessSocket = new Socket(targetHost, Integer.parseInt(targetPort));
                if (accessSocket != null) break;
            }
            Thread.sleep(ProxyConstants.CONNECT_PAUSE);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        InputStream webInputStream = null;
        BufferedReader webBufferedReader = null;
        PrintWriter webPrintWriter = null;
        InputStream clientInputStream = null;
        OutputStream clientOutputStream = null;
        PrintWriter clientOutPrintWriter = null;
        if (accessSocket == null) {
            System.out.println("目的主机：" + targetHost + ":" + targetPort + "连接失败");
        } else {
            System.out.println("目的主机：" + targetHost + "连接成功");
            System.out.println("请求将发送至:" + targetHost + ":" + targetPort);
            try {
                accessSocket.setSoTimeout(ProxyConstants.TIMEOUT);

                webInputStream = accessSocket.getInputStream();//获取网站返回的响应
                webBufferedReader = new BufferedReader(new InputStreamReader(webInputStream));
                webPrintWriter = new PrintWriter(accessSocket.getOutputStream());
                clientInputStream = socket.getInputStream();//创建从浏览器获取请求的输入流
                clientOutputStream = socket.getOutputStream();//创建向浏览器发送响应的流
                clientOutPrintWriter = new PrintWriter(clientOutputStream);

                boolean ifCached = ProxySever.ifCached(requestLine);
                CacheUnit cacheUnit = new CacheUnit(requestLine);
                if (!ifCached) {
                    //将请求直接发往网站，并获取响应，记录响应至缓存
                    sendRequestToWeb(replacement,oldHost,requestLine, webPrintWriter, clientBufferedReader);
                    sendResponseToClient(false, -1, webInputStream, clientOutputStream, clientOutPrintWriter, cacheUnit);
                } else {//寻找之前缓存过该请求
                    Boolean ifHasTime = false;
                    Integer cacheUrlIndex = -1;
                    String modifyTime = ProxySever.getModifyTime(requestLine, ifHasTime, cacheUrlIndex);
                    System.out.println("提取到的modifytime：" + modifyTime);
                    String info = null;
                    //如果缓存的内容里面该请求是没有Last-Modify属性的，就不用向服务器查询If-Modify了，否则向服务器查询If-Modify
                    if (ifHasTime) {
                        StringBuilder msg = new StringBuilder();
                        msg.append(requestLine).append("\r\n");
                        System.out.print("向服务器发送确认修改时间请求:\n" + msg);
                        msg.append("Host: ").append(targetHost).append("\r\n");
                        msg.append("If-modified-since: ").append(modifyTime).append("\r\n\r\n");
                        webPrintWriter.write(msg.toString());
                        webPrintWriter.flush();
                        info = webBufferedReader.readLine();
                        System.out.println("服务器发回的信息是：" + info);
                    }
                    if (!ifHasTime||info.contains(ProxyConstants.NOT_MODIFIED) ) {//如果服务器给回的响应是304 Not Modified，就将缓存的数据直接发送给浏览器
                        System.out.println("使用缓存数据");
                        StringBuilder sb = new StringBuilder();
                        //
                        if (cacheUrlIndex != -1) {
                            sb.append(ProxySever.getCacheUnit(cacheUrlIndex).getContent()).append("\r\n\r\n");
                            clientOutputStream.write(sb.toString().getBytes(), 0, sb.toString().length());
                            clientOutputStream.flush();
                        }

                    } else {
                        //服务器返回的不是304 Not Modified的话，就将服务器的响应直接转发到浏览器并记录缓存就好了
                        System.out.println("有更新，使用新的数据");
                        clientOutputStream.write(info.getBytes());
                        sendResponseToClient(true, cacheUrlIndex, webInputStream, clientOutputStream, clientOutPrintWriter, cacheUnit);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (webBufferedReader != null) webBufferedReader.close();
                    if (webPrintWriter != null) webPrintWriter.close();
                    if (clientBufferedReader != null) clientBufferedReader.close();
                    if (clientOutPrintWriter != null) clientOutPrintWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        try {
            if (socket != null) socket.close();
            if (accessSocket != null) accessSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequestToWeb(String replacement,String oldHost,String requestLine, PrintWriter webPrintWriter, BufferedReader clientBufferedReader) throws IOException {
        String buffer = requestLine;

        System.out.print("发送请求:\n");
        try {
            while (!buffer.equals("")) {
                buffer += "\r\n";
                if (replacement!=null&&buffer.contains("Host: ")) {
                    buffer=buffer.replace(oldHost,replacement);
                }
                webPrintWriter.write(buffer);
                System.out.println(buffer);
                buffer = clientBufferedReader.readLine();
            }

        }catch (SocketTimeoutException ignored){

        }

        webPrintWriter.write("\r\n");
        webPrintWriter.flush();
    }

    public void sendResponseToClient(boolean ifUpdate, Integer cacheUrlIndex, InputStream webInputStream, OutputStream clientOutputStream, PrintWriter clientOutPrintWriter, CacheUnit cacheUnit) {
//        HttpResponse response = null;
//        response = new HttpResponse(webBufferedReader);
//        clientOutPrintWriter.write(response.toString());
//        clientOutPrintWriter.flush();
//        assert response != null;
//        CacheUnit cacheUnit = new CacheUnit(requestLine, response);
//
//        ProxySever.addCacheUnit(cacheUnit);
        //use httpResponse
        byte[] bytes = new byte[2048];
        int length = 0;
        try {
            while (true) {
                if ((length = webInputStream.read(bytes)) > 0) {
                    clientOutputStream.write(bytes, 0, length);
                    String show_response = new String(bytes, 0, bytes.length);
                    System.out.println("服务器发回的消息是:\n---\n" + show_response + "\n---");
                    //write cache
                    cacheUnit.getContent().append(bytes).append("\r\n");
                    //if(webInputStream.available()<bytes.length)break;
                } else break;
            }

            clientOutPrintWriter.write("\r\n");
            clientOutPrintWriter.flush();
        } catch (SocketTimeoutException ignored){

        }catch (IOException e) {
            e.printStackTrace();
        }
        if (ifUpdate) {
            ProxySever.removeCacheUnit(cacheUrlIndex);
        }
        ProxySever.addCacheUnit(cacheUnit);

    }

    public static String[] getHostAndPort(String requestLine) {

        String host;
        String port = null;
        String[] result = new String[2];
        int index;
        int portIndex;
        String temp;

        StringTokenizer stringTokenizer = new StringTokenizer(requestLine);
        stringTokenizer.nextToken();//丢弃第一个字串 这是请求类型 比如GET POST
        temp = stringTokenizer.nextToken();//这个字串里面有主机名和端口

        int index1 = temp.indexOf("//");
        host = temp.substring(index1 == -1 ? 0 : index1 + 2);//比如 http://news.sina.com.cn/gov/2017-12-13/doc-ifypsqiz3904275.shtml -> news.sina.com.cn/gov/2017-12-13/doc-ifypsqiz3904275.shtml
        index = host.indexOf("/");
        if (index == -1) index = temp.length();
        if (index != -1) {
            host = host.substring(0, index);//比如 news.sina.com.cn/gov/2017-12-13/doc-ifypsqiz3904275.shtml -> news.sina.com.cn
            portIndex = host.indexOf(":");
            if (portIndex != -1) {
                port = host.substring(portIndex + 1);//比如 www.ghostlwb.com:8080 -> 8080
                host = host.substring(0, portIndex);
            } else {//没有找到端口号，则加上默认端口号80
                port = "80";
            }
        }
        result[0] = host;
        result[1] = port;
        return result;
    }
    public static String getURL(String requestLine) {
        String[] questLine = requestLine.split(" ");
        if (questLine.length != 3) throw new RuntimeException();
        return questLine[1];
    }
}