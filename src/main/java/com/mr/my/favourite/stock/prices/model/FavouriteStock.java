package com.mr.my.favourite.stock.prices.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavouriteStock {
    @Id
    @NotNull
    @Column(name = "stock_id")
    private BigInteger id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @NotNull
    @Column(name = "timestamp")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private OffsetDateTime timestamp;
}
