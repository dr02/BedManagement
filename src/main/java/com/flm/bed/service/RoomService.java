package com.flm.bed.service;

import java.util.List;

import com.flm.bed.dto.RoomRequestDTO;
import com.flm.bed.dto.RoomResponseDTO;

public interface RoomService {
	RoomResponseDTO addRoom(RoomRequestDTO roomRequestDTO);
	RoomResponseDTO updateRoom(String roomNumber, RoomRequestDTO roomRequestDTO);
	Boolean removeRoom(String roomNumber);
	List<RoomResponseDTO> getAllRooms();
}
