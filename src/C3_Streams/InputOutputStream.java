package C3_Streams;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by sayarasin on 07/23/2016.
 */
public class InputOutputStream {

    final static int LIMIT = 10;


    public void PipedStream() {

        try (
                final PipedOutputStream po = new PipedOutputStream();
                final PipedInputStream  pi = new PipedInputStream(po)
             ){

            Runnable senderTask = ()-> {
                try{
                    for(int i = 0 ; i < LIMIT ; ++i) {
                        po.write((byte)(Math.random() * 256));
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        po.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            Runnable recverTask = ()-> {
                try {
                    int b;
                    while((b = pi.read())!= -1)
                        System.out.println("received:" + b);
                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        pi.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            new Thread(recverTask).start();
            new Thread(senderTask).start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
