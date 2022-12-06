package com.cg.spc.services;

import java.util.List;

import com.cg.spc.entities.Concern;

public interface IConcernService {
	public Concern addConcern(Concern concern, int parentId);
	public Concern updateConcern(Concern concern, int parentId);
	public List<Concern> getAllConcerns();
	public Concern deleteById(int id);
}
