package com.mr.my.favourite.stock.prices.utils;

import java.math.BigDecimal;
import java.util.Random;

public class RandomGenerator {

    private static BigDecimal convertToBigDecimal(Float value) {
        return BigDecimal
                .valueOf(value);
    }

    public static BigDecimal generateStockPrice(int from, int to, int firstDigit) {
        Random generator = new Random();
        return convertToBigDecimal(generator.nextFloat() * (to - from) + firstDigit);
    }
}
