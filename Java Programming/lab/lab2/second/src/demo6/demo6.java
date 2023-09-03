package second.src.demo6;

public class demo6 {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine(5,5,200);
        Person person = new Person(50,20);

        vm.buyBeer(person.getAge(), person.getMoney());
        vm.buyCola(person.getMoney());
    }
}
