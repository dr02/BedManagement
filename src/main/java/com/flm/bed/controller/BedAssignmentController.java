package com.flm.bed.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flm.bed.dto.BedDetailsResponseDTO;
import com.flm.bed.service.BedAssignmentHistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bedAssignment")
@RequiredArgsConstructor
public class BedAssignmentController {

	private final BedAssignmentHistoryService bedAssignmentHistoryService;
	
	@PostMapping("/assignBed/{bedNumber}/{patientId}")
	public ResponseEntity<BedDetailsResponseDTO> assignBed(@PathVariable String bedNumber, @PathVariable String patientId) {
		BedDetailsResponseDTO bedDetailsResponseDTO = bedAssignmentHistoryService.assignBed(bedNumber, patientId);
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(bedDetailsResponseDTO);
	}
	
	@PostMapping("/vacateBed/{roomNumber}/{bedNumber}")
	public ResponseEntity<BedDetailsResponseDTO> vacateBed(@PathVariable String roomNumber, @PathVariable String bedNumber) {
		BedDetailsResponseDTO bedDetailsResponseDTO = bedAssignmentHistoryService.vacateBed(roomNumber, bedNumber);
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(bedDetailsResponseDTO);
	}
}
