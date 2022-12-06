package com.cg.spc.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.spc.entities.Concern;

public interface IConcernRepository extends JpaRepository<Concern, Integer>{
}
