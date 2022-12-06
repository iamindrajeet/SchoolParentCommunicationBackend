package com.cg.spc.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.spc.entities.Parent;
import com.cg.spc.entities.Student;
import com.cg.spc.exceptions.ParentNotFoundException;
import com.cg.spc.exceptions.StudentNotFoundException;
import com.cg.spc.repositories.IParentRepository;
import com.cg.spc.repositories.IStudentRepository;

/**
 * 
 * 
 * Implementation class for ParentService
 *
 */

@Service
public class ParentServiceImpl implements IParentService{
	
	@Autowired
	private IParentRepository parentRepository;
	
	@Autowired
	private IStudentRepository studentRepository;
	
	Logger logger = LoggerFactory.getLogger(ParentServiceImpl.class);
	
	/**
	 * 
	 * @return parent
	 * 
	 * 	- all the parents details will be retrieved.
	 */
	@Override
	public List<Parent> getAllParent() {
		logger.info("Parent getAllParent");
		return parentRepository.findAll();
	}

	/**
	 * @param id
	 * 
	 * @return parent
	 * 
	 * 	- if the parent id matches then the parent details will be retrieved.
	 */
	@Override
	public Parent getParentById(int id) {
		logger.info("Parent getParentById");
		return parentRepository.findById(id).orElseThrow(() -> new ParentNotFoundException());
	}

	/**
	 * @param parent
	 * 
	 * @return parent
	 * 
	 * 	- add the parent.
	 */
	@Override
	public Parent addParentDetails(Parent parent) {
		logger.info("Parent addParentDetails");
		return parentRepository.save(parent);
	}

	/**
	 * @param id
	 * 
	 * @return parent
	 * 
	 * 	- if the parent id matches then the parent details will be deleted.
	 */
	@Override
	public Parent deleteParentDetails(int id) {
		logger.info("Parent deleteParentDetails");
		Parent parent = parentRepository.findById(id).orElseThrow(() -> new ParentNotFoundException());
		parentRepository.deleteById(id);
		return parent;
	}

	
	/**
	 * @param parent, studentIdList
	 * 
	 * @return parent
	 * 
	 * 	-parent details will be updated based on the student id given.
	 */
	@Override
	public Parent updateParentDetails(Parent parent,List<Integer> studentIdList)
	{
		logger.info("Parent updateParentDetails");
		List<Student> studentList = new ArrayList<Student>();
		for (Integer studentId : studentIdList) {
			Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException());
			student.setParent(parent);
			studentList.add(student);
		}
		return parentRepository.save(parent);
	}

}
