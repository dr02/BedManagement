package com.flm.bed.builder;

import com.flm.bed.dto.BedRequestDTO;
import com.flm.bed.model.Bed;
import com.flm.bed.model.Room;

public class BedBuilder {

	public static Bed buildBed(BedRequestDTO bedRequestDTO, Room room) {
		return Bed.builder()
				.patientId(0L)
				.bedNumber(bedRequestDTO.getBedNumber())
				.isOccupied(bedRequestDTO.isOccupied())
				.room(room)
				.build();
	}
}
