FAANG/MAANG Company

String Substitution | Java 

QUESTION :
Write a library which supports substitutions of string by string variables.

Example 1:

“X” -> “123”
“Y” -> “456”
“%X%_%Y%” should be resolved to “123_456”

Example 2:

“USER” -> "root"
“HOME” -> "/usr/local/home/%USER%"
“DATE” -> "2020-09-16"
"%HOME%/data/file_%DATE%.txt" -> "/usr/local/home/root/data/file_2020-09-16.txt"

SOLUTION: 

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSubstitutor {
    private Map<String, String> substitutions;

    public StringSubstitutor(Map<String, String> substitutions) {
        this.substitutions = new HashMap<>(substitutions);
    }

    /**
     * Resolves the string with substitutions using a single pass and recursion for nested keys.
     * This method reduces the redundant scanning of the string for each key.
     *
     * @param str The input string that may contain placeholders for substitution.
     * @return The resolved string after all substitutions.
     */
    public String resolveString(String str) {
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("%([^%]+)%");
        Matcher matcher = pattern.matcher(str);

        int lastEnd = 0;
        while (matcher.find()) {
            result.append(str.substring(lastEnd, matcher.start()));  // Append the part before the match
            String key = matcher.group(1);
            String replacement = substitutions.get(key);
            if (replacement == null) {
                // If no substitution found, append the placeholder as is
                result.append(matcher.group());
            } else {
                // Recursive call to resolve nested substitutions
                result.append(resolveString(replacement));
            }
            lastEnd = matcher.end();
        }
        result.append(str.substring(lastEnd));  // Append the remaining part of the string

        return result.toString();
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("X", "123");
        map.put("Y", "456");
        map.put("USER", "root");
        map.put("HOME", "/usr/local/home/%USER%");
        map.put("DATE", "2020-09-16");

        StringSubstitutor substitutor = new StringSubstitutor(map);
        System.out.println(substitutor.resolveString("%X%_%Y%")); // Outputs "123_456"
        System.out.println(substitutor.resolveString("%HOME%/data/file_%DATE%.txt")); // Outputs "/usr/local/home/root/data/file_2020-09-16.txt"
    }
}

/*
1]   Pattern pattern = Pattern.compile("%([^%]+)%");
Here groups are separated by parathenesis (), here group(1)  corresponds to the placeholder name without the percentage signs.
For instance, if the match is %X%, matcher.group(1) will return X.  
matcher.group(1) specifically returns the content captured by the first capturing group in the pattern, grpups are seperated by parathenesis (),
here group(1) which corresponds to the placeholder name without the percentage signs. 

For instance, if the match is %USER%, matcher.group(1) will return USER.

*/
