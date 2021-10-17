package org.anitab.mentorship.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a\u0016\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001\u001a\u0016\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0001\u001a\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"DATE_FORMAT", "", "EXTENDED_DATE_FORMAT", "MILLISECONDS_FACTOR", "", "SEND_REQUEST_END_DATE_FORMAT", "convertDateIntoUnixTimestamp", "dateStr", "format", "convertUnixTimestampIntoStr", "unixTimestamp", "", "getUnixTimestampInMilliseconds", "app_debug"})
public final class DateAndTimeUtilsKt {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DATE_FORMAT = "dd MMM yyyy";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SEND_REQUEST_END_DATE_FORMAT = "dd/MM/yyyy";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTENDED_DATE_FORMAT = "dd MMMM yyyy";
    public static final long MILLISECONDS_FACTOR = 1000L;
    
    /**
     * Get [Date] from [unixTimestamp] in [String] format
     * @param unixTimestamp time in Unix timestamp format, in seconds
     * @param format date string format to use
     * @return date in string format
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String convertUnixTimestampIntoStr(float unixTimestamp, @org.jetbrains.annotations.NotNull()
    java.lang.String format) {
        return null;
    }
    
    /**
     * Convert Unix timestamp seconds to milliseconds [Long] format
     * @param unixTimestamp time in Unix timestamp format, in seconds
     * @return unix timestamp in milliseconds
     */
    public static final long getUnixTimestampInMilliseconds(float unixTimestamp) {
        return 0L;
    }
    
    /**
     * Convert Date to UnixTimestamp [Long] format
     * @param dateStr date in string format
     * @param format string format used to parse dateStr
     * @return unix timestamp in milliseconds
     */
    public static final long convertDateIntoUnixTimestamp(@org.jetbrains.annotations.NotNull()
    java.lang.String dateStr, @org.jetbrains.annotations.NotNull()
    java.lang.String format) {
        return 0L;
    }
}