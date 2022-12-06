package com.cg.spc.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.spc.entities.Exam;

public interface IExamRepository extends JpaRepository<Exam,Integer> {

	public Exam findByExamDate(LocalDate examDate);
	
}
