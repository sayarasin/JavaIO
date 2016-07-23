package C3_Streams;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by sayarasin on 07/23/2016.
 */
public class ScrambledOutputStream extends FilterOutputStream {

    private int [] map;

    /**
     * Creates an output stream filter built on top of the specified
     * underlying output stream.
     *
     * @param out the underlying output stream to be assigned to
     *            the field <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */
    public ScrambledOutputStream(OutputStream out, int [] map) {
        super(out);
        if(map == null)
            throw new NullPointerException("map is null");
        if(map.length != 256)
            throw new IllegalArgumentException("map length != 256");
        this.map = map;
    }


    /**
     * Scrambled the character
     * @param b
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
        super.write(map[b]);
    }
}
