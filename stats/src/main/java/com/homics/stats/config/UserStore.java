package com.homics.stats.config;

public class UserStore {
    private static final ThreadLocal<String> USER_CONTEXT = new ThreadLocal<>();

    private UserStore() {
    }

    public static void setUserName(String userName) {
        USER_CONTEXT.set(userName);
    }

    public static String getUserName() {
        return USER_CONTEXT.get();
    }

    public static void clear() {
        USER_CONTEXT.remove();
    }
}
