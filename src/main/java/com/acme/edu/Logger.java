package com.acme.edu;

public class Logger {
    public static void log(int message) {
        PrintStr("primitive: " + message);
    }

    public static void log(byte message) {
        PrintStr("primitive: " + message);
    }

    public static void log(char message) {
        PrintStr("char: " + message);
    }

    public static void log(boolean message) {
        PrintStr("primitive: " + message);
    }

    public static void PrintStr(String s) {
        System.out.println(s);
    }
}
