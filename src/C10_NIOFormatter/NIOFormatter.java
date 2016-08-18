package C10_NIOFormatter;

import C3_Streams.*;

import java.util.Locale;

/**
 * This is a formatter demo
 * Created by sayarasin on 08/11/2016.
 */
public class NIOFormatter {

    public void Demo() {
        EmployeeEx emp = new EmployeeEx("John Doe", 1000);
        System.out.printf("[%s]%n", emp);
        System.out.printf(Locale.FRENCH, "[%s]%n", emp);
        System.out.printf("[%S]%n", emp);
        System.out.printf("[%10.3s]%n", emp);
        System.out.printf("[%-10.3s]%n", emp);
        System.out.printf("[%#s]%n", emp);
    }
}
