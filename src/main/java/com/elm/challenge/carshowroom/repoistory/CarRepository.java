package com.elm.challenge.carshowroom.repoistory;

import com.elm.challenge.carshowroom.repoistory.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findByDeletedFalseAndCarShowroomDeletedFalse(Pageable pageable);
    Page<Car> findByMakerAndDeletedFalseAndCarShowroomDeletedFalse(String maker, Pageable pageable);
    Page<Car> findByCarShowroom_NameAndDeletedFalseAndCarShowroomDeletedFalse(String showroomName, Pageable pageable);
    Page<Car> findByMakerAndCarShowroom_NameAndDeletedFalseAndCarShowroomDeletedFalse(
            String maker, String showroomName, Pageable pageable);
}