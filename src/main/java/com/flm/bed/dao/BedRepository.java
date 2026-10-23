package com.flm.bed.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flm.bed.model.Bed;
import com.flm.bed.model.Room;

public interface BedRepository extends JpaRepository<Bed, Long>{
	Optional<Bed> findByBedNumberAndRoom(Long bedNumber, Room room);
	Optional<Bed> findByBedNumber(Long bedNumber);
}
