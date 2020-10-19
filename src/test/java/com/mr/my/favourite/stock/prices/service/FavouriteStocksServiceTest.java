package com.mr.my.favourite.stock.prices.service;

import com.mr.my.favourite.stock.prices.exception.StockBadRequestException;
import com.mr.my.favourite.stock.prices.model.FavouriteStock;
import com.mr.my.favourite.stock.prices.model.Stock;
import com.mr.my.favourite.stock.prices.repository.FavouriteStocksRepository;
import com.mr.my.favourite.stock.prices.service.impl.FavouriteStocksServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FavouriteStocksServiceTest {
    private final OffsetDateTime mockedOffset = OffsetDateTime.of(LocalDate.parse("2020-01-01"), LocalTime.parse("01:00:00"), ZoneOffset.UTC);
    @Mock
    FavouriteStocksRepository favouriteStocksRepository;
    @InjectMocks
    FavouriteStocksServiceImpl favouriteStocksService;

    @Test
    public void itShouldReturnAllFavouriteStocksName() {
        List<FavouriteStock> stocksNames = Arrays.asList(new FavouriteStock(BigInteger.ONE, BigDecimal.valueOf(1), mockedOffset),
                new FavouriteStock(BigInteger.valueOf(2), BigDecimal.valueOf(2), mockedOffset));

        Mockito.when(favouriteStocksRepository.findAll()).thenReturn(stocksNames);
        List<FavouriteStock> allFoundedStocks = favouriteStocksService.getAllFavouriteStocks();

        assertEquals(stocksNames, allFoundedStocks);
    }

    @Test
    public void itShouldAddNewFavouriteStock() {
        Stock inputStock = new Stock(BigInteger.ONE, "TEST", BigDecimal.valueOf(1));
        FavouriteStock newFavouriteStock = new FavouriteStock(BigInteger.ONE, BigDecimal.valueOf(1), mockedOffset);

        Mockito.when(favouriteStocksRepository.save(any())).thenReturn(newFavouriteStock);
        FavouriteStock addedFavouriteStock = favouriteStocksService.addStockToFavourites(inputStock);

        assertEquals(newFavouriteStock.getId(), addedFavouriteStock.getId());
        assertEquals(newFavouriteStock.getPrice(), addedFavouriteStock.getPrice());
    }

    @Test
    public void itShouldThrowExceptionIfWrongStockDuringFavouriteStockAddition() {
        Stock inputStock = new Stock();

        assertThrows(StockBadRequestException.class, () -> favouriteStocksService.addStockToFavourites(inputStock));
    }
}