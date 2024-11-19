package com.elm.challenge.carshowroom.service;

import com.elm.challenge.carshowroom.repoistory.CarShowroomRepository;
import com.elm.challenge.carshowroom.repoistory.model.CarShowroom;
import com.elm.challenge.carshowroom.web.dto.CarShowroomRequest;
import com.elm.challenge.carshowroom.web.dto.CarShowroomResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CarShowroomService {
    private final CarShowroomRepository carShowroomRepository;

    public CarShowroomResponse createShowroom(CarShowroomRequest request) throws BadRequestException {
        if (carShowroomRepository.existsByCommercialRegistrationNumber(
                request.getCommercialRegistrationNumber())) {
            throw new BadRequestException("Commercial registration number already exists");
        }

        return carShowroomRepository.save(request.toEntity()).toDto();
    }

    public Page<CarShowroomResponse> listShowrooms(Pageable pageable) {
        return carShowroomRepository.findByDeletedFalse(pageable).map(CarShowroom::toDto);
    }

    public CarShowroomResponse getShowroom(Long id) {
        return carShowroomRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Showroom not found")).toDto();
    }

    public CarShowroomResponse updateShowroom(Long id, CarShowroomRequest dto) {
        CarShowroom showroom = getShowroomEntity(id);

        if (dto.getContactNumber() != null) {
            showroom.setContactNumber(dto.getContactNumber());
        }
        if (dto.getManagerName() != null) {
            showroom.setManagerName(dto.getManagerName());
        }
        if (dto.getAddress() != null) {
            showroom.setAddress(dto.getAddress());
        }

        return carShowroomRepository.save(showroom).toDto();
    }

    public void deleteShowroom(Long id) {
        CarShowroom showroom = getShowroomEntity(id);
        showroom.setDeleted(true);
        carShowroomRepository.save(showroom);
    }

    public CarShowroom getShowroomEntity(Long id) {
        return carShowroomRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Showroom not found"));
    }
}
