package com.flm.bed.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bed")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bed {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bed_number")
	private Long bedNumber;
	
	@ManyToOne
	@JoinColumn(name = "room_number")
	private Room room;
	
	@Column(name = "is_occupied")
	private Boolean isOccupied;
	
	@Column(name = "patient_id")
	private Long patientId;
	
	@OneToMany(mappedBy = "bed", cascade = CascadeType.ALL)
	@Builder.Default
	private List<BedAssignmentHistory> bedAssignmentHistories = new ArrayList<BedAssignmentHistory>();

	public Bed(Room room, Boolean isOccupied, Long patientId) {
		super();
		this.room = room;
		this.isOccupied = isOccupied;
		this.patientId = patientId;
	}
	
	
}
