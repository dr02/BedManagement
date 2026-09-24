package com.flm.bed.service.impl;

import org.springframework.stereotype.Service;

import com.flm.bed.builder.RoomBuilder;
import com.flm.bed.builder.RoomDTOBuilder;
import com.flm.bed.dao.RoomRepository;
import com.flm.bed.dto.RoomRequestDTO;
import com.flm.bed.dto.RoomResponseDTO;
import com.flm.bed.model.Room;
import com.flm.bed.service.RoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
	private final RoomRepository roomRepository;
	
	@Override
	public RoomResponseDTO addRoom(RoomRequestDTO roomRequestDTO) {
		Room room = RoomBuilder.buildRoom(roomRequestDTO);
		
		Room savedRoom = roomRepository.save(room);
		return RoomDTOBuilder.buildRoomResponseDTOFromRoom(savedRoom);
	}

}
