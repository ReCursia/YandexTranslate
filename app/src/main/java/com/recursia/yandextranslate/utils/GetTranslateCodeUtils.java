package com.recursia.yandextranslate.utils;

public class GetTranslateCodeUtils {

    public static String getCode(String from, String to) {
        return getIsoCode(from) + "-" + getIsoCode(to);
    }

    private static String getIsoCode(String lang) throws IllegalArgumentException {
        switch (lang) {
            case "Russian":
                return "ru";
            case "English":
                return "en";
            case "Ukrainian":
                return "uk";
            default:
                throw new IllegalArgumentException();
        }
    }

}
