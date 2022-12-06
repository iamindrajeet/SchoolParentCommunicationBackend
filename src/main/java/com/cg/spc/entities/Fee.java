package com.cg.spc.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * POJO class for Fee
 * 
 */

@Entity
public class Fee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne(mappedBy = "fee",cascade = CascadeType.ALL)
	@JsonBackReference("student_fee")
	private Student student;
	
	private double feeDue;
	
	private double feePaid;
	
	private final double totalFee = 24000;

	public Fee() {
		super();
	}

	public Fee(Student student, double feeDue, double feePaid) {
		super();
		this.student = student;
		this.feeDue = feeDue;
		this.feePaid = feePaid;
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

	public double getFeeDue() {
		return feeDue;
	}

	public void setFeeDue(double feeDue) {
		this.feeDue = feeDue;
	}

	public double getFeePaid() {
		return feePaid;
	}

	public void setFeePaid(double feePaid) {
		this.feePaid = feePaid;
	}

	public double getTotalFee() {
		return totalFee;
	}

	@Override
	public String toString() {
		return "Fee [id=" + id + ", student=" + student + ", feeDue=" + feeDue + ", feePaid=" + feePaid + ", totalFee="
				+ totalFee + "]";
	}

}
