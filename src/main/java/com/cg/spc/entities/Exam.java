package com.cg.spc.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO class for Exam
 * 
 */


@Entity
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private LocalDate examDate;

	@Enumerated(EnumType.STRING)
	private Subject subject;

	@ManyToMany
	//@JsonManagedReference("exam_standard")
	
	@JsonIgnoreProperties("examList")
	private List<Standard> standard;
	
	@Column
	private String duration;
	
	@Column
	private int marks;

	public Exam() {
		super();
	}

	public Exam(LocalDate examDate, Subject subject, List<Standard> standard, String duration, int marks) {
		super();
		this.examDate = examDate;
		this.subject = subject;
		this.standard = standard;
		this.duration = duration;
		this.marks = marks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getExamDate() {
		return examDate;
	}

	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Standard> getStandard() {
		return standard;
	}

	public void setStandard(List<Standard> standard) {
		this.standard = standard;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Exam [id=" + id + ", examDate=" + examDate + ", subject=" + subject + ", standard=" + standard
				+ ", duration=" + duration + ", marks=" + marks + "]";
	}

	
}
