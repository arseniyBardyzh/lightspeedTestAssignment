package com.lightspeed.incrementor.exception;

/**
 * Custom exception class for counter Data
 */
public class CounterException extends RuntimeException{

    public static final String COUNTER_ALREADY_EXIST = "Counter with name %s Already Exist";
    public static final String COUNTER_NOT_AVAILABLE = "Counter with name %s is not available";
    public static final String COUNTER_IS_MAXIMUM = "Counter with name %s can not increment, because it has maximum value";
    public static final String SUM_OF_COUNTER_BIGGER_LONG = "Sum of all counters value can not calculate";


    public CounterException() {
        super();
    }

    public CounterException(String message) {
        super(message);
    }

    public CounterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CounterException(Throwable cause) {
        super(cause);
    }

    protected CounterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
