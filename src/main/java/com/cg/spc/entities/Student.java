package com.cg.spc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * POJO class for Student
 * 
 */

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String name;
	
	@ManyToOne
	@JsonBackReference("parent_student")
	private Parent parent;
	
	@OneToOne
	@JsonManagedReference("student_diary")
	private Diary diary;
	
	@ManyToOne
	@JsonManagedReference("student_standard")
	private Standard standard;
	
	@OneToOne
	@JsonManagedReference("student_attendence")
	private Attendance attendance;
	
	@OneToOne
	@JsonManagedReference("student_fee")
	private Fee fee;
	
	@OneToOne(mappedBy = "student")
	@JsonManagedReference
	private ReportCard reportCard;
	
	public Student() {
		super();
	}

	public Student(String name, Parent parent, Diary diary, Standard standard, Attendance attendance, Fee fee,
			ReportCard reportCard) {
		super();
		this.name = name;
		this.parent = parent;
		this.diary = diary;
		this.standard = standard;
		this.attendance = attendance;
		this.fee = fee;
		this.reportCard = reportCard;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public Diary getDiary() {
		return diary;
	}

	public void setDiary(Diary diary) {
		this.diary = diary;
	}

	public Standard getStandard() {
		return standard;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	public Fee getFee() {
		return fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

	public ReportCard getReportCard() {
		return reportCard;
	}

	public void setReportCard(ReportCard reportCard) {
		this.reportCard = reportCard;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", parent=" + parent + ", diary=" + diary + ", standard="
				+ standard + ", attendance=" + attendance + ", fee=" + fee + ", reportCard=" + reportCard + "]";
	}
	
}
