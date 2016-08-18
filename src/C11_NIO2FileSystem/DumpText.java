package C11_NIO2FileSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * This is a dump texture demo
 * Created by sayarasin on 08/16/2016.
 */
public class DumpText {

    public void DumpForReadAll(String file) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(file));
        for(String line : lines)
            System.out.println(line);

    }


    public void DumpForReader(String file) throws IOException {

        String line;
        BufferedReader br = Files.newBufferedReader(Paths.get(file));

        while((line = br.readLine()) != null)
            System.out.println(line);
    }

}
