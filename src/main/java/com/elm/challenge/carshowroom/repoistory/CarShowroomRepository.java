package com.elm.challenge.carshowroom.repoistory;

import com.elm.challenge.carshowroom.repoistory.model.CarShowroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarShowroomRepository extends JpaRepository<CarShowroom, Long> {
    Page<CarShowroom> findByDeletedFalse(Pageable pageable);
    Optional<CarShowroom> findByIdAndDeletedFalse(Long id);
    boolean existsByCommercialRegistrationNumber(String commercialRegistrationNumber);
}
