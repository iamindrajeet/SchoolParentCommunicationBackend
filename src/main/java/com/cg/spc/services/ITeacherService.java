package com.cg.spc.services;

import java.util.List;

import com.cg.spc.entities.Teacher;

public interface ITeacherService {

	public List<Teacher> getAllTeachers();
	public Teacher getTeacherById(int id);
	public Teacher deleteTeacherById(int id);
	public Teacher updateTeacher(Teacher teacher,String standardIdList,String standardId);
	public Teacher addTeacher(Teacher teacher);
	
}
