package C12_FileTreeWalking;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumSet;

/**
 * This is a file tree demo
 * Created by sayarasin on 08/17/2016.
 */
public class FileTreeDemo {

    public void FTWDemo(String dir) throws IOException {

        Files.walkFileTree(Paths.get(dir), EnumSet.noneOf(FileVisitOption.class),
                2, new DoNothingVisitor());
    }

}
