package com.elm.challenge.carshowroom.service;

import com.elm.challenge.carshowroom.repoistory.CarRepository;
import com.elm.challenge.carshowroom.repoistory.model.Car;
import com.elm.challenge.carshowroom.repoistory.model.CarShowroom;
import com.elm.challenge.carshowroom.web.dto.CarRequest;
import com.elm.challenge.carshowroom.web.dto.CarResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarShowroomService carShowroomService;

    public CarResponse createCar(CarRequest dto) {
        CarShowroom showroom = carShowroomService.getShowroomEntity(dto.getCarShowroomId());

        Car car = new Car();
        BeanUtils.copyProperties(dto, car);
        car.setCarShowroom(showroom);
        return carRepository.save(car).toDto();
    }

    public Page<CarResponse> listCars(String maker, String showroomName, Pageable pageable) {
        if (maker != null && showroomName != null) {
            return carRepository.findByMakerAndCarShowroom_NameAndDeletedFalseAndCarShowroomDeletedFalse(
                    maker, showroomName, pageable).map(Car::toDto);
        } else if (maker != null) {
            return carRepository.findByMakerAndDeletedFalseAndCarShowroomDeletedFalse(maker, pageable).map(Car::toDto);
        }
        else if (showroomName != null) {
            return carRepository.findByCarShowroom_NameAndDeletedFalseAndCarShowroomDeletedFalse(showroomName, pageable).map(Car::toDto);
        }
        return carRepository.findByDeletedFalseAndCarShowroomDeletedFalse(pageable).map(Car::toDto);
    }
}