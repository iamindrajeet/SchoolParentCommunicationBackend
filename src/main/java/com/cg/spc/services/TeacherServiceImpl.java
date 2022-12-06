package com.cg.spc.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spc.entities.Standard;
import com.cg.spc.entities.Teacher;
import com.cg.spc.exceptions.StandardNotFoundException;
import com.cg.spc.exceptions.TeacherNotFoundException;
import com.cg.spc.repositories.IStandardRepository;
import com.cg.spc.repositories.ITeacherRepository;

/**
 * 
 * 
 * Implementation class for TeacherService
 *
 */

@Service
public class TeacherServiceImpl implements ITeacherService{

	@Autowired
	private ITeacherRepository teacherRepository;
	
	@Autowired
	private IStandardRepository standardRepository;
	
	Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

	/**
	 * 
	 * @return teacher
	 * 
	 * 	- all the teacher details will be retrieved.
	 */
	@Override
	public List<Teacher> getAllTeachers() {
		logger.info("Teacher getAllTeachers");
		return teacherRepository.findAll();
	}


	/**
	 * @param id
	 * 
	 * @return teacher
	 * 
	 * 	- if teacher id is matched then the teacher details will be retrieved.
	 */
	@Override
	public Teacher getTeacherById(int id) {
		logger.info("Teacher getTeacherById");
		return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException());
	}


	/**
	 * @param id
	 * 
	 * @return teacher
	 * 
	 * 	- if teacher id is matched then the teacher details will be deleted.
	 */
	@Override
	public Teacher deleteTeacherById(int id) {
		logger.info("Teacher deleteTeacherById");
		Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException());
		teacherRepository.deleteById(id);
		return teacher;
	}


	/**
	 * @param teacher, standardList, standardId
	 * 
	 * @return teacher
	 * 
	 * 	- if standard id is valid then the teacher detail is updated.  
	 */
	@Override
	public Teacher updateTeacher(Teacher teacher,String standardIdList,String standardId) {
		logger.info("Teacher updateTeacher");
		String sIdList[] = standardIdList.split(",");
		int sIdListInt[]= new int[sIdList.length];
		for (int i=0;i<sIdList.length;i++) {
			sIdListInt[i]=Integer.parseInt(sIdList[i]);
		}
		List<Standard> standardList = new ArrayList<Standard>();
		for (Integer id : sIdListInt) {
			Standard standard = standardRepository.findById(id).orElseThrow(() -> new StandardNotFoundException());
			standardList.add(standard);
		}
		teacher.setStandardList(standardList);
		int sId = Integer.parseInt(standardId);
		Standard standard = standardRepository.findById(sId).orElseThrow(() -> new StandardNotFoundException());
		teacher.setStandard(standard);
		return teacherRepository.save(teacher);
	}

	@Override
	public Teacher addTeacher(Teacher teacher) {
		logger.info("Teacher addTeacher");
		return teacherRepository.save(teacher);
	}

}
