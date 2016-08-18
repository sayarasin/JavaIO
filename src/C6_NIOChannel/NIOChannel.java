package C6_NIOChannel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * This is a channel demo
 * Created by sayarasin on 08/03/2016.
 */
public class NIOChannel {

    private static final int BUFFER_LENGTH = 1;


    private void CopyApproaches1(ReadableByteChannel src, WritableByteChannel dst)
            throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_LENGTH);
        while(src.read(buffer) != -1) {
            buffer.flip();
            dst.write(buffer);
            buffer.compact();
        }
        buffer.flip();
        while(buffer.hasRemaining())
            dst.write(buffer);
    }


    private void CopyApproaches2(ReadableByteChannel src, WritableByteChannel dst)
            throws IOException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_LENGTH);
        while(src.read(buffer) != -1) {
            buffer.flip();
            while(buffer.hasRemaining())
                dst.write(buffer);
            buffer.clear();
        }
    }


    public void Copy(InputStream input, OutputStream output) {

        try(ReadableByteChannel src = Channels.newChannel(input);
            WritableByteChannel dst = Channels.newChannel(output)) {

            CopyApproaches2(src, dst);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void ScatterAndGather() {

        try {
            FileInputStream fis = new FileInputStream("E:\\x.dat");
            ScatteringByteChannel src = (ScatteringByteChannel) Channels.newChannel(fis);
            ByteBuffer buffer1 = ByteBuffer.allocateDirect(5);
            ByteBuffer buffer2 = ByteBuffer.allocateDirect(3);
            ByteBuffer [] buff = {buffer1, buffer2};
            src.read(buff);

            buffer1.flip();
            while(buffer1.hasRemaining())
                System.out.println(buffer1.get());
            System.out.println();

            buffer2.flip();
            while(buffer2.hasRemaining())
                System.out.println(buffer2.get());
            System.out.println();

            buffer1.rewind();
            buffer2.rewind();

            FileOutputStream fos = new FileOutputStream("E:\\y.dat");
            GatheringByteChannel dst = (GatheringByteChannel) Channels.newChannel(fos);
            buff[0] = buffer2;
            buff[1] = buffer1;
            dst.write(buff);

        } catch(IOException e) {
            e.printStackTrace();
        }

    }


    public void FileChannelDeom() {

        String file = "e:\\temp";

        try {
            RandomAccessFile rf = new RandomAccessFile(file, "rw");
            FileChannel fc = rf.getChannel();

            long pos;
            System.out.println("Position = " + (pos = fc.position()));
            System.out.println("size: " + fc.size());

            String msg = "This is a message.";
            ByteBuffer buffer = ByteBuffer.allocateDirect(msg.length() * 4);
            buffer.asCharBuffer().put(msg);

            System.out.println("position:" + buffer.position()
                    + " limit:" + buffer.limit() + " capacity:" + buffer.capacity()
                    + " remaining:" + buffer.remaining());
            buffer.flip();
            System.out.println("position:" + buffer.position()
                    + " limit:" + buffer.limit() + " capacity:" + buffer.capacity()
                    + " remaining:" + buffer.remaining());



            while(buffer.hasRemaining())
                System.out.print(buffer.getChar());
            System.out.println();

            System.out.println("write size = " + fc.write(buffer));
            fc.force(true);

            System.out.println("Position = " + fc.position());
            System.out.println("size: " + fc.size());

            buffer.clear();

            fc.position(pos);
            fc.read(buffer);
            buffer.flip();

            while(buffer.hasRemaining())
                System.out.print(buffer.getChar());

            rf.close();

            (new File(file)).delete();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void Transfer(String file) {

        try(FileInputStream ifs = new FileInputStream(file)) {

            FileChannel fc = ifs.getChannel();
            WritableByteChannel out = Channels.newChannel(System.out);
            fc.transferTo(0, fc.size(), out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
