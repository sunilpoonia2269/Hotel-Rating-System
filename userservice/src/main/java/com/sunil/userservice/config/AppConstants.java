package com.sunil.userservice.config;

public class AppConstants {
    private AppConstants() {
    }

    public static final String EMAIL_VALIDATION_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static final String DEFAULT_PAGE_SIZE = "5";
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_SORT_BY = "email";
    public static final String DEFAULT_SORT_DIR = "ASC";
    public static final String DEFAULT_UNPAGED = "false";

}
