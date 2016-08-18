package C6_NIOChannel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * This is a mapped file demo
 * Created by sayarasin on 08/08/2016.
 */
public class NIOMappedFile {

    public void Access(String file) {

        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            FileChannel fc = raf.getChannel();
            long size = fc.size();

            System.out.println("Size: " + size);

            MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, size);

            while(buffer.remaining() > 0)
                System.out.println((char)buffer.get());

            System.out.println();
            System.out.println();

            for (int i = 0 ; i < buffer.limit() / 2 ; ++i) {
                byte b1 = buffer.get(i);
                byte b2 = buffer.get(buffer.limit() - i - 1);
                buffer.put(i, b2);
                buffer.put(buffer.limit() - i - 1, b1);
            }

            buffer.flip();

            while(buffer.remaining() > 0)
                System.out.println((char)buffer.get());

            fc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
