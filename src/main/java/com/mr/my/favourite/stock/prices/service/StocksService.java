package com.mr.my.favourite.stock.prices.service;

import com.mr.my.favourite.stock.prices.model.Stock;

import java.util.List;

public interface StocksService {
    List<Stock> getAllStocks();

    Stock getStockByName(String name);
}
