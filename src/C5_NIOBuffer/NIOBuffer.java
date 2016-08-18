package C5_NIOBuffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.channels.Channel;

/**
 * This is a demo of NIO-Buffer
 * Created by sayarasin on 08/02/2016.
 */
public class NIOBuffer {


    private void printArray(ByteBuffer bb) {
        if(bb.hasArray()) {
            System.out.println("Buffer array: " + bb.array());
            System.out.println("Buffer array offset: " +
                    bb.arrayOffset());
            System.out.println("Capacity: " + bb.capacity());
            System.out.println("Limit: " + bb.limit());
            System.out.println("Position: " + bb.position());
            System.out.println("Remaining: " + bb.remaining());
            System.out.println("toString: " + bb.toString());
            for(int i = 0 ; i < bb.limit() ; ++i) {
                System.out.print(Byte.toString(bb.get(i)) + "|");
            }
            System.out.println();
        }
    }

    public void allocalDemo() {

        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        if(buffer1.hasArray()) {
            printArray(buffer1);
        }
        buffer1.put(7, Byte.valueOf("1"));
        ByteBuffer bufferView = buffer1.duplicate();
        bufferView.put(Byte.valueOf("2"));
        bufferView.put(0, Byte.valueOf("3"));
        if(buffer1.hasArray()) {
            printArray(buffer1);
        }
        if(bufferView.hasArray()) {
            printArray(bufferView);
        }

        byte [] bytes = new byte[20];
        ByteBuffer buffer2 = ByteBuffer.wrap(bytes, 1, 5);
        if(buffer2.hasArray()) {
            printArray(buffer2);
        }
    }


    public void poemDemo() {

        String [] poem = {
          "Roses are red", "Violets are blue", "Sugar is sweet", "And so are you."
        };

        CharBuffer buffer = CharBuffer.allocate(50);

        for(int i = 0 ; i < poem.length ; ++i) {

            // Fill the buffer all poem sentence
            for(int j = 0 ; j < poem[i].length() ; ++j)
                buffer.put(poem[i].charAt(j));

            // flip the buffer so that its contents can be read.
            buffer.flip();

            while(buffer.hasRemaining())
                System.out.print(buffer.get());

            buffer.clear();

            System.out.println();
        }
    }


    public void markDemo() {
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.put((byte)10).put((byte)20).put((byte)30).put((byte)40);
        buffer.limit(4);
        while(buffer.hasRemaining())
            System.out.print(buffer.get());
        System.out.println();
        buffer.position(1).mark().position(3);
        while(buffer.hasRemaining())
            System.out.print(buffer.get());
        System.out.println();
        buffer.reset();
        while(buffer.hasRemaining())
            System.out.print(buffer.get());
        System.out.println();
    }

    public void byteOrderDemo() {
        System.out.println(ByteOrder.nativeOrder());
    }
}
