package com.elm.challenge.carshowroom.web.dto;

import com.elm.challenge.carshowroom.repoistory.model.Car;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarShowroomResponse {
    private Long id;
    private String name;
    private String commercialRegistrationNumber;
    private String managerName;
    private String contactNumber;
    private String address;
    private List<CarResponse> cars = new ArrayList<>();
}
