package com.flm.bed.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flm.bed.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
	
	Optional<Room> findByRoomNumber(Long roomNumber);
}
