package com.flm.bed.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDTO {
	private long roomNumber;
	private String roomType;
	private long roomCapacity;
	private List<BedDetailsResponseDTO> beds;
}
