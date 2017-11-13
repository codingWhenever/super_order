package com.e_at.eatlibrary.utils;


import java.util.regex.Pattern;

public class RegexUtil {
    private static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";

    public static boolean isMobileSimple(CharSequence input) {
        return isMatch(REGEX_MOBILE_SIMPLE, input);
    }

    private static boolean isMatch(String regex, CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }
}
