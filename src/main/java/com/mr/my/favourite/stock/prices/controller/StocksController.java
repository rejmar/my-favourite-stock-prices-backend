package com.mr.my.favourite.stock.prices.controller;

import com.mr.my.favourite.stock.prices.model.Stock;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Stocks endpoints", description = "Stocks endpoints")
public interface StocksController {
    @Operation(summary = "Fetch all available stocks")
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Stock>> getAllStocks();

    @Operation(summary = "Get stock by name")
    @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Stock> getStockByName(
            @Parameter(name = "name", description = "Stock's name", required = true)
            @PathVariable(name = "name")
                    String name
    );
}
