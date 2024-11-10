package com.library.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParserUtil {

    public static List<String> parseInput(String input) {
        String regex = "\"([^\"]*)\"|(\\S+)";

        // Create a pattern from the regex
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // List to hold the parts
        List<String> result = new ArrayList<>();

        // Find all matches
        while (matcher.find()) {
            // Add the matched group (either a quoted string or a non-quoted word)
            if (matcher.group(1) != null) {
                // Add the quoted string without quotes
                result.add(matcher.group(1));
            } else if (matcher.group(2) != null) {
                // Add the regular word
                result.add(matcher.group(2));
            }
        }
        return result;
    }
}
