package com.mr.my.favourite.stock.prices.repository;

import com.mr.my.favourite.stock.prices.model.FavouriteStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface FavouriteStocksRepository extends JpaRepository<FavouriteStock, BigInteger> {
}