package com.flm.bed.service;

import com.flm.bed.dto.BedDetailsResponseDTO;

public interface BedAssignmentHistoryService {
	BedDetailsResponseDTO assignBed(String bedNumber, String patientId);
	BedDetailsResponseDTO vacateBed(String roomNumber, String bedNumber);
}
