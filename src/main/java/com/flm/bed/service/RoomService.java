package com.flm.bed.service;

import com.flm.bed.dto.RoomRequestDTO;
import com.flm.bed.dto.RoomResponseDTO;

public interface RoomService {
	RoomResponseDTO addRoom(RoomRequestDTO roomRequestDTO);
}
