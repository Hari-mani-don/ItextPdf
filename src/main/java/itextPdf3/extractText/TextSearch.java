package itextPdf3.extractText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextSearch {
    public static void main(String[] args) {
        String extractedText = "SearchSearchSearchSearchSearch This is a sample text Search extracted from a PDF document. Let's search for keywords within this text.";

        String keyword = "search";
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(extractedText);

        while (matcher.find()) {
            System.out.println("Found keyword '" + keyword + "' at position: " + matcher.start());
        }
    }
}
/*What You Can Do with These Classes:
 * 
 * 
 * Search for Patterns in Text:
 * 
 * You can find specific words, phrases, or patterns in a string, as your code
 * demonstrates by searching for the keyword "search".
 * 
 * Extract Substrings Based on Patterns:
 * 
 * You can extract substrings that match certain patterns, which is useful for
 * tasks like extracting dates, email addresses, or phone numbers from a text.
 * 
 * Find Multiple Occurrences:
 * 
 * The Matcher.find() method allows you to locate multiple matches of the
 * pattern within the input string.
 * 
 * Validate Strings:
 * 
 * You can use regular expressions to validate input, like checking if a string
 * is a valid email address, phone number, etc.
 * 
 * Replace Text Using Patterns:
 * 
 * You can search for a pattern in a string and replace it with another value
 * using the replaceAll or replaceFirst methods.
 * 
 * Advanced Pattern Matching:
 * 
 * You can use complex regular expressions with options like grouping,
 * alternations, and quantifiers.
 */
