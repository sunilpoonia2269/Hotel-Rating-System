package com.sunil.ratingservice.config;

public class AppConstants {

    private AppConstants() {
        throw new IllegalStateException("AppConstants class");
    }

    public static final String PAGE_SIZE = "1";
    public static final String PAGE_NUMBER = "0";

    public static final String SORT_BY = "createdAt";
    public static final String SORT_DIR = "DESC";

    /// Error response
    public static final String DUPLICATE_ERROR = "Rating is already created for this userId and hotelId";

    public static final String DEFAULT_UNPAGED = "false";

}
