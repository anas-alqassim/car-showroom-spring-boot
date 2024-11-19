package com.elm.challenge.carshowroom.web.dto;

import com.elm.challenge.carshowroom.repoistory.model.Car;
import com.elm.challenge.carshowroom.repoistory.model.CarShowroom;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CarShowroomRequest {
    @Size(max = 100)
    private String name;
    @Size(max = 10)
    @Pattern(regexp = "\\d{10}")
    private String commercialRegistrationNumber;
    @Size(max = 100)
    private String managerName;
    @Size(max = 10)
    @Pattern(regexp = "\\d{1,15}")
    private String contactNumber;
    @Size(max = 255)
    private String address;

    public CarShowroom toEntity() {
        CarShowroom showroom = new CarShowroom();
        BeanUtils.copyProperties(this, showroom);
        return showroom;
    }
}
