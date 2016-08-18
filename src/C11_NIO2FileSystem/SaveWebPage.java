package C11_NIO2FileSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a save web page demo
 * Created by sayarasin on 08/16/2016.
 */
public class SaveWebPage {

    public void Save(String webURL) throws IOException {

        URL url = new URL(webURL);

        InputStreamReader isr = new InputStreamReader(url.openStream());
        BufferedReader br = new BufferedReader(isr);
        List<String> lines = new ArrayList<>();
        String line;

        while((line = br.readLine()) != null)
            lines.add(line);
        Files.write(Paths.get("E:\\page.html"), lines);
    }

    public void SaveEx(String webURL) throws IOException {

        URL url = new URL(webURL);

        InputStreamReader isr = new InputStreamReader(url.openStream());
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("e:\\page2.html"));
        String line;
        while((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }

    public void SaveEx1(String webURL) throws IOException {

        URL url = new URL(webURL);
        Files.copy(url.openStream(), Paths.get("e:\\page3.html"));
    }
}
