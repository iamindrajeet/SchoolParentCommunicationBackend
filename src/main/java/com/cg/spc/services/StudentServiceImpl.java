package com.cg.spc.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.spc.entities.Student;
import com.cg.spc.exceptions.StudentNotFoundException;
import com.cg.spc.repositories.IStudentRepository;

/**
 * 
 * 
 * Implementation class for StudentService
 *
 */

@Service
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private IStudentRepository studentRepository;
	
	Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	/**
	 * @return student
	 * 
	 * 	- all the student details will be retrieved.
	 */
	@Override
	public List<Student> getAllStudents() {
		logger.info("Student getAllStudents");
		return studentRepository.findAll();
	}

	/**
	 * @param id
	 * 
	 * @return student
	 * 
	 * 	- if student id is matched then the Student details will be retrieved.
	 */
	@Override
	public Student getStudentById(int id) {
		logger.info("Student getStudentById");
		return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException());
	}

	/**
	 * @param id
	 * 
	 * @return student
	 * 
	 * 	- if student id is matched then the student details will be deleted.
	 */
	@Override
	public Student deleteStudentById(int id) {
		logger.info("Student deleteStudentById");
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException());
		studentRepository.deleteById(id);
		return student;
	}


	/**
	 * @param student
	 * 
	 * @return st
	 * 
	 * 	- Students details will be updated.
	 */
	@Override
	public Student updateStudent(Student student) {
		logger.info("Student updateStudent");
		Student st = studentRepository.save(student);
		return st;
	}


	/**
	 * @param student
	 * 
	 * @return student
	 * 
	 * 	- student details are added.
	 */
	@Override
	public Student addStudent(Student student) {
		logger.info("Student addStudent");
		return studentRepository.save(student);
	}

}
