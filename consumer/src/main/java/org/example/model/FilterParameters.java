package org.example.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FilterParameters {
    private BigDecimal productMinPrice;
    private BigDecimal productMaxPrice;
    private Long categoryId;
}