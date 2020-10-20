package com.mr.my.favourite.stock.prices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mr.my.favourite.stock.prices.model.FavouriteStock;
import com.mr.my.favourite.stock.prices.model.Stock;
import com.mr.my.favourite.stock.prices.service.FavouriteStocksService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FavouriteStocksController.class)
class FavouriteStocksControllerTest {
    @MockBean
    FavouriteStocksService stocksService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void itShouldCheckGetAllFavStocksEndpoint() throws Exception {
        List<FavouriteStock> mockedOutput = Collections.singletonList(new FavouriteStock(BigInteger.ONE, "TEST", BigDecimal.ONE, OffsetDateTime.now()));
        when(stocksService.getAllFavouriteStocks()).thenReturn(mockedOutput);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/fav-stocks/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void itShouldCheckAddFavStockEndpoint() throws Exception {
        Stock inputStock = new Stock(BigInteger.ONE, "TEST", BigDecimal.ONE);
        FavouriteStock outputStock = new FavouriteStock();
        when(stocksService.addStockToFavourites(any(Stock.class))).thenReturn(outputStock);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/fav-stocks/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputStock)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void itShouldCheckDeleteFavStockEndpoint() throws Exception {
        FavouriteStock outputStock = new FavouriteStock();
        when(stocksService.deleteFavouriteStock(anyString())).thenReturn(outputStock);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/fav-stocks/{id}", "1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(outputStock)));
    }

}