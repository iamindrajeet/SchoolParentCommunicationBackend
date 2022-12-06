package com.cg.spc.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.spc.entities.Standard;
import com.cg.spc.entities.Student;
import com.cg.spc.exceptions.StandardNotFoundException;
import com.cg.spc.exceptions.StudentNotFoundException;
import com.cg.spc.repositories.IStandardRepository;
import com.cg.spc.repositories.IStudentRepository;

/**
 * 
 * 
 * Implementation class for StandardService
 *
 */

@Service
public class StandardServiceImpl implements IStandardService{

	@Autowired
	private IStandardRepository standardRepository;
	
	@Autowired
	private IStudentRepository studentRepository;
	
	Logger logger = LoggerFactory.getLogger(StandardServiceImpl.class);
	
	/**
	 * @param standard
	 * 
	 * @return standard
	 * 
	 * 	- Standard details are added.
	 */
	@Override
	public Standard addDetails(Standard standard) {
		logger.info("Standard addDetails");
		return standardRepository.save(standard);
	}

	/**
	 * @param id
	 * 
	 * @return standard
	 * 
	 * 	- if the  Standard id matches then the Standard details will be retrieved.
	 */
	@Override
	public Standard getDetailsById(int id) {
		logger.info("Standard getDetailsById");
		Standard standard = standardRepository.findById(id).orElseThrow(() -> new StandardNotFoundException());
		return standard;
	}

	/**
	 * @param standard, studentIdList
	 * 
	 * @return standard
	 * 
	 * 	- Standard details will be updated for the particular standardList.
	 */
	@Override
	public Standard updateDetails(Standard standard,List<Integer> studentIdList) {
		logger.info("Standard updateDetails");
		List<Student> studentList = new ArrayList<Student>();
		for (Integer studentId : studentIdList) {
			Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException());
			student.setStandard(standard);
			studentList.add(student);
		}
		standard.setStudentList(studentList);
		return standardRepository.save(standard);
	}

	/**
	 * @param id
	 * 
	 * @return standard
	 * 
	 * 	- if Standard id is matched then the Standard details will be deleted.
	 */
	@Override
	public Standard deleteDetailsById(int id) {
		logger.info("Standard deleteDetailsById");
		Standard standard = standardRepository.findById(id).orElseThrow(() -> new StandardNotFoundException());
		standardRepository.deleteById(id);
		return standard;
	}

}
