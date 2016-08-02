package C3_Streams;

import java.io.Serializable;

/**
 * Employee demo
 * Created by sayarasin on 07/29/2016.
 */
public class Employee implements Serializable {

    private String name;
    private int age;
    private transient char [] password;

    public  Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
