package C7_Selection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

/**
 * This is a selection demo
 * Created by sayarasin on 08/10/2016.
 */
public class Selection {

    private final static int DEFAULT_PORT = 9999;

    private static ByteBuffer bb = ByteBuffer.allocate(8);


    public void Server(int port) throws IOException {

        if(port == 0)
            port = DEFAULT_PORT;

        System.out.println("Server starting ... listening on port " + port);

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ServerSocket ss = ssc.socket();
        ss.bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);

        Selector s = Selector.open();
        ssc.register(s, SelectionKey.OP_ACCEPT);

        while(true) {
            int n = s.select();
            if(n == 0)
                continue;

            Iterator it = s.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if(key.isAcceptable())
                {
                    SocketChannel sc;
                    sc = ((ServerSocketChannel) key.channel()).accept();
                    if(sc == null)
                        continue;

                    System.out.println("Receiving connection");
                    bb.clear();
                    bb.putLong(System.currentTimeMillis());
                    bb.flip();
                    System.out.println("Writing current time");
                    while(bb.hasRemaining())
                        sc.write(bb);
                    sc.close();
                }
                it.remove();
            }
        }
    }


    public void Client(int port) {

        if(port == 0)
            port = DEFAULT_PORT;

        try {

            SocketChannel sc = SocketChannel.open();
            InetSocketAddress addr = new InetSocketAddress("localhost", port);
            sc.connect(addr);

            long time = 0;
            while(sc.read(bb) != -1) {
                bb.flip();
                /*
                while(bb.remaining() > 0) {
                    time <<= 8;
                    time |= bb.get() & 255;
                }
                */
                time = bb.getLong();
                bb.clear();
            }
            System.out.println(new Date(time));
            sc.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}
