package com.team4.bookreview.util;

public class Util {
    private Util() {
        throw new IllegalStateException("Utility class");
    }
    public static String toSearchString(String s) {
        s = s.replace(' ', '%');
		s = "%" + s + "%";
		return s;
    }
}