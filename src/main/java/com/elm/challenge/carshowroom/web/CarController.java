package com.elm.challenge.carshowroom.web;

import com.elm.challenge.carshowroom.service.CarService;
import com.elm.challenge.carshowroom.web.dto.CarRequest;
import com.elm.challenge.carshowroom.web.dto.CarResponse;
import com.elm.challenge.carshowroom.web.swagger.CarControllerApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController implements CarControllerApi {
    private final CarService carService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    @Override
    public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest dto) {
        return ResponseEntity.ok(carService.createCar(dto));
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<CarResponse>> listCars(
            @RequestParam(required = false) String maker,
            @RequestParam(required = false) String showroomName,
            @PageableDefault(sort = "maker") Pageable pageable) {
        return ResponseEntity.ok(carService.listCars(maker, showroomName, pageable));
    }

}
