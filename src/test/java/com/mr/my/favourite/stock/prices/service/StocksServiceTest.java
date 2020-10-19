package com.mr.my.favourite.stock.prices.service;

import com.mr.my.favourite.stock.prices.exception.StockBadRequestException;
import com.mr.my.favourite.stock.prices.exception.StockNotFoundException;
import com.mr.my.favourite.stock.prices.model.Stock;
import com.mr.my.favourite.stock.prices.repository.StocksRepository;
import com.mr.my.favourite.stock.prices.service.impl.StocksServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class StocksServiceTest {
    @Mock
    StocksRepository stocksRepository;

    @InjectMocks
    StocksServiceImpl stocksService;

    @Test
    public void itShouldReturnStockByName() {
        String name = "APPLE";

        Mockito.when(stocksRepository.findByName(anyString())).thenReturn(Optional.of(new Stock()));
        Stock foundStock = stocksService.getStockByName(name);

        assertEquals(new Stock(), foundStock);
    }

    @Test
    public void itShouldThrowAnExceptionWhenFindByNameIsWrong() {
        String name = "apple";

        Mockito.when(stocksRepository.findByName(anyString())).thenThrow(StockNotFoundException.class);

        assertThrows(StockNotFoundException.class, () -> stocksService.getStockByName(name));
    }

    @Test
    public void itShouldThrowAnExceptionWhenFindByNameIsEmpty() {
        String name = "";

        assertThrows(StockBadRequestException.class, () -> stocksService.getStockByName(name));
    }

    @Test
    public void itShouldReturnAllStocksName() {
        List<Stock> stocksNames = Arrays.asList(new Stock(BigInteger.ONE, "TEST_1", BigDecimal.valueOf(1)),
                new Stock(BigInteger.valueOf(2), "TEST_2", BigDecimal.valueOf(2)));

        Mockito.when(stocksRepository.findAll()).thenReturn(stocksNames);
        List<Stock> allFoundedStocks = stocksService.getAllStocks();

        assertEquals(stocksNames, allFoundedStocks);
    }
}