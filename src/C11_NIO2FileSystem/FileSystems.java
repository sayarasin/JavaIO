package C11_NIO2FileSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.nio.file.spi.FileSystemProvider;
import java.time.Instant;
import java.util.List;

/**
 * This is NIO2 file system demo
 * Created by sayarasin on 08/11/2016.
 */
public class FileSystems {

    public void GetProviders() {
        List<FileSystemProvider> providers =
                FileSystemProvider.installedProviders();

        for(FileSystemProvider provider : providers)
            System.out.println(provider);
    }


    public void Path() {

        FileSystem fsDefault = java.nio.file.FileSystems.getDefault();
        Path path = fsDefault.getPath("E:", "a", "b", "c");
        System.out.println(path);
        System.out.printf("File name: %s%n", path.getFileName());
        for(int i = 0 ; i < path.getNameCount() ; ++i)
            System.out.println(path.getName(i));
        System.out.printf("Parent: %s%n", path.getParent());
        System.out.printf("Root: %s%n", path.getRoot());
        System.out.printf("Subpath [0, 2): %s%n", path.subpath(0, 2));
        System.out.printf("Path %s is a %s path %n", path, path.isAbsolute() ?
                "absolute" : "relative");
        if(!path.isAbsolute())
            System.out.printf("the absolute path is %s%n", path.toAbsolutePath().toString());

        System.out.println();
    }


    public void MorePath() {

        Path path = Paths.get("reports", ".", "2015", "jan");
        System.out.println(path);
        System.out.println(path.normalize());
        System.out.println();
        path = Paths.get("reports", "2015", "..", "jan");
        System.out.println(path);
        System.out.println(path.relativize(Paths.get("reports", "2016", "mar")));

        try {
            Path root = java.nio.file.FileSystems.getDefault().getRootDirectories()
                    .iterator().next();
            if(root != null) {
                System.out.printf("Root %s%n", root.toString());
                Path path1 = Paths.get(root.toString(), "reports", "2016", "mar");
                System.out.printf("Path: %s%n", path1);
                System.out.println(path.relativize(path1));
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println();

        path = Paths.get("reports", "2015");
        System.out.println(path);
        System.out.println(path.resolve("apr"));
        System.out.println();
        Path path2 = Paths.get("reports", "2015", "jan");
        System.out.println(path2);
        System.out.println(path2.getParent());
        System.out.println(path2.resolveSibling(Paths.get("mar")));
        System.out.println(path2.resolve(Paths.get("mar")));
    }


    public void StoreDetail(String point) throws IOException {

        FileStore fs = Files.getFileStore(Paths.get(point));

        System.out.printf("Total space: %d%n", fs.getTotalSpace());
        System.out.printf("Unallocated space: %d%n", fs.getUnallocatedSpace());
        System.out.printf("Usable space: %d%n", fs.getUsableSpace());
        System.out.printf("Read only: %b%n", fs.isReadOnly());
        System.out.printf("Name: %s%n", fs.name());
        System.out.printf("Type: %s%n%n", fs.type());

    }


    private boolean isSupport(Class<? extends FileAttributeView> clazz) {
        return Files.getFileAttributeView(Paths.get("."), clazz) != null;
    }

    public void FileAttributeSupport() {
        System.out.printf("Supports basic: %b%n",
                isSupport(BasicFileAttributeView.class));
        System.out.printf("Supports posix: %b%n",
                isSupport(PosixFileAttributeView.class));
        System.out.printf("Supports acl: %b%n",
                isSupport(AclFileAttributeView.class));
    }


    public void FileAttribute(String file, boolean isSet) throws IOException {

        Path path = Paths.get(file);

        System.out.printf("Creation time: %s%n",
                Files.getAttribute(path, "creationTime"));
        System.out.printf("File key: %s%n",
                Files.getAttribute(path, "fileKey"));
        System.out.printf("Is directory: %b%n",
                Files.getAttribute(path, "isDirectory"));
        System.out.printf("Is other: %b%n",
                Files.getAttribute(path, "isOther"));
        System.out.printf("Is regular file: %b%n",
                Files.getAttribute(path, "isRegularFile"));
        System.out.printf("Is symbol link: %b%n",
                Files.getAttribute(path, "isSymbolicLink"));
        System.out.printf("Last access time: %s%n",
                Files.getAttribute(path, "lastAccessTime"));
        System.out.printf("Last modified time: %s%n",
                Files.getAttribute(path, "lastModifiedTime"));
        System.out.printf("Size: %d%n",
                Files.getAttribute(path, "size"));

        if(isSet) {
            Files.setAttribute(path, "lastModifiedTime",
                    FileTime.from(Instant.now().plusSeconds(60)));
            System.out.printf("Last modified time: %s%n",
                    Files.getAttribute(path, "lastModifiedTime"));
        }
    }


    public void FileAttributeDOS(String file, boolean isSet) throws IOException {

        Path path = Paths.get(file);

        System.out.printf("Is archive: %b%n",
                Files.getAttribute(path, "dos:archive"));
        System.out.printf("Is hidden: %b%n",
                Files.getAttribute(path, "dos:hidden"));
        System.out.printf("Is readonly: %b%n",
                Files.getAttribute(path, "dos:readonly"));
        System.out.printf("Is system: %b%n",
                Files.getAttribute(path, "dos:system"));

        if(isSet) {
            Files.setAttribute(path, "dos:system", true);
            System.out.printf("Is system: %b%n",
                    Files.getAttribute(path, "dos:system"));
        }
    }

}
