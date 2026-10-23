package com.flm.bed.service;

import java.util.List;

import com.flm.bed.dto.BedDetailsResponseDTO;
import com.flm.bed.dto.BedRequestDTO;

public interface BedService {
	BedDetailsResponseDTO addBedInRoom(BedRequestDTO bedRequestDTO);
	BedDetailsResponseDTO updateBedInRoom(String roomNumber, String bedNumber, BedRequestDTO bedRequestDTO);
	String removeBed(String roomNumber, String bedNumber);
	List<BedDetailsResponseDTO> getAllBeds();
	List<BedDetailsResponseDTO> getVacantBedsByRoomNumber(String roomNumber);
}
