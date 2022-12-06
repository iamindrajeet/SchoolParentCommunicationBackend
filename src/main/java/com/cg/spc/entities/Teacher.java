package com.cg.spc.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * POJO class for Teacher
 * 
 */

@Entity
public class Teacher {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	
	@Column(length=25)
	private String name;
	
	@OneToOne
	@JsonManagedReference("teacher_standard")
	private Standard standard;
	
	@ManyToMany
//	@JsonManagedReference("teacher_standardList")
	@JsonIgnoreProperties("subjectTeachers")
	private List<Standard> standardList;
	
	@Enumerated(EnumType.STRING)
	private Subject subject;
	
	public Teacher() {
		super();
		
	}

	public Teacher(String name, Standard standard, List<Standard> standardList, Subject subject) {
		super();
		this.name = name;
		this.standard = standard;
		this.standardList = standardList;
		this.subject = subject;
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

	public Standard getStandard() {
		return standard;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	public List<Standard> getStandardList() {
		return standardList;
	}

	public void setStandardList(List<Standard> standardList) {
		this.standardList = standardList;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", standard=" + standard + ", standardList=" + standardList
				+ ", subject=" + subject + "]";
	}
	
}
