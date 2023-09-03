package main;

import constants.ProxyConstants;
import thread.ProxyThread;
import util.CacheUnit;
import util.SerializationUtil;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * ProxySever
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-10-03 20:13
 */
public class ProxySever {
    public static String cachePath;
    private static List<CacheUnit> cache;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket;
        Socket currsoket = null;
        /** users need to set up work space */

        System.out.println("==============请输入缓存的存储目录，输入 d 则设置为默认目录（程序同一目录下）=================");
        Scanner scanner = new Scanner(System.in);
        cachePath = scanner.nextLine();
        if (cachePath.equals("d")) {
            cachePath = ProxyConstants.DEFAULT_CACHE_PATH;
        }
        /** 初始化缓存写对象 */
        //readCache(cachePath);
        File cacheFile = new File(cachePath);
        //cacheFile.delete();
        if (!cacheFile.exists()) {
            cacheFile.createNewFile();
        }
        cache = ((SerializationUtil.readObjectForList(cacheFile)));
        System.out.println("=================================== 工作目录设置完毕====================================");

        try {
            //设置serversocket，绑定端口8888
            serverSocket = new ServerSocket(ProxyConstants.PROXY_PORT);
            int i = 0;

            //循环，持续监听从这个端口的所有请求
            while (true) {
                currsoket = serverSocket.accept();
                //启动一个新的线程来处理这个请求
                i++;
                System.out.println("启动第" + i + "个线程");
                new ProxyThread(currsoket).run();
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (currsoket != null) {
                currsoket.close();//及时关闭这个socket
            }
            writeCache();
        }


    }

    synchronized public static void addCacheUnit(CacheUnit cacheUnit) {
        cache.add(cacheUnit);
    }

    synchronized public static boolean ifCacheEmpty() {
        return cache.isEmpty();
    }

    synchronized public static boolean ifCached(String requestLine) {
        if (requestLine == null) return false;
        for (CacheUnit unit : cache
        ) {
            if (unit.getRequestLine().equals(requestLine)) return true;
        }
        return false;
    }

    synchronized public static CacheUnit getCacheUnit(Integer index) {
        return cache.get(index).copy();
    }

    synchronized public static void removeCacheUnit(int cacheUrlIndex) {
        cache.remove(cacheUrlIndex);
    }
    synchronized public static void writeCache(){
        SerializationUtil.writeObject(cache, new File(cachePath));
    }
    synchronized public static String getModifyTime(String requestLine, Boolean ifHasTime, Integer cacheUrlIndex) {

        if (requestLine == null) throw new IllegalArgumentException();
        String LastModifiTime = null;
        for (int i = 0; i < cache.size(); i++) {
            CacheUnit unit = cache.get(i);
            if (requestLine.equals(unit.getRequestLine())) {
                cacheUrlIndex = i;
                for (String line : unit.getLines()
                ) {
                    if (line.contains("http://"))
                        break;
                    if (line.contains("Last-Modified:")) {
                        LastModifiTime = cachePath.substring(line.indexOf("Last-Modified:"));
                        ifHasTime=true;
                        return LastModifiTime;
                    }
                    if (line.contains("<html>")) {
                        ifHasTime = false;
                        return LastModifiTime;
                    }
                }
            }
        }
        ifHasTime = false;
        return LastModifiTime;
//            if (requestLine.equals(unit.getRequestLine())) {
//                for (String head : unit.getHeadLines().keySet()
//                ) {
//                    if (ProxyConstants.LAST_MODIFIED.equals(head)) {
//                        ifHasTime = true;
//                        cacheUrlIndex = i;
//                        return unit.getHeadLines().get(head);
//                    }
//                }
//            }


    }
}