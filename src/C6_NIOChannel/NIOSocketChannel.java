package C6_NIOChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * This is a socket channel demo
 * Created by sayarasin on 08/08/2016.
 */
public class NIOSocketChannel {

    public void ServerDemo() throws IOException {

        System.out.println("Starting server....");

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(9999));
        ssc.configureBlocking(false);// setting nonblocking mode

        String msg = "Local address: " + ssc.socket().getLocalSocketAddress();

        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());

        while(true) {
            System.out.print(".");
            SocketChannel sc = ssc.accept();
            if(sc != null) {

                System.out.println();
                System.out.println("Received connection from "
                    + sc.socket().getRemoteSocketAddress());
                buffer.rewind();
                sc.write(buffer);
                sc.close();

            } else {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void ClientDemo() {

        try {
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false); // set to non-blocking mode
            InetSocketAddress addr = new InetSocketAddress("localhost", 9999);
            sc.connect(addr);

            while(!sc.finishConnect()) {
                System.out.println("Waiting to finish connection");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ByteBuffer buffer = ByteBuffer.allocate(200);
            while(sc.read(buffer) >= 0)
            {
                buffer.flip();
                while(buffer.hasRemaining())
                    System.out.print((char) buffer.get());
                buffer.clear();
            }

            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private final static int PORT = 9999;

    public void DatagramServer() {

        System.out.println("Server starting and listening on port " +
                PORT + " for incomning requests ...");

        try {
            DatagramChannel dcServer = DatagramChannel.open();
            dcServer.socket().bind(new InetSocketAddress(PORT));

            ByteBuffer symbol = ByteBuffer.allocate(4);
            ByteBuffer payload = ByteBuffer.allocate(16);

            while (true) {
                symbol.clear();
                payload.clear();

                SocketAddress sa = dcServer.receive(symbol);
                if (sa == null)
                    return;

                System.out.println("Received request from " + sa);
                String stockSymbol = new String(symbol.array(), 0, 4);
                System.out.println("Symbol: " + stockSymbol);

                if (stockSymbol.toUpperCase().equals("MSFT")) {
                    payload.putFloat(0, 37.40f);
                    payload.putFloat(4, 37.22f);
                    payload.putFloat(8, 37.48f);
                    payload.putFloat(12, 37.41f);
                } else {
                    payload.putFloat(0, 0f);
                    payload.putFloat(4, 0f);
                    payload.putFloat(8, 0f);
                    payload.putFloat(12, 0f);
                }
                dcServer.send(payload, sa);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DatagramClient(String strStockName) {

        try {
            DatagramChannel dcClient = DatagramChannel.open();

            ByteBuffer symbol = ByteBuffer.wrap(strStockName.getBytes());
            ByteBuffer response = ByteBuffer.allocate(16);

            InetSocketAddress sa = new InetSocketAddress("localhost", PORT);
            dcClient.send(symbol, sa);

            System.out.print("Receiving datagram from " +
                dcClient.receive(response));
            System.out.println("Open  price: " + response.getFloat(0));
            System.out.println("Low   price: " + response.getFloat(4));
            System.out.println("High  price: " + response.getFloat(8));
            System.out.println("Close price: " + response.getFloat(12));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
