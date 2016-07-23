package C3_Streams;

import java.io.*;
import java.util.Random;

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


    private int [] MakeMap() {
        int [] map = new int [ScrambledOutputStream.MAP_LENGTH];

        // initialize map to ordered number
        for(int i = 0 ; i < map.length ; ++i)
            map[i] = i;

        // Shuffle map
        Random r = new Random();
        for(int i = 0 ; i < map.length ; ++i) {
            int n = r.nextInt(map.length);
            int t = map[i];
            map[i]= map[n];
            map[n]= t;
        }

        return map;
    }


    public void FilterStream(String inputFile, String outputFile) {

        FileInputStream fis = null;
        ScrambledOutputStream sos = null;

        try {
            fis = new FileInputStream(inputFile);
            FileOutputStream fos = new FileOutputStream(outputFile);
            sos = new ScrambledOutputStream(fos, MakeMap());

            int b;
            while((b = fis.read()) != -1)
                sos.write(b);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(sos != null) {
                try {
                    sos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
