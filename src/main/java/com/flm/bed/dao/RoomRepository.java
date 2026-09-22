package com.flm.bed.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flm.bed.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
