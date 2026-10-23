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
	
	@PutMapping("/update-rooms/{roomNumber}")
	public ResponseEntity<RoomResponseDTO> updateRoom(@PathVariable String roomNumber, @RequestBody RoomRequestDTO roomRequestDTO) {
		RoomResponseDTO roomResponseDTO = roomService.updateRoom(roomNumber, roomRequestDTO);
		return ResponseEntity.ok(roomResponseDTO);
	}
	
	@DeleteMapping("/delete-rooms/{roomNumber}")
	public ResponseEntity<Boolean> removeRoom(@PathVariable String roomNumber) {
		Boolean isRoomRemoved = roomService.removeRoom(roomNumber);
		return ResponseEntity.ok(isRoomRemoved);
	}
	
	@GetMapping("/getAllRooms")
	public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
		List<RoomResponseDTO> allRooms = roomService.getAllRooms();
		return ResponseEntity.ok(allRooms);
	}
	
	
}
