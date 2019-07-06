package com.yolohealth.yolokioskapp.utils;

import android.util.Log;

import com.yolohealth.yolokioskapp.platform.Platform;



public class Logger {
    
    public static void i(String tag, String message) {
        if (Platform.getInstance().isLoggingEnabled()) {
            Log.i(tag, message);
        }
    }

    public static void i(String tag, String message, Throwable tr) {
        if (Platform.getInstance().isLoggingEnabled()) {
            Log.i(tag, message, tr);
        }
    }

    public static void d(String tag, String message) {
        if (Platform.getInstance().isLoggingEnabled()) {
            Log.d(tag, message);
        }
    }

    public static void d(String tag, String message, Throwable tr) {
        if (Platform.getInstance().isLoggingEnabled()) {
            Log.d(tag, message, tr);
        }
    }

    public static void e(String tag, String message) {
        if (Platform.getInstance().isLoggingEnabled()) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Throwable tr) {
        if (Platform.getInstance().isLoggingEnabled()) {
            Log.e(tag, message, tr);
        }
    }

    public static void v(String tag, String message) {
        if (Platform.getInstance().isLoggingEnabled()) {
            Log.v(tag, message);
        }
    }

    public static void v(String tag, String message, Throwable tr) {
        if (Platform.getInstance().isLoggingEnabled()) {
            Log.v(tag, message, tr);
        }
    }

    public static void w(String tag, String message) {
        if (Platform.getInstance().isLoggingEnabled()) {
            Log.w(tag, message);
        }
    }

    public static void w(String tag, String message, Throwable tr) {
        if (Platform.getInstance().isLoggingEnabled()) {
            Log.w(tag, message, tr);
        }
    }
}
