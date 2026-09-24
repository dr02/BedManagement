package com.flm.bed.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flm.bed.dto.RoomRequestDTO;
import com.flm.bed.dto.RoomResponseDTO;
import com.flm.bed.service.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
	
	private final RoomService roomService;
	
	@PostMapping("/addRoom")
	public ResponseEntity<RoomResponseDTO> addRoom(@RequestBody RoomRequestDTO roomRequestDTO) {
		RoomResponseDTO roomResponseDTO = roomService.addRoom(roomRequestDTO);
		return new ResponseEntity<RoomResponseDTO>(roomResponseDTO, HttpStatus.CREATED);
	}
}
