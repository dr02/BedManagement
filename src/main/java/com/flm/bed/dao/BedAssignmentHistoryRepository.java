package com.flm.bed.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flm.bed.model.Bed;
import com.flm.bed.model.BedAssignmentHistory;

public interface BedAssignmentHistoryRepository extends JpaRepository<BedAssignmentHistory, Long>{
	
	Optional<BedAssignmentHistory> findByBed(Bed bed);
}
