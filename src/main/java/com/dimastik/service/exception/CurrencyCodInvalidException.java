package com.dimastik.service.exception;

public class CurrencyCodInvalidException extends RuntimeException {

    public CurrencyCodInvalidException(String message) {
        super(message);
    }

    public CurrencyCodInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyCodInvalidException(Throwable cause) {
        super(cause);
    }

    protected CurrencyCodInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
