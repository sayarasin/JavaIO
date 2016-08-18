package C6_NIOChannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * This is a piped channel demo
 * Created by sayarasin on 08/09/2016.
 */
public class NIOPipedChannel {

    private final static int BUFSIZE = 10;
    private final static int LIMIT = 3;


    public void PipeDemo() throws IOException {

        final Pipe pipe = Pipe.open();

        Runnable sendTask =
                new Runnable() {

                    @Override
                    public void run() {

                        WritableByteChannel src = pipe.sink();
                        ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);

                        for(int i = 0 ; i < LIMIT ; ++i) {
                            buffer.clear();
                            for(int j = 0 ; j < BUFSIZE ; ++j)
                                buffer.put((byte)(Math.random() * 256));
                            buffer.flip();

                            try {
                                while(src.write(buffer) > 0);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            src.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

        Runnable receiverTask =
                new Runnable() {
                    @Override
                    public void run() {
                        ReadableByteChannel dst = pipe.source();
                        ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
                        try {
                            try {
                                while (dst.read(buffer) > 0) {
                                    buffer.flip();
                                    while (buffer.remaining() > 0) {
                                        System.out.format("%03d", buffer.get() & 255);
                                        System.out.print("|");
                                    }
                                    buffer.clear();
                                    System.out.println();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } finally {
                            try {
                                dst.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };

        Thread sender = new Thread(sendTask);
        Thread receiver = new Thread(receiverTask);
        sender.start();
        receiver.start();
    }
}
