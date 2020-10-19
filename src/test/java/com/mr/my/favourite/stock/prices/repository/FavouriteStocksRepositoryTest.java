package com.mr.my.favourite.stock.prices.repository;

import com.mr.my.favourite.stock.prices.model.FavouriteStock;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FavouriteStocksRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    FavouriteStocksRepository favouriteStocksRepository;


    @Test
    public void itShouldSaveNewFavouriteStock() {
        FavouriteStock newFavouriteStock = new FavouriteStock();
        newFavouriteStock.setId(BigInteger.ONE);
        newFavouriteStock.setPrice(BigDecimal.valueOf(100));
        newFavouriteStock.setTimestamp(OffsetDateTime.of(LocalDate.parse("2020-01-01"), LocalTime.parse("01:00:00"), ZoneOffset.UTC));

        favouriteStocksRepository.save(newFavouriteStock);

        assertEquals(newFavouriteStock, favouriteStocksRepository.findById(BigInteger.valueOf(1)).get());
    }

    @Test
    public void itShouldThrowExceptionWhenStockNotFound() {
        assertThrows(NoSuchElementException.class, () -> {
            favouriteStocksRepository.findById(BigInteger.valueOf(1)).get();
        });
    }
}