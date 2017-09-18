package com.contractor.util;

import java.sql.Timestamp;

public class Utils {

    public static Timestamp timestampFromMillis() {
        return Timestamp.valueOf(String.valueOf(System.currentTimeMillis()));
    }

    public static boolean compareTimestamp(Timestamp compare, Timestamp reference) {
        return compare.before(reference) || compare.equals(reference);
    }
}
