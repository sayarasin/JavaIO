package C8_RegularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * This is a regular expression demo
 * Created by sayarasin on 08/10/2016.
 */
public class RegEx {

    public void Demo(String pattern, String string) {

        System.out.println("Regex = " + pattern);
        System.out.println("Input = " + string);

        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(string);
            while (m.find())
                System.out.println("Located [" + m.group() + "] starting at "
                        + m.start() + " and ending at "
                        + ((m.start() == m.end()) ? m.end() : (m.end() - 1)));
        } catch(PatternSyntaxException e) {
            System.err.println("Bad regex: " + e.getMessage());
            System.err.println("Description: " + e.getDescription());
            System.err.println("Index: " + e.getIndex());
            System.err.println("Incorrect pattern: " + e.getPattern());
        }
    }
}
