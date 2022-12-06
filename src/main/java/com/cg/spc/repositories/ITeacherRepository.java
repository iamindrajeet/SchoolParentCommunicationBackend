package com.cg.spc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.spc.entities.Teacher;

public interface ITeacherRepository extends JpaRepository<Teacher, Integer>{
	
}
