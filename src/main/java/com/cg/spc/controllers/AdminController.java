package com.cg.spc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cg.spc.entities.Parent;
import com.cg.spc.entities.Standard;
import com.cg.spc.entities.Student;
import com.cg.spc.entities.Teacher;
import com.cg.spc.services.IParentService;
import com.cg.spc.services.IStandardService;
import com.cg.spc.services.IStudentService;
import com.cg.spc.services.ITeacherService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IParentService parentService;

	@Autowired
	private IStudentService studentService;

	@Autowired
	private ITeacherService teacherService;

	@Autowired
	private IStandardService standardService;


	/**
	 * 
	 * adding parent details by using PostMapping
	 * 
	 */
	@PostMapping("/parent/add")
	public ResponseEntity<Parent> addParentDetails(@RequestBody Parent parent) {
		return new ResponseEntity<Parent>(parentService.addParentDetails(parent),HttpStatus.CREATED) ;
	}


	/**
	 * 
	 * updating parent details by using PutMapping
	 * 
	 */

	@PutMapping("/parent/update")
	public ResponseEntity<Parent> updateParentDetails(@RequestBody Parent parent, @RequestParam List<Integer> studentIdList) {
		return new ResponseEntity<Parent>(parentService.updateParentDetails(parent, studentIdList),HttpStatus.OK);
	}


	/**
	 * 
	 * getting parent details by using GetMapping
	 * 
	 */
	
	@GetMapping("/parent/getParents")
	public ResponseEntity<List<Parent>>  getAllParent() {
		return new ResponseEntity<List<Parent>>(parentService.getAllParent(),HttpStatus.OK);
	}
	
	/**
	 * 
	 * getting student details by using GetMapping
	 * 
	 */

	@GetMapping("/student/getStudents")
	public ResponseEntity<List<Student>> getAllStudents() {
		return new ResponseEntity<List<Student>>(studentService.getAllStudents(),HttpStatus.OK);
		
	}

	/**
	 * 
	 * adding student details by using PostMapping
	 * 
	 */
	@PostMapping("/student/add")
	public ResponseEntity<Student> addStudentDetails(@RequestBody Student student) {
		return new ResponseEntity<Student>(studentService.addStudent(student),HttpStatus.CREATED);
	}


	/**
	 * 
	 * updating student details by using PutMapping
	 * 
	 */
	@PutMapping("/student/update")
	public ResponseEntity<Student> updateStudentDetails(@RequestBody Student student) {
		return new ResponseEntity<Student>(studentService.updateStudent(student),HttpStatus.OK);
	}


	/**
	 * 
	 * adding teacher details by using PostMapping
	 * 
	 */
	@PostMapping("/teacher/add")
	public ResponseEntity<Teacher> addTeacherDetails(@RequestBody Teacher teacher) {
		return new ResponseEntity<Teacher> (teacherService.addTeacher(teacher),HttpStatus.CREATED);
	}


	/**
	 * 
	 *
	 * updating teacher details by using PutMapping
	 * 
	 */
	@PutMapping("/teacher/update")
	public ResponseEntity<Teacher> updateTeacherDetails(@RequestBody Teacher teacher, @RequestParam String standardIdList,
			@RequestParam String standardId) {

		return new ResponseEntity<Teacher>(teacherService.updateTeacher(teacher, standardIdList, standardId),HttpStatus.OK);
	}


	/**
	 * 
	 * getting teacher details by using GetMapping
	 * 
	 */
	@GetMapping("/teacher/getTeachers")
	public ResponseEntity<List<Teacher>> getAllTeacher() {
		return new ResponseEntity<List<Teacher>>(teacherService.getAllTeachers(),HttpStatus.OK);
	}
	
	


	/**
	 * 
	 * adding standard details by using PostMapping
	 * 
	 */
	@PostMapping("/standard/add")
	public ResponseEntity<Standard> addStandardDetails(@RequestBody Standard standard) {
		return new ResponseEntity<Standard>(standardService.addDetails(standard), HttpStatus.CREATED);
	}


	/**
	 * 
	 * adding standard details by using PutMapping
	 * 
	 */
	@PutMapping("/standard/update")
	public ResponseEntity<Standard> updateStandardDetails(@RequestBody Standard standard,@RequestParam List<Integer> studentIdList) {
		return new ResponseEntity<Standard>(standardService.updateDetails(standard, studentIdList),HttpStatus.OK);
	}

}