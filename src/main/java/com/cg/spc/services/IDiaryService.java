package com.cg.spc.services;

import java.util.List;

import com.cg.spc.entities.Diary;


public interface IDiaryService {

	public Diary addDiary(Diary diary, int studentId);
	public Diary updateDiary(Diary diary, int studentId);
	public Diary getDiaryById(int id);
	public Diary deleteDiaryById(int id);
	public List<Diary> getAllDiaryDetails();
	public Diary getDiaryByStudentId(int id);
}
