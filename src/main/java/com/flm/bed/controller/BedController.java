package com.flm.bed.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flm.bed.dto.BedDetailsResponseDTO;
import com.flm.bed.dto.BedRequestDTO;
import com.flm.bed.service.BedService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bed")
@RequiredArgsConstructor
public class BedController {
	
	private final BedService bedService;
	
	@PostMapping("/addBed")
	public ResponseEntity<String> addBedInRoom(@RequestBody BedRequestDTO bedRequestDTO) {
		BedDetailsResponseDTO bedDetailsResponseDTO = bedService.addBedInRoom(bedRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added Bed Number "+bedDetailsResponseDTO.getBedNumber()+" into Room Number "+bedDetailsResponseDTO.getRoomNumber());
	}
	
	@PutMapping("/update-bed/{roomNumber}/{bedNumber}")
	public ResponseEntity<BedDetailsResponseDTO> updateBedInRoom(@PathVariable String roomNumber, @PathVariable String bedNumber, @RequestBody BedRequestDTO bedRequestDTO) {
		BedDetailsResponseDTO bedDetailsResponseDto = bedService.updateBedInRoom(roomNumber, bedNumber, bedRequestDTO);
		return ResponseEntity.ok(bedDetailsResponseDto);
	}
	
	@DeleteMapping("/delete-bed/{roomNumber}/{bedNumber}")
	public ResponseEntity<String> removeBed(@PathVariable String roomNumber, @PathVariable String bedNumber) {
		String successMessage = bedService.removeBed(roomNumber, bedNumber);
		return ResponseEntity.ok(successMessage);
	}
	
	@GetMapping("/getAllBeds")
	public ResponseEntity<List<BedDetailsResponseDTO>> getAllBeds() {
		List<BedDetailsResponseDTO> allBeds = bedService.getAllBeds();
		return ResponseEntity.ok(allBeds);
	}
	
	@GetMapping("/vacant/room/{roomNumber}")
	public ResponseEntity<List<BedDetailsResponseDTO>> getVacantBedsByRoomNumber(@PathVariable String roomNumber) {
		List<BedDetailsResponseDTO> vacantBedsByRoomNumber = bedService.getVacantBedsByRoomNumber(roomNumber);
		return ResponseEntity.ok(vacantBedsByRoomNumber);
	}
}
