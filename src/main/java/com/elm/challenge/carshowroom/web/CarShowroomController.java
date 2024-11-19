package com.elm.challenge.carshowroom.web;

import com.elm.challenge.carshowroom.service.CarShowroomService;
import com.elm.challenge.carshowroom.web.dto.CarShowroomRequest;
import com.elm.challenge.carshowroom.web.dto.CarShowroomResponse;
import com.elm.challenge.carshowroom.web.swagger.CarShowroomControllerAPI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/showrooms")
@RequiredArgsConstructor
public class CarShowroomController implements CarShowroomControllerAPI {
    private final CarShowroomService carShowroomService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    @Override
    public ResponseEntity<CarShowroomResponse> createShowroom(@Valid @RequestBody CarShowroomRequest dto) throws BadRequestException {
        return ResponseEntity.ok(carShowroomService.createShowroom(dto));
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<CarShowroomResponse>> listShowrooms(
            @PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.ok(carShowroomService.listShowrooms(pageable));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CarShowroomResponse> getShowroom(@PathVariable Long id) {
        return ResponseEntity.ok(carShowroomService.getShowroom(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    @Override
    public ResponseEntity<CarShowroomResponse> updateShowroom(
            @PathVariable Long id, @Valid @RequestBody CarShowroomRequest dto) {
        return ResponseEntity.ok(carShowroomService.updateShowroom(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    @Override
    public ResponseEntity<Void> deleteShowroom(@PathVariable Long id) {
        carShowroomService.deleteShowroom(id);
        return ResponseEntity.noContent().build();
    }
}

