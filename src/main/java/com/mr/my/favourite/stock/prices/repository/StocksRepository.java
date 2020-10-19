package com.mr.my.favourite.stock.prices.repository;

import com.mr.my.favourite.stock.prices.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface StocksRepository extends JpaRepository<Stock, BigInteger> {
    Optional<Stock> findByName(String name);
}
