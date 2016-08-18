package C6_NIOChannel;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * This is a file lock demo
 * Created by sayarasin on 08/08/2016.
 */
public class NIOFileLock {

    private final static int MAXQUERIES = 1500;
    private final static int MAXUPDATES = 1500;
    private final static int RECLEN = 16;

    static ByteBuffer bufferB = ByteBuffer.allocate(RECLEN);
    static IntBuffer  bufferI = bufferB.asIntBuffer();

    static int counter = 1;

    public void FileLockDemo(boolean writer) {

        try {
            RandomAccessFile raf = new RandomAccessFile("E:\\LockFile.dat",
                    writer ? "rw" : "r");

            FileChannel fc = raf.getChannel();
            if(writer)
                update(fc);
            else
                query(fc);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void query(FileChannel fc) throws IOException {

        for(int i = 0 ; i < MAXQUERIES ; ++i) {
            System.out.println("acquiring shared lock");
            FileLock lock = fc.lock(0, RECLEN, true);
            try {
                bufferB.clear();
                fc.read(bufferB, 0);

                int a = bufferI.get(0);
                int b = bufferI.get(1);
                int c = bufferI.get(2);
                int d = bufferI.get(3);

                System.out.println("Reading: " + a + " " +
                    b + " " +
                    c + " " +
                    d);
                if(a * 2 != b || a * 3 != c || a * 4 != d) {
                    System.out.println("error");
                    return;
                }
            }
            finally {
                lock.release();
            }
        }
    }


    private void update(FileChannel fc) throws IOException
    {
        for(int i = 0 ; i < MAXUPDATES ; ++i) {

            FileLock lock = fc.lock(0, RECLEN, false); /* false is important */
            try {

                bufferI.clear();
                int a = counter;
                int b = counter * 2;
                int c = counter * 3;
                int d = counter * 4;
                System.out.println("Writing: " + a + " " +
                        b + " " +
                        c + " " +
                        d);
                bufferI.put(a);
                bufferI.put(b);
                bufferI.put(c);
                bufferI.put(d);

                counter++;

                bufferB.clear();
                fc.write(bufferB, 0);

            } finally {
              lock.release();
            }

        }
    }
}
