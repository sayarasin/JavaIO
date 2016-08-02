import C4_WriterAndReader.WriterAndReader;

/**
 * Created by sayarasin on 07/17/2016.
 */

public class Main {

    public static void main(String [] args) {

        WriterAndReader wr = new WriterAndReader();
        wr.WriteForNonWriter();
        wr.WriteForWriter();
        wr.WriteForNonWriterAndNonBuffer();
    }
}
