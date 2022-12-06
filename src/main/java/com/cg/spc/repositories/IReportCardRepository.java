package com.cg.spc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.spc.entities.ReportCard;

public interface IReportCardRepository extends JpaRepository<ReportCard, Integer>{

	@Query("select r from ReportCard r where r.student.id = ?1 ")
	public ReportCard findByStudentId(int sId);
	
}
