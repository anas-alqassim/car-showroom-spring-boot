package com.elm.challenge.carshowroom.repoistory.model;

import com.elm.challenge.carshowroom.web.dto.CarResponse;
import com.elm.challenge.carshowroom.web.dto.CarShowroomResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Entity
@Table(name = "cars")
@Getter
@Setter
public class Car extends BaseEntity {
    @Column(nullable = false, length = 25)
    private String vin;

    @Column(nullable = false, length = 25)
    private String maker;

    @Column(nullable = false, length = 25)
    private String model;

    @Column(nullable = false)
    @Max(9999)
    private Integer modelYear;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_showroom_id", nullable = false)
    private CarShowroom carShowroom;

    public CarResponse toDto(){
        CarResponse carShowroomResponse = new CarResponse();
        BeanUtils.copyProperties(this, carShowroomResponse);
        return carShowroomResponse;
    }

}
