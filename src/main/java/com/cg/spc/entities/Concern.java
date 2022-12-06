package com.cg.spc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * POJO class for Concern
 * 
 */

@Entity
public class Concern {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String concern;

	@Column(length = 5)
	private boolean isResolved = false;

	@ManyToOne
	@JsonBackReference("parent_concern")
	private Parent parent;

	@Enumerated(EnumType.STRING)
	private ConcernType concernType;

	public Concern() {
		super();
	}

	public Concern(String concern, boolean isResolved, Parent parent, ConcernType concernType) {
		super();
		this.concern = concern;
		this.isResolved = isResolved;
		this.parent = parent;
		this.concernType = concernType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConcern() {
		return concern;
	}

	public void setConcern(String concern) {
		this.concern = concern;
	}

	public boolean isResolved() {
		return isResolved;
	}

	public void setResolved(boolean isResolved) {
		this.isResolved = isResolved;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public ConcernType getConcernType() {
		return concernType;
	}

	public void setConcernType(ConcernType concernType) {
		this.concernType = concernType;
	}

	@Override
	public String toString() {
		return "Concern [id=" + id + ", concern=" + concern + ", isResolved=" + isResolved + ", parent=" + parent + "]";
	}

}
