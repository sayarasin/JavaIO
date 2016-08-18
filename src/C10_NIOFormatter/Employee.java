package C10_NIOFormatter;

/**
 * For test
 * Created by sayarasin on 08/11/2016.
 */
public class Employee {

    private String name;
    private int    empno;


    public Employee(String name, int empno) {
        this.name = name;
        this.empno= empno;
    }

    @Override
    public String toString() {
        return name + ": " + empno;
    }
}
