package com.team4.bookreview.util;

public class ErrorMsg {
    public static final String ERROR_STRING = "Error";
    public static final String ERROR_DATA_NOT_SATISFIED = "Data Not Satisfied";
    public static final String ERROR_DB_UPDATE = "DB Update Error";
    public static final String ERROR_DB_DELETION = "DB Deletion error";
    public static final String ERROR_DB_INSERTION = "DB Insertion Error";
    public static final String ERROR_DB_SELECTION = "DB Selection Error";
    public static final String ERROR_INTERNAL = "Internal Error";
    public static final String ERROR_RETURN_VALUE_NOT_0_1 = "Return Value is NOT 0 or 1";
    public static final String ERROR_UNKNOWN = "Unknown Error";

    

    private ErrorMsg() {
        throw new IllegalStateException("Error class");
    }
}