package com.flm.bed.builder;

import java.util.ArrayList;
import java.util.List;

import com.flm.bed.dto.BedRequestDTO;
import com.flm.bed.dto.RoomRequestDTO;
import com.flm.bed.model.Bed;
import com.flm.bed.model.Room;

public class RoomBuilder {

	public static Room buildRoomFromRoomDto(RoomRequestDTO roomRequestDTO) {
		Room room = Room.builder()
				.roomNumber(roomRequestDTO.getRoomNumber())
				.roomCapacity(roomRequestDTO.getRoomCapacity())
				.roomType(roomRequestDTO.getRoomType())
				.build();
		
		List<BedRequestDTO> bedsRequestDto = roomRequestDTO.getBeds();
		
		if (bedsRequestDto != null && !(bedsRequestDto.isEmpty())) {
			List<Bed> bedsList = new ArrayList<>();
			for(BedRequestDTO dto: bedsRequestDto) {
				bedsList.add(BedBuilder.buildBed(dto, room));
			}
			room.setListOfBeds(bedsList);
		}
		
		return room;
	}
	
}
