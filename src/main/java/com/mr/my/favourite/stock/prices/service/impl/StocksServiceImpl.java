package com.mr.my.favourite.stock.prices.service.impl;

import com.mr.my.favourite.stock.prices.exception.StockBadRequestException;
import com.mr.my.favourite.stock.prices.exception.StockNotFoundException;
import com.mr.my.favourite.stock.prices.model.Stock;
import com.mr.my.favourite.stock.prices.repository.StocksRepository;
import com.mr.my.favourite.stock.prices.service.StocksService;
import com.mr.my.favourite.stock.prices.utils.RandomGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class StocksServiceImpl implements StocksService {
    private final StocksRepository stocksRepository;

    @Autowired
    public StocksServiceImpl(StocksRepository stocksRepository) {
        this.stocksRepository = stocksRepository;
    }

    @Override
    public List<Stock> getAllStocks() {
        stocksRepository.findAll().forEach(stock ->
        {
            stock.setPrice(RandomGenerator
                    .generateStockPrice(1, 1500, 1));
            stocksRepository.save(stock);
        });
        return stocksRepository.findAll();
    }

    @Override
    public Stock getStockByName(String name) {
        if (!name.isEmpty()) {
            Optional<Stock> foundedStock = stocksRepository.findByName(name.toUpperCase());
            return foundedStock.orElseThrow(() -> new StockNotFoundException(name + " not found "));
        } else {
            log.info("Empty name provided!");
            throw new StockBadRequestException("Empty name provided!");
        }
    }
}
