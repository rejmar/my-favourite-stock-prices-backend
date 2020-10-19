package com.mr.my.favourite.stock.prices.controller.impl.v1;

import com.mr.my.favourite.stock.prices.controller.StocksController;
import com.mr.my.favourite.stock.prices.model.Stock;
import com.mr.my.favourite.stock.prices.service.impl.StocksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/stocks")
public class StocksControllerImpl implements StocksController {
    private final StocksServiceImpl stocksService;

    @Autowired
    public StocksControllerImpl(StocksServiceImpl stocksService) {
        this.stocksService = stocksService;
    }

    @Override
    public ResponseEntity<List<Stock>> getAllStocks() {
        return ResponseEntity.ok(stocksService.getAllStocks());
    }

    @Override
    public ResponseEntity<Stock> getStockByName(String name) {
        return ResponseEntity.ok(stocksService.getStockByName(name));
    }
}
