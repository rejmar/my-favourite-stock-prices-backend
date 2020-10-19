package com.mr.my.favourite.stock.prices.service.impl;

import com.mr.my.favourite.stock.prices.exception.StockBadRequestException;
import com.mr.my.favourite.stock.prices.exception.StockNotFoundException;
import com.mr.my.favourite.stock.prices.model.FavouriteStock;
import com.mr.my.favourite.stock.prices.model.Stock;
import com.mr.my.favourite.stock.prices.repository.FavouriteStocksRepository;
import com.mr.my.favourite.stock.prices.service.FavouriteStocksService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class FavouriteStocksServiceImpl implements FavouriteStocksService {
    private final FavouriteStocksRepository favouriteStocksRepository;

    @Autowired
    public FavouriteStocksServiceImpl(FavouriteStocksRepository favouriteStocksRepository) {
        this.favouriteStocksRepository = favouriteStocksRepository;
    }

    @Override
    public List<FavouriteStock> getAllFavouriteStocks() {
        return favouriteStocksRepository.findAll();
    }

    @Override
    public FavouriteStock addStockToFavourites(Stock stock) {
        if (stock.getId() != null && stock.getPrice() != null) {
            FavouriteStock newFavouriteStock = new FavouriteStock();
            newFavouriteStock.setId(stock.getId());
            newFavouriteStock.setPrice(stock.getPrice());
            newFavouriteStock.setTimestamp(OffsetDateTime.now());

            favouriteStocksRepository.save(newFavouriteStock);
            log.info("Saving {} to favourites", stock.getName());
            return newFavouriteStock;
        } else {
            log.info("Wrong stock provided: {}", stock);
            throw new StockBadRequestException("Wrong stock provided!");
        }
    }

    @Override
    public FavouriteStock deleteFavouriteStock(String id) {
        BigInteger identity = BigInteger.valueOf(Long.parseLong(id));
        if (identity.doubleValue() > 0) {
            Optional<FavouriteStock> favouriteStock = favouriteStocksRepository.findById(identity);

            FavouriteStock foundFavouriteStock = favouriteStock.orElseThrow(
                    () -> new StockNotFoundException("Fav stock with id = " + id + " not found!"));

            favouriteStocksRepository.delete(favouriteStock.get());
            log.info("Stock with id = {} removed from favourites", id);
            return foundFavouriteStock;
        } else {
            log.info("Id cannot be empty or less than 0!");
            throw new StockBadRequestException("Id cannot be empty or less than 0!");
        }
    }
}
