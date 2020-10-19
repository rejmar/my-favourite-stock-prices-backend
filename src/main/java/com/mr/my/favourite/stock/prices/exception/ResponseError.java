package com.mr.my.favourite.stock.prices.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class ResponseError {
    private int status;
    private String message;
    private OffsetDateTime timestamp;
}
