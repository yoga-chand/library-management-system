package com.library.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParserUtil {

    public static List<String> parseInput(String input) {
        String regex = "\"([^\"]*)\"|(\\S+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        List<String> result = new ArrayList<>();

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                result.add(matcher.group(1));
            } else if (matcher.group(2) != null) {
                result.add(matcher.group(2));
            }
        }
        return result;
    }
}
