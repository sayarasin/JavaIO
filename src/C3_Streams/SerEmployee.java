package C3_Streams;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * SerEmployee
 * Created by sayarasin on 07/29/2016.
 */
public class SerEmployee implements Serializable{

    private NonSerialEmployee employee;
    private String name;

    SerEmployee(String name) {
        this.name = name;
        employee = new NonSerialEmployee(name);
    }


    private void writeObject(ObjectOutputStream os) throws IOException {
        os.writeUTF(name);
    }

    private void readObject(ObjectInputStream is) throws IOException {
        name = is.readUTF();
        employee = new NonSerialEmployee(name);
    }

    @Override
    public String toString() {
        return employee.toString();
    }
}
