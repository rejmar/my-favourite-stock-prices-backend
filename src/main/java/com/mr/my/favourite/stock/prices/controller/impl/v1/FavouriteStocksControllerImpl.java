package com.mr.my.favourite.stock.prices.controller.impl.v1;

import com.mr.my.favourite.stock.prices.controller.FavouriteStocksController;
import com.mr.my.favourite.stock.prices.model.FavouriteStock;
import com.mr.my.favourite.stock.prices.model.Stock;
import com.mr.my.favourite.stock.prices.service.FavouriteStocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/fav-stocks")
public class FavouriteStocksControllerImpl implements FavouriteStocksController {
    private final FavouriteStocksService favouriteStocksService;

    @Autowired
    public FavouriteStocksControllerImpl(FavouriteStocksService favouriteStocksService) {
        this.favouriteStocksService = favouriteStocksService;
    }

    @Override
    public ResponseEntity<List<FavouriteStock>> getAllFavStocks() {
        return ResponseEntity.ok(favouriteStocksService.getAllFavouriteStocks());
    }

    @Override
    public ResponseEntity<FavouriteStock> addNewFavStock(Stock stock) {
        return ResponseEntity.ok(favouriteStocksService.addStockToFavourites(stock));
    }

    @Override
    public ResponseEntity<FavouriteStock> deleteFavStock(String id) {
        return ResponseEntity.ok(favouriteStocksService.deleteFavouriteStock(id));
    }
}

