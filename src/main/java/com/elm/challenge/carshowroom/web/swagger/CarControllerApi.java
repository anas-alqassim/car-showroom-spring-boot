package com.elm.challenge.carshowroom.web.swagger;

import com.elm.challenge.carshowroom.web.dto.CarRequest;
import com.elm.challenge.carshowroom.web.dto.CarResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@OpenAPIDefinition(
        info = @Info(title = "Cars Management API", description = "1")
)
@SecurityRequirement(name = "access_token")
@Tag(name = "Cars Management API", description = "API for managing cars in showrooms")
public interface CarControllerApi {

    @Operation(
            method = "POST",
            summary = "Add new car",
            operationId = "createCar",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "401", description = "Not authorized to creating new car"),
            }
    )
    ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest dto);

    @Operation(
            method = "GET",
            summary = "Getting all cars in showrooms by maker or showroomName",
            operationId = "listCars",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "401", description = "Not authorized to creating new car"),
            }
    )
    ResponseEntity<Page<CarResponse>> listCars(
            @RequestParam(required = false) String maker,
            @RequestParam(required = false) String showroomName,
            @ParameterObject Pageable pageable);
}
