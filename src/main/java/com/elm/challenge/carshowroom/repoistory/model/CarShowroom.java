package com.elm.challenge.carshowroom.repoistory.model;

import com.elm.challenge.carshowroom.web.dto.CarShowroomResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car_showrooms")
@Getter
@Setter
public class CarShowroom extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 10)
    @Pattern(regexp = "\\d{10}")
    private String commercialRegistrationNumber;

    @Column(length = 100)
    private String managerName;

    @Column(nullable = false, length = 15)
    @Pattern(regexp = "\\d{1,15}")
    private String contactNumber;

    @Column(length = 255)
    private String address;

    @OneToMany(mappedBy = "carShowroom", cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();

    public CarShowroomResponse toDto(){
        CarShowroomResponse carShowroomResponse = new CarShowroomResponse();
        BeanUtils.copyProperties(this, carShowroomResponse);
        carShowroomResponse.setCars(this.cars.stream().map(Car::toDto).toList());
       return carShowroomResponse;
    }

}
