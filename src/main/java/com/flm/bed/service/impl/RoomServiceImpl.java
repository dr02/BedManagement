package com.flm.bed.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flm.bed.builder.BedBuilder;
import com.flm.bed.builder.RoomBuilder;
import com.flm.bed.builder.RoomDTOBuilder;
import com.flm.bed.dao.RoomRepository;
import com.flm.bed.dto.BedRequestDTO;
import com.flm.bed.dto.RoomRequestDTO;
import com.flm.bed.dto.RoomResponseDTO;
import com.flm.bed.exception.RoomNotFoundException;
import com.flm.bed.model.Bed;
import com.flm.bed.model.Room;
import com.flm.bed.service.RoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
	private final RoomRepository roomRepository;
	
	@Override
	public RoomResponseDTO addRoom(RoomRequestDTO roomRequestDTO) {
		Room room = RoomBuilder.buildRoomFromRoomDto(roomRequestDTO);
		
		Room savedRoom = roomRepository.save(room);
		return RoomDTOBuilder.buildRoomResponseDTOFromRoom(savedRoom);
	}

	@Override
	public RoomResponseDTO updateRoom(String roomNumber, RoomRequestDTO roomRequestDTO) {
		Room room = findRoomByRoomNumber(roomNumber);
		List<BedRequestDTO> bedsReqDto = roomRequestDTO.getBeds();
		
		long currentBedCount = room.getRoomCapacity();
		long requestBedCount = roomRequestDTO.getRoomCapacity();
		
		if(requestBedCount < currentBedCount) throw new IllegalArgumentException("Cannot reduce room capacity to "+requestBedCount+" as room currently has "+currentBedCount+" beds.");
		
		room.setRoomCapacity(roomRequestDTO.getRoomCapacity());
		room.setRoomType(roomRequestDTO.getRoomType());
		
		if (bedsReqDto != null) {
			List<Bed> bedsList = new ArrayList<>();
			for(BedRequestDTO dto: bedsReqDto) {
				bedsList.add(BedBuilder.buildBed(dto, room));
			}
		}
		
		roomRepository.save(room);
		
		return RoomDTOBuilder.buildRoomResponseDTOFromRoom(room);
	}
	
	@Override
	public Boolean removeRoom(String roomNumber) {
		Room room = findRoomByRoomNumber(roomNumber);
		roomRepository.delete(room);
		return true;
	}
	
	@Override
	public List<RoomResponseDTO> getAllRooms() {
		List<Room> allRooms = roomRepository.findAll();
		return allRooms
				.stream()
				.map(RoomDTOBuilder::buildRoomResponseDTOFromRoom)
				.toList();
	}

	private Room findRoomByRoomNumber(String roomNumber) {
		long roomNum = Long.parseLong(roomNumber);
		return roomRepository.findByRoomNumber(roomNum).orElseThrow(() -> new RoomNotFoundException("Room Not Found with Room Number: "+roomNumber));
	}
}
