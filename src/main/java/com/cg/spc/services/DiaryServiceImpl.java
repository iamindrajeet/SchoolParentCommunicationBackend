package com.cg.spc.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spc.entities.Diary;
import com.cg.spc.entities.Student;
import com.cg.spc.exceptions.DiaryNotFoundException;
import com.cg.spc.exceptions.StudentNotFoundException;
import com.cg.spc.repositories.IDiaryRepository;
import com.cg.spc.repositories.IStudentRepository;

/**
 * 
 * 
 * Implementation class for DiaryService
 *
 */

@Service
public class DiaryServiceImpl implements IDiaryService{
	
	@Autowired
	private IDiaryRepository diaryRepository;

	@Autowired
	private IStudentRepository studentRepository;
	
	Logger logger = LoggerFactory.getLogger(DiaryServiceImpl.class);
	
	/**
	 * @param diary, studentId
	 * 
	 * @return diary
	 * 
	 * 	- if the studentId matches then diary details will be added 
	 * 
	 */
	@Override
	public Diary addDiary(Diary diary, int studentId) {
		logger.info("Diary addDiary");
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException());
		diary.setStudent(student);
		student.setDiary(diary);
		return diaryRepository.save(diary);
	}

	/**
	 * @param diary, studentId
	 * 
	 * @return diary
	 * 
	 * 	- if the studentId matches then diary will be updated 
	 * 
	 */
	@Override
	public Diary updateDiary(Diary diary, int studentId) {
		logger.info("Diary updateDiary");
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException());
		diary.setStudent(student);
		student.setDiary(diary);
		return diaryRepository.save(diary);
	}
	
	/**
	 * @param id
	 * 
	 * @return diary
	 * 
	 * 	- if the diary id matches then diary details will be retrieved 
	 * 
	 */
	@Override
	public Diary getDiaryById(int id) {
		logger.info("Diary getDiaryById");
		Diary diary = diaryRepository.findById(id).orElseThrow(() -> new DiaryNotFoundException());
		return diary;
	}

	/**
	 * @param id
	 * 
	 * @return diary
	 * 
	 * 	- if the diary id matches then diary details will be deleted 
	 * 
	 */
	@Override
	public Diary deleteDiaryById(int id) {
		logger.info("Diary deleteDiaryById");
		Diary diary = diaryRepository.findById(id).orElseThrow(() -> new DiaryNotFoundException());
		diaryRepository.deleteById(id);
		return diary;
	}

	
	/**
	 * @return diary
	 * 
	 * 	- all the diary details are retrieved
	 */
	@Override
	public List<Diary> getAllDiaryDetails() {
		logger.info("Diary getAllDiaryDetails");
		return diaryRepository.findAll();
	}
	
	
	/**
	 * @param id
	 * 
	 * @return diary
	 * 
	 * 	- if the diary id matches then the diary details will be retrieved. 
	 */
	@Override
	public Diary getDiaryByStudentId(int id) {
		logger.info("Diary getDiaryByStudentId");
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException());
		if(student.getDiary()==null) {
            throw new DiaryNotFoundException();
        }
        return diaryRepository.findByStudentId(id);
		
	}

}
