package second.src.demo6;

public class VendingMachine {
    private int cola;
    private int beer;
    private int balance;

    public VendingMachine(int cola, int beer,int balance) {
        this.cola = cola;
        this.beer = beer;
        this.balance = balance;
    }
    public boolean buyCola( int money){
        if(cola<=0){
            System.out.println("可乐已经卖完了");
            return false;
        }
        if(money<3){
            System.out.println("你没有足够的钱");
            return false;
        }
        System.out.println("你买到一瓶可乐");
        balance+=3;
        cola--;
        return true;
    }
    public boolean buyBeer( int age,int money){
        if(age<18){
            System.out.println("你未成年，不能购买啤酒");
        }
        if(beer<=0){
            System.out.println("啤酒已经卖完了");
            return false;
        }
        if(money<5){
            System.out.println("你没有足够的钱");
            return false;
        }
        System.out.println("你买到一瓶啤酒");
        balance+=5;
        beer--;
        return true;
    }
}
