package com.flm.bed.builder;

import java.util.List;

import com.flm.bed.dto.BedDetailsResponseDTO;
import com.flm.bed.dto.RoomResponseDTO;
import com.flm.bed.model.Bed;
import com.flm.bed.model.Room;

public class RoomDTOBuilder {

	public static RoomResponseDTO buildRoomResponseDTOFromRoom(Room room) {
		return RoomResponseDTO
				.builder()
				.roomNumber(room.getRoomNumber())
				.roomCapacity(room.getRoomCapacity())
				.roomType(room.getRoomType())
				.beds(buildBedDetailsResponseDTOs(room.getListOfBeds()))
				.build();
	}
	
	private static List<BedDetailsResponseDTO> buildBedDetailsResponseDTOs(List<Bed> beds) {
		return beds
				.stream()
				.map(BedDTOBuilder::buildBedDetailsResponseDTOFromBed)
				.toList();
	}
}
