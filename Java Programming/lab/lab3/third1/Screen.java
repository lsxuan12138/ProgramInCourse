package third1;

public class Screen {
    //当前值
    private int value;
    //最小值
    //对月份及日是1，对其他是0
    private int min;
    //最大值
    private int max;

    //构造方法
    public Screen(int time,int min ,int max) {
        this.value = time;
        this.min =min;
        this.max = max;
    }
    //若进位，返回true，不进位，返回false
    public boolean increase(){
        if(value>=max-1){
            value=min;
            return true;
        }
        value++;
        return false;
    }
    //setter and getter
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
