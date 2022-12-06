package com.cg.spc.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spc.entities.Attendance;
import com.cg.spc.entities.Concern;
import com.cg.spc.entities.Diary;
import com.cg.spc.entities.Exam;
import com.cg.spc.entities.Fee;
import com.cg.spc.entities.ReportCard;
import com.cg.spc.services.IAttendanceService;
import com.cg.spc.services.IConcernService;
import com.cg.spc.services.IDiaryService;
import com.cg.spc.services.IExamService;
import com.cg.spc.services.IFeeService;
import com.cg.spc.services.IReportCardService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/parent")
public class ParentController {

	@Autowired
	private IAttendanceService attendanceService;

	@Autowired
	private IReportCardService reportCardService;

	@Autowired
	private IFeeService feeService;

	@Autowired
	private IExamService examService;

	@Autowired
	private IDiaryService diaryService;

	@Autowired
	private IConcernService concernService;


	/**
	 * 
	 * 
	 * getting student reportCard details from student id by using GetMapping
	 * 
	 */

	@GetMapping("/student/reportCard/{sId}")
	public ResponseEntity<ReportCard> getReportCardDetails(@PathVariable("sId") int sId) {
		return new ResponseEntity<ReportCard>(reportCardService.getReportCardByStudentId(sId), HttpStatus.OK);
	}


	/**
	 * 
	 * getting student fee details from student id by using GetMapping
	 * 
	 */
	@GetMapping("/student/fee/{sId}")
	public ResponseEntity<Fee> getFee(@PathVariable("sId") int sId) {
		return new ResponseEntity<Fee>(feeService.getFeeByStudentId(sId), HttpStatus.OK);
	}
	


	/**
	 * 
	 * getting student exam details from examDate by using GetMapping
	 * 
	 */
	@GetMapping("/student/exam/{date}")
	public ResponseEntity<Exam> getExamDetails(@PathVariable("date") String date) {
		LocalDate examDate = LocalDate.parse(date);
		return new ResponseEntity<Exam>(examService.getExamByDate(examDate), HttpStatus.OK);
	}
	

	/**
	 * 
	 * getting student diary details from studentId by using GetMapping
	 * 
	 */
	@GetMapping("/student/diary/{sId}")
	public ResponseEntity<Diary> getDailyDiary(@PathVariable("sId") int sId) {
		return new ResponseEntity<Diary>(diaryService.getDiaryByStudentId(sId), HttpStatus.OK);
	}


	/**
	 * 
	 * getting student attendance details from studentId by using GetMapping
	 * 
	 */
	@GetMapping("/student/attendance/{sId}")
	public ResponseEntity<Attendance> getAttendance(@PathVariable("sId") int sId) {
		return new ResponseEntity<Attendance>(attendanceService.getAttendanceByStudentId(sId), HttpStatus.OK);
	}


	/**
	 * 
	 * adding concern details by using PostMapping
	 * 
	 */
	@PostMapping("/concern/add")
	public ResponseEntity<Concern> addConcern(@RequestBody Concern concern, @RequestParam int parentId) {
		return new ResponseEntity<Concern>(concernService.addConcern(concern, parentId), HttpStatus.CREATED);
	}
}
