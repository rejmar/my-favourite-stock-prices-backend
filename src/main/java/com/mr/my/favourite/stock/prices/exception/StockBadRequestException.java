package com.mr.my.favourite.stock.prices.exception;

public class StockBadRequestException extends RuntimeException {
    public StockBadRequestException(String message) {
        super(message);
    }
}
