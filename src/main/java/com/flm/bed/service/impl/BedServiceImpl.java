package com.flm.bed.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flm.bed.builder.BedBuilder;
import com.flm.bed.builder.BedDTOBuilder;
import com.flm.bed.dao.BedRepository;
import com.flm.bed.dao.RoomRepository;
import com.flm.bed.dto.BedDetailsResponseDTO;
import com.flm.bed.dto.BedRequestDTO;
import com.flm.bed.exception.BedNotFoundException;
import com.flm.bed.exception.BedUnavailableException;
import com.flm.bed.exception.RoomNotFoundException;
import com.flm.bed.model.Bed;
import com.flm.bed.model.Room;
import com.flm.bed.service.BedService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BedServiceImpl implements BedService {

	private final BedRepository bedRepository;
	private final RoomRepository roomRepository;

	@Override
	public BedDetailsResponseDTO addBedInRoom(BedRequestDTO bedRequestDTO) {
		Room room = findRoomByRoomNumber(bedRequestDTO.getRoomNumber());
		List<Bed> listOfBeds = room.getListOfBeds();
		
		if(!(listOfBeds.isEmpty() || listOfBeds.size() < room.getRoomCapacity())) throw new IllegalStateException("Cannot add Bed into Room Number "+bedRequestDTO.getRoomNumber()+" as the room is full.");
			
		boolean bedNumberExists = listOfBeds.stream()
				.anyMatch((bed) -> bed.getBedNumber() == bedRequestDTO.getBedNumber());
		
		if(bedNumberExists) throw new IllegalArgumentException("Bed Number "+bedRequestDTO.getBedNumber()+" already exists in room number "+bedRequestDTO.getRoomNumber());
		
		Bed bed = BedBuilder.buildBed(bedRequestDTO, room);
		Bed addedBed = bedRepository.save(bed);
		return BedDTOBuilder.buildBedDetailsResponseDTOFromBed(addedBed);
	}

	@Override
	public BedDetailsResponseDTO updateBedInRoom(String roomNumber, String bedNumber, BedRequestDTO bedRequestDTO) {
		Room room = findRoomByRoomNumber(roomNumber);
		Bed bed = findBedByBedNumber(bedNumber, room);
		bed.setIsOccupied(!bed.getIsOccupied());
		Bed updatedBed = bedRepository.save(bed);
		return BedDTOBuilder.buildBedDetailsResponseDTOFromBed(updatedBed);
	}
	
	@Override
	public String removeBed(String roomNumber, String bedNumber) {
		Room room = findRoomByRoomNumber(roomNumber);
		Bed bed = findBedByBedNumber(bedNumber, room);
		bedRepository.delete(bed);
		return "Success";
	}
	
	@Override
	public List<BedDetailsResponseDTO> getAllBeds() {
		List<Bed> allBedsList = bedRepository.findAll();
		return allBedsList
				.stream()
				.map(BedDTOBuilder::buildBedDetailsResponseDTOFromBed)
				.toList();
	}
	
	@Override
	public List<BedDetailsResponseDTO> getVacantBedsByRoomNumber(String roomNumber) {
		Room room = findRoomByRoomNumber(roomNumber);
		List<Bed> listOfBeds = room.getListOfBeds();
		List<Bed> vacantBedsList = listOfBeds
		.stream()
		.filter((bed) -> bed.getIsOccupied() != false)
		.toList();
		
		if(vacantBedsList.isEmpty()) throw new BedUnavailableException("No vacant beds available in room number "+roomNumber);
		
		return vacantBedsList
				.stream()
				.map(BedDTOBuilder::buildBedDetailsResponseDTOFromBed)
				.toList();
	}
	
	private Room findRoomByRoomNumber(String roomNumber) {
		long id = Long.parseLong(roomNumber);
		return findRoomByRoomNumber(id);
	}
	
	private Room findRoomByRoomNumber(Long roomNumber) {
		return roomRepository.findByRoomNumber(roomNumber).orElseThrow(() -> new RoomNotFoundException("Room Number "+roomNumber+" doesn't exist."));
	}
	
	private Bed findBedByBedNumber(String bedNumber, Room room) {
		long bedNum = Long.parseLong(bedNumber);
		return bedRepository.findByBedNumberAndRoom(bedNum, room).orElseThrow(() -> new BedNotFoundException("Bed Not Found with BedNumber: "+bedNumber+" in Room Number: "+room.getRoomNumber()));
	}
}
