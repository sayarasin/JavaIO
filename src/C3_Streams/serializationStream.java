package C3_Streams;

import java.io.*;

/**
 * This is a serialization stream demo
 * Created by sayarasin on 07/29/2016.
 */
public class SerializationStream {

    final static String FILENAME = "employee.dat";

    public void GeneralSerialize() {

        ObjectOutputStream os = null;
        ObjectInputStream  is = null;

        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
            os = new ObjectOutputStream(fos);

            Employee employee1 = new Employee("John Doe", 32);
            os.writeObject(employee1);

            FileInputStream  fis = new FileInputStream(FILENAME);
            is = new ObjectInputStream(fis);
            Employee employee2 = (Employee)is.readObject();

            System.out.println(employee1.getName() + ":" + employee1.getAge());
            System.out.println(employee2.getName() + ":" + employee2.getAge());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) { }
            }
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) { }
            }
        }
    }


    public void SubClassSerialize() {

        ObjectOutputStream os = null;
        ObjectInputStream  is = null;

        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
            os = new ObjectOutputStream(fos);

            SerEmployee employee1 = new SerEmployee("John Doe");
            os.writeObject(employee1);

            FileInputStream  fis = new FileInputStream(FILENAME);
            is = new ObjectInputStream(fis);
            SerEmployee employee2 = (SerEmployee)is.readObject();

            System.out.println(employee1.toString());
            System.out.println(employee2.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) { }
            }
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) { }
            }
        }
    }
}
