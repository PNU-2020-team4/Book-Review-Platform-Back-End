package com.team4.bookreview.util;

public class Util {
    public static String toSearchString(String s) {
        s = s.replace(' ', '%');
		s = "%" + s + "%";
		return s;
    }
}