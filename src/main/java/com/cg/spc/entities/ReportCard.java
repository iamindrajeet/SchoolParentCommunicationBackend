package com.cg.spc.entities;

import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * POJO class for ReportCard
 * 
 */

@Entity
public class ReportCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ElementCollection
	private Map <Subject,Integer> marksheet;
	
	@OneToOne
	@JsonBackReference
	private Student student;
	
	@Column(length = 5)
	private boolean isAttempted=false;
	
	public ReportCard() {
		super();
	}

	public ReportCard(Map<Subject, Integer> marksheet, Student student, boolean isAttempted) {

		super();
		this.marksheet = marksheet;
		this.student = student;
		this.isAttempted = isAttempted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Subject, Integer> getMarksheet() {
		return marksheet;
	}

	public void setMarksheet(Map<Subject, Integer> marksheet) {
		this.marksheet = marksheet;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student2) {
		this.student = student2;
	}

	public boolean isAttempted() {
		return isAttempted;
	}

	public void setAttempted(boolean isAttempted) {
		this.isAttempted = isAttempted;
	}

	@Override
	public String toString() {
		return "ReportCard [id=" + id + ", marksheet=" + marksheet + ", student=" + student + ", isAttempted="
				+ isAttempted + "]";
	}

}
