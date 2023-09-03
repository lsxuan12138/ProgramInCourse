package third;

public class Clock {
    //年
    private Screen year;
    //月
    private Screen month;
    //日
    private Screen day;
    //小时
    private Screen hour;
    //分钟
    private Screen minute;
    //秒
    private Screen second;

    //构造方法
    public Clock(int hour, int minute, int second, int year, int month, int day){
        //设置当前时间 及上下限
        this.hour = new Screen(hour,0,24);
        this.minute = new Screen(minute,0,60);
        this.second = new Screen(second,0,60);
        this.year = new Screen(year,0,9999);
        this.month = new Screen(month,1,13);
        this.day = new Screen(day,1,32);
        //根据年份（是否为闰年）及月份，设置 天 的上限
        switch (month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                this.day.setMax(32);
                break;
            case 4: case 6: case 9: case 11:
                this.day.setMax(31);
                break;
            case 2:
                if(this.year.getValue()%4==0 && this.year.getValue()%100!=0){
                    this.day.setMax(30);
                }
                else {
                    this.day.setValue(29);
                }
                break;
        }
    }
    //增加一秒
    public void tick(){
        //根据 当前的（可能已经变化过） 年份（是否为闰年）及月份，设置 天 的上限
        switch (month.getValue()){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                day.setMax(31);
                break;
            case 4: case 6: case 9: case 11:
                day.setMax(30);
                break;
            case 2:
                if(year.getValue()%4==0 && year.getValue()%100!=0){
                    day.setMax(29);
                }
                else {
                    day.setMax(28);
                }
                break;
        }
        //变化日期
        //若进位则调用其他Screen的increase()方法
        if(second.increase()){
            if(minute.increase()){
                if(hour.increase()){
                    if(day.increase()){
                        if(month.increase()){
                            if(year.increase());
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {

        int y = year.getValue();
        int m = month.getValue();
        int d = day.getValue();
        //计算星期几
        int W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400)%7;
        String week = "";
        switch (W){
            case 0:week="一";break;
            case 1:week="二";break;
            case 2:week="三";break;
            case 3:week="四";break;
            case 4:week="五";break;
            case 5:week="六";break;
            case 6:week="日";break;

        }
        //return String.format("%4d:%2d:%2d:%2d:%2d:%2d",year.getValue(),month.getValue(),day.getValue(),hour.getValue(),minute.getValue(),second.getValue());
        return year.getValue()
                +":"+//若小于10，则前面补0
                (month.getValue()<10?"0"+month.getValue():month.getValue())
                +":"+
                (day.getValue()<10?"0"+day.getValue():day.getValue())
                +":"+
                (hour.getValue()<10?"0"+hour.getValue():hour.getValue())
                +":"+
                (minute.getValue()<10?"0"+minute.getValue():minute.getValue())
                +":"+
                (second.getValue()<10?"0"+second.getValue():second.getValue())
                +"星期"+week
                ;
    }
}
