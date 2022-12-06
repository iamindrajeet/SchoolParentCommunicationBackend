package com.cg.spc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.spc.entities.Fee;

public interface IFeeRepository extends JpaRepository<Fee, Integer>{

	@Query("select f from Fee f where f.student.id = ?1 ")
	public Fee findByStudentId(int sId);
	
}
