package P2;

import java.util.HashSet;
import java.util.Set;

public class Person {

    private final String name;
    /**
     * 常量池，确保一次运行中，不会出现相同名字
     */
    public static final Set<String> namePool;
    static {
        namePool = new HashSet<>();
    }

    /**构造方法
     * 创造空结点
     */
    public Person() {
        this.name = null;
    }

    /**构造方法
     * 创造结点
     * @param name 节点名称
     */
    public Person(String name) {
        if(Person.namePool.contains(name)){
            System.out.println("你输入的名字已存在");
            System.exit(-1);
        }
        namePool.add(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }



    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Person)) {
            throw new IllegalArgumentException("class exception");
        }
        Person person = (Person) o;
        if(this.getName()==null||person.getName()==null){
            throw new IllegalArgumentException("null point");
        }

        return name.equals(person.getName());

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
