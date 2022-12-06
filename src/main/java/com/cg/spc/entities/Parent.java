package com.cg.spc.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * POJO class for Parent
 * 
 */

@Entity
public class Parent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	@JsonManagedReference("parent_student")
	private List<Student> student;

	@Column
	private String name;

	@Column
	private String contact;

	@OneToMany(mappedBy = "parent")
	@JsonManagedReference("parent_concern")
	private List<Concern> concern;

	public Parent() {
		super();
	}

	public Parent(List<Student> student, String name, String contact, List<Concern> concern) {
		super();
		this.student = student;
		this.name = name;
		this.contact = contact;
		this.concern = concern;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Concern> getConcern() {
		return concern;
	}

	public void setConcern(List<Concern> concern) {
		this.concern = concern;
	}

	@Override
	public String toString() {
		return "Parent [id=" + id + ", student=" + student + ", name=" + name + ", contact=" + contact + ", concern="
				+ concern + "]";
	}

}
