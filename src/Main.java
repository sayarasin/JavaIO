import C10_NIOFormatter.NIOFormatter;
import C11_NIO2FileSystem.DumpText;
import C4_WriterAndReader.WriterAndReader;
import C5_NIOBuffer.NIOBuffer;
import C6_NIOChannel.NIOChannel;
import C6_NIOChannel.NIOFileLock;
import C6_NIOChannel.NIOPipedChannel;
import C6_NIOChannel.NIOSocketChannel;
import C7_Selection.Selection;
import C8_RegularExpression.RegEx;
import C9_NIOCharsets.Charsets;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

/**
 * Created by sayarasin on 07/17/2016.
 */

public class Main {

    public static void main(String [] args) {

        C12_FileTreeWalking.FileTreeDemo ftd = new C12_FileTreeWalking.FileTreeDemo();
        try {
            ftd.FTWDemo("E:\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
