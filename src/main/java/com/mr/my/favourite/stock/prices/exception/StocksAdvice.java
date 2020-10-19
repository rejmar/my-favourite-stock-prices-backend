package com.mr.my.favourite.stock.prices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StocksAdvice {

    @ResponseBody
    @ExceptionHandler(StockNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String stockNotFoundHandler(StockNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(StockBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String stockBadRequestHandler(StockBadRequestException ex) {
        return ex.getMessage();
    }
}