package com.mr.my.favourite.stock.prices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mr.my.favourite.stock.prices.model.Stock;
import com.mr.my.favourite.stock.prices.service.impl.StocksServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StocksController.class)
class StocksControllerTest {
    @MockBean
    StocksServiceImpl stocksService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void itShouldCheckGetAllStocksEndpoint() throws Exception {
        List<Stock> mockedOutput = Collections.singletonList(new Stock(BigInteger.ONE, "TEST", BigDecimal.ONE));
        when(stocksService.getAllStocks()).thenReturn(mockedOutput);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/stocks/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(mockedOutput)));
    }

    @Test
    public void itShouldCheckGetStockByNameEndpoint() throws Exception {
        Stock mockedOutput = new Stock(BigInteger.ONE, "TEST", BigDecimal.ONE);
        when(stocksService.getStockByName(anyString())).thenReturn(mockedOutput);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/stocks/apple"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(mockedOutput)));
    }
}