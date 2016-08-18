package C11_NIO2FileSystem;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This is a directory stream demo
 * Created by sayarasin on 08/16/2016.
 */
public class DirStream {

    private void readHelper(Path p, DirectoryStream.Filter<Path> fliter)
            throws IOException {
        DirectoryStream<Path> ds = Files.newDirectoryStream(p, fliter);
        for(Path p1 : ds) {
            if(Files.isDirectory(p1))
                readHelper(p1, fliter);
            else
                System.out.println(p1);
        }
    }

    public void read(String dir, String suffix) throws IOException {
        Path path = Paths.get(dir);
        DirectoryStream.Filter<Path> filter =
                new DirectoryStream.Filter<Path>() {
                    @Override
                    public boolean accept(Path entry) throws IOException {
                        if(Files.isDirectory(entry))
                            return true;
                        return entry.toString().endsWith(suffix);
                    }
                };

        readHelper(path, filter);
    }
}
