package com.cg.spc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.cg.spc.entities.Parent;

public interface IParentRepository extends JpaRepository<Parent, Integer>{

}

