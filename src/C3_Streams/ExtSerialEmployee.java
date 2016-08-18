package C3_Streams;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by sayarasin on 07/30/2016.
 */
public class ExtSerialEmployee implements Externalizable {

    private String name;
    private int age;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("writeExternal() called");
        out.writeUTF(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("readExternal() called");
        name = in.readUTF();
        age = in.readInt();
    }

    public ExtSerialEmployee(){
        System.out.println("Employee() called");
    }

    public ExtSerialEmployee(String name, int age) {
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
