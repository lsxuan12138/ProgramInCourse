package P3;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Person {

    private String name;
    public static Set<String> namePool;
    static {
        namePool = new HashSet<String>();
    }
    public Person() {
    }

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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Person)) {
            throw new IllegalArgumentException();
        }
        Person person = (Person) o;
        if(this.getName()==null||person.getName()==null){
            throw new IllegalArgumentException();
        }
        if (this.getName().equals(person.getName())){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
