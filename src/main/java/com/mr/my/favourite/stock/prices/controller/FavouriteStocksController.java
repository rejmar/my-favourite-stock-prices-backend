package com.mr.my.favourite.stock.prices.controller;

import com.mr.my.favourite.stock.prices.model.FavouriteStock;
import com.mr.my.favourite.stock.prices.model.Stock;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Favourite stocks endpoints", description = "Favourite stocks endpoints")
public interface FavouriteStocksController {
    @Operation(summary = "Fetch all favourite stocks")
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<FavouriteStock>> getAllFavStocks();

    @Operation(summary = "Add stock to favourites")
    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FavouriteStock> addNewFavStock(
            @Parameter(description = "Stock to add to favourites", required = true)
            @RequestBody Stock stock
    );

    @Operation(summary = "Delete stock from favourites")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FavouriteStock> deleteFavStock(
            @Parameter(name = "id", description = "Stock id", required = true)
            @PathVariable(name = "id") String id
    );
}
