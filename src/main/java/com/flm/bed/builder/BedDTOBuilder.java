package com.flm.bed.builder;

import com.flm.bed.dto.BedDetailsResponseDTO;
import com.flm.bed.model.Bed;

public class BedDTOBuilder {

	public static BedDetailsResponseDTO buildBedDetailsResponseDTOFromBed(Bed bed) {
		return BedDetailsResponseDTO
				.builder()
				.bedNumber(bed.getBedNumber())
				.roomNumber(bed.getRoom().getRoomNumber())
				.isOccupied(bed.getIsOccupied())
				.build();
	}
}
