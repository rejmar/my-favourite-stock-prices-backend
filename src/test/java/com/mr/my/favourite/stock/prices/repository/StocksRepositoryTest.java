package com.mr.my.favourite.stock.prices.repository;

import com.mr.my.favourite.stock.prices.model.Stock;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class StocksRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    StocksRepository stocksRepository;

    @Test
    public void itShouldFindAllDefaultStocks() {
        List<String> stocksNames = Arrays.asList("APPLE", "MICROSOFT", "IG GROUP", "TESLA", "GENERAL MOTORS");

        List<String> foundStocksNames = stocksRepository.findAll().stream().map(Stock::getName).collect(Collectors.toList());

        assertEquals(stocksNames, foundStocksNames);
    }

    @Test
    public void itShouldSaveNewStock() {
        Stock newStock = new Stock();
        newStock.setId(BigInteger.ONE);
        newStock.setName("TEST");
        newStock.setPrice(BigDecimal.valueOf(100));

        stocksRepository.save(newStock);

        assertEquals(newStock, stocksRepository.findById(BigInteger.valueOf(1)).get());
    }

    @Test
    public void itShouldDeleteFirstStock() {
        List<String> stocksNames = Arrays.asList("MICROSOFT", "IG GROUP", "TESLA", "GENERAL MOTORS");

        stocksRepository.deleteById(BigInteger.ONE);
        List<String> foundStocksNames = stocksRepository.findAll().stream().map(Stock::getName).collect(Collectors.toList());

        assertEquals(stocksNames, foundStocksNames);
    }
}