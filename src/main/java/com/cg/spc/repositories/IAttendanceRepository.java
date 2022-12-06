package com.cg.spc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.spc.entities.Attendance;

public interface IAttendanceRepository extends JpaRepository<Attendance, Integer>{

	@Query("select a from Attendance a where a.student.id = ?1")
	public Attendance findByStudentId(int sId);
	
}
