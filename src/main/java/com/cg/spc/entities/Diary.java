package com.cg.spc.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * POJO class for Diary
 * 
 */


@Entity
public class Diary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne(mappedBy = "diary",cascade = CascadeType.ALL)
	@JsonBackReference("student_diary")
	private Student student;

	@Column
	private LocalDate generatedDate;

	@Column
	private String remark;

	public Diary() {
		super();

	}

	public Diary(Student student, LocalDate generatedDate, String remark) {
		super();
		this.student = student;
		this.generatedDate = generatedDate;
		this.remark = remark;
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

	public LocalDate getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(LocalDate generatedDate) {
		this.generatedDate = generatedDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Diary [id=" + id + ", student=" + student + ", generatedDate=" + generatedDate + ", remark=" + remark
				+ "]";
	}

}
