package C4_WriterAndReader;

import java.io.*;


/**
 * This is a demo for Writer and Reader Class
 * Created by sayarasin on 08/01/2016.
 */
public class WriterAndReader {

    private static final String TEST_WRITER_FILE = "E:/Writer.dat";
    private static final String TEST_NON_WRITER_FILE = "E:/NoneWriter.dat";
    private static final String TEST_NON_WRITER_FILE_NO_BUFFERED = "E:/NoneWriterNoneBuffered.dat";
    private static final int    TIMES = 10000000;
    private static final String TEST_STRING = "what's amazing ";

    private FileOutputStream fs = null;
    private BufferedOutputStream bs = null;

    /**
     * Timestamp:20160801195718864 at Start
     * Timestamp:20160801195751710 at end
     * Non buffered: about 32846
     * 1M buffered: about 8285
     * 4M buffered: about 6347
     * */
    public void WriteForNonWriter() {

        File targetFile = new File(TEST_NON_WRITER_FILE);
        if(targetFile.exists())
            targetFile.delete();

        try {
            fs = new FileOutputStream(targetFile);
            bs = new BufferedOutputStream(fs, 4 * 1024 * 1024);

            System.out.println(Common.Common.getTimeStamp(" at Start"));

            for (int i = 0; i < TIMES; ++i) {
                String s = TEST_STRING;
                bs.write(s.getBytes("utf-8"));
            }

            System.out.println(Common.Common.getTimeStamp(" at end"));

        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Timestamp:20160801195751712 at Start
     * Timestamp:20160801195820662 at end
     * Non buffered: about 68950
     * 1M buffered: about 6808
     * 4M buffered: about 5995
     */
    public void WriteForWriter() {
        File targetFile = new File(TEST_WRITER_FILE);
        if(targetFile.exists())
            targetFile.delete();

        try {
            fs = new FileOutputStream(targetFile);
            bs = new BufferedOutputStream(fs, 4 * 1024 * 1024);
            OutputStreamWriter ot = new OutputStreamWriter(bs, "utf-8");

            System.out.println(Common.Common.getTimeStamp(" at Start"));

            for (int i = 0; i < TIMES; ++i) {
                String s = TEST_STRING;
                ot.write(s);
            }

            System.out.println(Common.Common.getTimeStamp(" at end"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Timestamp:20160801201601851 at Start
     * Timestamp:20160801201616631 at end
     * about 14780
     */
    public void WriteForNonWriterAndNonBuffer() {

        File targetFile = new File(TEST_NON_WRITER_FILE_NO_BUFFERED);
        if(targetFile.exists())
            targetFile.delete();

        try {
            fs = new FileOutputStream(targetFile);

            System.out.println(Common.Common.getTimeStamp(" at Start"));

            for (int i = 0; i < TIMES; ++i) {
                String s = TEST_STRING;
                fs.write(s.getBytes("utf-8"));
            }

            System.out.println(Common.Common.getTimeStamp(" at end"));

        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
