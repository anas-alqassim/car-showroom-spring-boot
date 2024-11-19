package com.elm.challenge.carshowroom.web.swagger;

import com.elm.challenge.carshowroom.web.dto.CarShowroomRequest;
import com.elm.challenge.carshowroom.web.dto.CarShowroomResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@OpenAPIDefinition(
        info = @Info(title = "Car showrooms Management API", description = "1")
)
@SecurityRequirement(name = "access_token")
@Tag(name = "Car showrooms Management API", description = "API for managing Car showrooms")
public interface CarShowroomControllerAPI {

    ResponseEntity<CarShowroomResponse> createShowroom(
            @Valid @RequestBody CarShowroomRequest dto
    ) throws BadRequestException;

    @Operation(
            method = "GET",
            summary = "Getting all cars showrooms",
            operationId = "listShowrooms"
    )
    ResponseEntity<Page<CarShowroomResponse>> listShowrooms(
            @ParameterObject Pageable pageable
    );

    ResponseEntity<CarShowroomResponse> getShowroom(@PathVariable Long id);

    ResponseEntity<CarShowroomResponse> updateShowroom(
            @PathVariable Long id,
            @Valid @RequestBody CarShowroomRequest dto
    );

    ResponseEntity<Void> deleteShowroom(@PathVariable Long id);
}

