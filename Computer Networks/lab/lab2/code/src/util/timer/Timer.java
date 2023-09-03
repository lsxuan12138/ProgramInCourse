package util.timer;

import constants.Constants;
import host.Host;

/**
 * 计时器
 */
public class Timer extends Thread{
    private int timeout;
    private Host host;
    private final Object lock = new Object();
    static int min = 0, sec = 0;
    private boolean pause = false;//阻塞标志（默认关闭）

    public Timer(Host host) {
        this.timeout = Constants.DEFAULT_TIMEOUT;
        this.host = host;
    }
    public Timer(Host host,int timeout) {
        this.timeout=timeout;
        this.host = host;
    }
    public void pauseThread() {
        //System.out.println("计时器暂停");
        this.pause = true;
    }//将阻塞信号开启

    public void clear() {
        //System.out.println("计时器清零");
        min = 0;
        sec = 0;
        //show();
    }

    void onPause() {
        synchronized (lock) {
            try {
                lock.wait();//阻塞线程方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }//阻塞方法

    public void resumeThread() {
        //System.out.println("计时器恢复");
        this.pause = false;
        synchronized (lock) {
            lock.notify();//恢复线程方法
        }
    }//恢复方法

    void show() {
        System.out.println("当前时间为：" + min + "：" + sec);
    }

    void mmshow() {
        int mm = (min * 60 + sec) * 100;
        System.out.print("毫秒示数为：" + mm + '\n');

    }


    public void run() {
        super.run();

        while (true) {
            if (pause) {

                onPause();
            }
            try {
                if(sec>timeout) {
                    //System.out.println("超时");
                    host.timeOut();
                    this.clear();
                }
                sec++;
                //show();

                if (sec == 60) {
                    sec = 0;
                    min++;
                }

                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }//线程运行方法
}

