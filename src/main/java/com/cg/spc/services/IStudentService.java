package com.cg.spc.services;

import java.util.List;

import com.cg.spc.entities.Student;

public interface IStudentService {

	public List<Student> getAllStudents();
	public Student getStudentById(int id);
	public Student deleteStudentById(int id);
	public Student updateStudent(Student student);
//	public Student updateStudent(Student student);
	public Student addStudent(Student student);
	
}
