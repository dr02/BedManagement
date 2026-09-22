package com.flm.bed.builder;

import java.util.List;

import com.flm.bed.dto.BedRequestDTO;
import com.flm.bed.dto.RoomRequestDTO;
import com.flm.bed.model.Bed;
import com.flm.bed.model.Room;

public class RoomBuilder {

	public static Room buildRoom(RoomRequestDTO roomRequestDTO) {
		return Room.builder()
				.roomNumber(roomRequestDTO.getRoomNumber())
				.roomCapacity(roomRequestDTO.getRoomCapacity())
				.roomType(roomRequestDTO.getRoomType())
				.build();
	}
	
	
	
}
