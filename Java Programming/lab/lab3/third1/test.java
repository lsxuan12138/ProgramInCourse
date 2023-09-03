package third1;

public class test {
    public static void main(String[] args) {
        //创建Scanner对象
        java.util.Scanner in = new java.util.Scanner(System.in);
        //输入顺序int hour, int minute, int second, int year, int month, int day
        Clock clock = new Clock(in.nextInt(), in.nextInt(), in.nextInt(),in.nextInt(), in.nextInt(), in.nextInt());//起始时间
        //循环1000次测试效果
        for(int i =0;i<1000;i++){
            clock.tick();
            System.out.println(clock);
        }
        //关闭 输入流
        in.close();
    }
}
