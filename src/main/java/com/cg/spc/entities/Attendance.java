package com.cg.spc.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

 /**
 * POJO class for Attendance
 * 
 */
@Entity
public class Attendance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(mappedBy = "attendance",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonBackReference("student_attendence")
	private Student student;
	
	@Column
	private LocalDate attendanceDate;
	
	@Column(length = 5)
	private boolean present;

	public Attendance() {
		super();
		
	}

	public Attendance(Student student, LocalDate attendanceDate, boolean present) {
		super();
		this.student = student;
		this.attendanceDate = attendanceDate;
		this.present = present;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	@Override
	public String toString() {
		return "Attendance [id=" + id + ", student=" + student + ", attendanceDate=" + attendanceDate + ", present="
				+ present + "]";
	}

	
}
