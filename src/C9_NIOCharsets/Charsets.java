package C9_NIOCharsets;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * This is a charsets demo
 * Created by sayarasin on 08/11/2016.
 */
public class Charsets {


    public void ShowCharDiff() {

        String msg = "façade touché";
        String [] csNames = {
                "US-ASCII",
                "ISO-8859-1",
                "UTF-8",
                "UTF-16BE",
                "UTF-16LE",
                "UTF-16"
        };

        encode(msg, Charset.defaultCharset());
        for(String strCS : csNames) {
            encode(msg, Charset.forName(strCS));
        }
    }


    private void encode(String msg, Charset cs) {

        System.out.println("Charset: " + cs.toString());
        System.out.println("Message: " + msg);

        ByteBuffer buffer = cs.encode(msg);
        System.out.print("Encoded: ");

        for(int i = 0 ; buffer.hasRemaining() ; ++i) {
            int _byte = buffer.get() & 255;
            char ch   = (char)_byte;
            if(Character.isWhitespace(ch) || Character.isISOControl(ch))
                ch = '\u0000';
            System.out.printf("%02x (%c)|", _byte, ch);
        }

        System.out.println();
    }


    public void ToStringDemo() {
        byte [] encodeMsg = {
                0x66, 0x61, (byte) 0xc3, (byte) 0xa7, 0x61, 0x64, 0x65, 0x20, 0x74, 0x6f,
                0x75, 0x63, 0x68, (byte) 0xc3, (byte) 0xa9
        };
        try {
            String s = new String(encodeMsg, "UTF-8");
            System.out.println(s);
            System.out.println();
            byte [] bytes = s.getBytes("UTF-16");
            for (byte _byte: bytes)
                System.out.print(Integer.toHexString(_byte & 255) + " ");
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
