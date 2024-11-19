package com.elm.challenge.carshowroom.web.dto;

import com.elm.challenge.carshowroom.repoistory.model.Car;
import com.elm.challenge.carshowroom.repoistory.model.CarShowroom;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CarRequest {
    @Size(min = 25,max = 25)
    private String vin;
    @Size(min = 25,max = 25)
    private String maker;
    @Size(min = 25,max = 25)
    private String model;
    @Max(9999)
    private Integer modelYear;
    private BigDecimal price;
    private Long carShowroomId;


    public Car toEntity() {
        Car car = new Car();
        BeanUtils.copyProperties(this, car);
        return car;
    }

}