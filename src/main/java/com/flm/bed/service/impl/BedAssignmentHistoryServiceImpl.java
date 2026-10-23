package com.flm.bed.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.flm.bed.builder.BedDTOBuilder;
import com.flm.bed.dao.BedAssignmentHistoryRepository;
import com.flm.bed.dao.BedRepository;
import com.flm.bed.dao.RoomRepository;
import com.flm.bed.dto.BedDetailsResponseDTO;
import com.flm.bed.exception.BedNotFoundException;
import com.flm.bed.exception.BedUnavailableException;
import com.flm.bed.exception.RoomNotFoundException;
import com.flm.bed.model.Bed;
import com.flm.bed.model.BedAssignmentHistory;
import com.flm.bed.model.Room;
import com.flm.bed.service.BedAssignmentHistoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BedAssignmentHistoryServiceImpl implements BedAssignmentHistoryService {
	
	private final BedAssignmentHistoryRepository bedAssignmentHistoryRepository;
	private final BedRepository bedRepository;
	private final RoomRepository roomRepository;
	
	@Override
	public BedDetailsResponseDTO assignBed(String bedNumber, String patientId) {
		Bed bed = findBedByBedNumber(bedNumber);
		if(bed.getIsOccupied()) throw new BedUnavailableException("Bed "+bedNumber+" is already occupied.");
		
		long patientIdLong = Long.parseLong(patientId);
		
		bed.setIsOccupied(true);
		bed.setPatientId(patientIdLong);
		
		BedAssignmentHistory bedAssignmentHistory = new BedAssignmentHistory(bed, patientIdLong, LocalDateTime.now());
		Bed savedBed = bedRepository.save(bed);
		bedAssignmentHistoryRepository.saveAndFlush(bedAssignmentHistory);
		
		return BedDTOBuilder.buildBedDetailsResponseDTOFromBed(savedBed);
	}

	@Override
	public BedDetailsResponseDTO vacateBed(String roomNumber, String bedNumber) {
		Bed bed = findBedByBedNumber(bedNumber);
		
		BedAssignmentHistory bedAssignmentHistory = bedAssignmentHistoryRepository.findByBed(bed).orElseThrow(() -> new IllegalStateException("No active bed assignment found for bed "+bedNumber));
		
		bed.setIsOccupied(false);
		bed.setPatientId(0L);
		Bed updatedBed = bedRepository.save(bed);
		
		bedAssignmentHistory.setVacatedAt(LocalDateTime.now());
		bedAssignmentHistoryRepository.save(bedAssignmentHistory);
		return BedDTOBuilder.buildBedDetailsResponseDTOFromBed(updatedBed);
	}
	
	private Bed findBedByBedNumber(String bedNumber) {
		long id = Long.parseLong(bedNumber);
		return bedRepository.findByBedNumber(id).orElseThrow(() -> new BedNotFoundException("Bed Not Found with BedNumber: "+bedNumber));
	}

	private Bed findBedByBedNumberAndRoom(String bedNumber, String roomNumber) {
		long bedNumberLong = Long.parseLong(bedNumber);
		long roomNumberLong = Long.parseLong(roomNumber);
		
		Room room = roomRepository.findByRoomNumber(roomNumberLong).orElseThrow(() -> new RoomNotFoundException("Room not found with ID: "+roomNumber));
		return bedRepository.findByBedNumberAndRoom(bedNumberLong, room).orElseThrow(() -> new BedNotFoundException("Bed Not Found with BedNumber: "+bedNumber+" in room number: "+roomNumber));
	}
}
