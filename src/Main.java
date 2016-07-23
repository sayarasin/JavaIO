import C1_Basics_And_APIs.BaseOperation;
import C2_Random_Access_File.PartsDB;
import C3_Streams.InputOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sayarasin on 07/17/2016.
 */

public class Main {

    public static void main(String [] args) {

        /*
        try {
            PartsDB db = new PartsDB("./test.db");
            System.out.println("Current database record: " + db.NumRecs());
            db.Insert("1-9009-3323-4x", "Wiper Blade Micro Edge", 30, 2468);
            System.out.println("Current database record: " + db.NumRecs());
            PartsDB.Part part = db.Select(0);
            System.out.println("Num:" + part.getPartNum()
                    + " Desc:" + part.getDesc()
                    + " Qty:" + part.getQty()
                    + " Cost:" + part.getCost());
            db.Delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        InputOutputStream ios = new InputOutputStream();
        ios.PipedStream();
    }
}
