package com.cg.spc.services;

import com.cg.spc.entities.Parent;
import java.util.*;

public interface IParentService {

	public List<Parent> getAllParent();
	public Parent getParentById(int id);
	public Parent addParentDetails(Parent parent);
	public Parent deleteParentDetails(int id);
	public Parent updateParentDetails(Parent parent,List<Integer> studentIdList);
	
	
}
