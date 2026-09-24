package com.flm.bed.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
	@Id
	@Column(name = "room_number", nullable = false)
	private Long roomNumber;
	
	@Column(name = "room_type")
	private String roomType;
	
	@Column(name = "room_capacity")
	private Long roomCapacity;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	@Builder.Default
	private List<Bed> listOfBeds = new ArrayList<>();

	public Room(String roomType, Long roomCapacity) {
		super();
		this.roomType = roomType;
		this.roomCapacity = roomCapacity;
	}

	public Room(String roomType, Long roomCapacity, List<Bed> listOfBeds) {
		super();
		this.roomType = roomType;
		this.roomCapacity = roomCapacity;
		this.listOfBeds = listOfBeds;
	}
}
