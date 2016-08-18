package C12_FileTreeWalking;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * This is a demo of visitor
 * Created by sayarasin on 08/17/2016.
 */
public class DoNothingVisitor extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {
        System.out.printf("preVisitDirectory: %s%n", dir);
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
        System.out.printf("visitFile: %s%n", file);
        System.out.printf("    lastModifiedTime: %s%n",
                attrs.lastModifiedTime());
        System.out.printf("    size: %d%n%n",
                attrs.size());
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc)
            throws IOException {
        System.out.printf("visitFiled: %s %s%n%n", file, exc);
        return super.visitFileFailed(file, exc);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc)
            throws IOException {
        System.out.printf("postVisitDirectory: %s %s%n%n", dir, exc);
        return super.postVisitDirectory(dir, exc);
    }
}
