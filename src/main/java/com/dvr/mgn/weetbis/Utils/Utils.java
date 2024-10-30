package com.dvr.mgn.weetbis.Utils;

public class Utils {
    public static String toTitleMode(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String firstLetter = str.substring(0, 1).toUpperCase();
        String restLetters = str.substring(1).toLowerCase();
        String result = firstLetter + restLetters;
        return result.trim();
    }
}
