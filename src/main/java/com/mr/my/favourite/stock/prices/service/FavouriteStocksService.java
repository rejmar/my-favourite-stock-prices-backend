package com.mr.my.favourite.stock.prices.service;

import com.mr.my.favourite.stock.prices.model.FavouriteStock;
import com.mr.my.favourite.stock.prices.model.Stock;

import java.util.List;

public interface FavouriteStocksService {
    List<FavouriteStock> getAllFavouriteStocks();

    FavouriteStock addStockToFavourites(Stock stock);

    FavouriteStock deleteFavouriteStock(String id);
}


