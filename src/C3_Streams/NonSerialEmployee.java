package C3_Streams;

/**
 * Not implement Serializable interface
 * Created by sayarasin on 07/29/2016.
 */
public class NonSerialEmployee {

    private String name;

    NonSerialEmployee(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
