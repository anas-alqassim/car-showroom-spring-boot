package com.elm.challenge.carshowroom.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarResponse {
    private String vin;
    private String maker;
    private String model;
    private Integer modelYear;
    private BigDecimal price;
}