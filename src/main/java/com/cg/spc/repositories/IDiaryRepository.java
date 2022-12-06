package com.cg.spc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.spc.entities.Diary;

public interface IDiaryRepository extends JpaRepository<Diary, Integer> {
	
	@Query("select d from Diary d where d.student.id=?1")
	public Diary findByStudentId(int sId);
	
}
