package com.cg.spc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Team 1
 * 
 * Team Members : - Rahul Pandey 
 * 					Indrajeet Singh 
 * 					Akash Bobade 
 * 					Kaushik More
 * 					Deepak Shaw 
 * 					Anikesh Sinha 
 *
 */
@SpringBootApplication
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
