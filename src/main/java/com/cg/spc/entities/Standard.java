package com.cg.spc.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO class for Standard
 * 
 */

@Entity
public class Standard {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String grade;
	
	@OneToMany(mappedBy = "standard")
	@JsonBackReference("student_standard")
	private List<Student> studentList; 
	
	@OneToOne(mappedBy = "standard",cascade = CascadeType.ALL)
	@JsonBackReference("teacher_standard")
	private Teacher classTeacher;
	
	@ManyToMany(mappedBy = "standardList",cascade = CascadeType.ALL)
	@JsonBackReference("teacher_standardList")
	//@JsonIgnoreProperties("standardList")
	private List<Teacher> subjectTeachers;
	
	@ManyToMany(mappedBy = "standard",cascade = CascadeType.ALL)
	//@JsonBackReference("exam_standard")
	@JsonIgnoreProperties("standard")
	private List<Exam> examList;
	
	@Column
	private int classStrength;
	
	
	public Standard() {
		super();
	}

	public Standard(String grade, List<Student> studentList, Teacher classTeacher, List<Teacher> subjectTeachers,
			List<Exam> examList, int classStrength) {
		super();
		this.grade = grade;
		this.studentList = studentList;
		this.classTeacher = classTeacher;
		this.subjectTeachers = subjectTeachers;
		this.examList = examList;
		this.classStrength = classStrength;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public Teacher getClassTeacher() {
		return classTeacher;
	}

	public void setClassTeacher(Teacher classTeacher) {
		this.classTeacher = classTeacher;
	}

	public List<Teacher> getSubjectTeachers() {
		return subjectTeachers;
	}

	public void setSubjectTeachers(List<Teacher> subjectTeachers) {
		this.subjectTeachers = subjectTeachers;
	}

	public List<Exam> getExamList() {
		return examList;
	}

	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}

	public int getClassStrength() {
		return classStrength;
	}

	public void setClassStrength(int classStrength) {
		this.classStrength = classStrength;
	}

	@Override
	public String toString() {
		return "Standard [id=" + id + ", grade=" + grade + ", studentList=" + studentList + ", classTeacher="
				+ classTeacher + ", subjectTeachers=" + subjectTeachers + ", examList=" + examList + ", classStrength="
				+ classStrength + "]";
	}

}
