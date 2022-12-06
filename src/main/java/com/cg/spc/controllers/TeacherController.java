package com.cg.spc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cg.spc.entities.Attendance;
import com.cg.spc.entities.Concern;
import com.cg.spc.entities.Diary;
import com.cg.spc.entities.Exam;
import com.cg.spc.entities.ReportCard;
import com.cg.spc.services.IAttendanceService;
import com.cg.spc.services.IConcernService;
import com.cg.spc.services.IDiaryService;
import com.cg.spc.services.IExamService;
import com.cg.spc.services.IReportCardService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private IExamService examService;

	@Autowired
	private IReportCardService reportCardService;

	@Autowired
	private IAttendanceService attendanceService;

	@Autowired
	private IDiaryService diaryService;

	@Autowired
	private IConcernService concernService;


	/**
	 * 
	 * adding exam details by using PostMapping
	 * 
	 */
	@PostMapping("/exam/add")
	public ResponseEntity<Exam> addExamDetails(@RequestBody Exam exam, @RequestParam List<Integer> standardIdList) {
		return new ResponseEntity<Exam>(examService.addExam(exam, standardIdList), HttpStatus.CREATED);
	}


	/**
	 * 
	 * updating exam details by using PutMapping
	 * 
	 */
	@PutMapping("/exam/update")
	public ResponseEntity<Exam> updateExamDetails(@RequestBody Exam exam, @RequestParam List<Integer> standardIdList) {
		return new ResponseEntity<Exam>(examService.updateExam(exam, standardIdList), HttpStatus.OK);
	}


	/**
	 * 
	 * adding reportCard details by using PostMapping
	 * 
	 */
	@PostMapping("/reportCard/add")
	public ResponseEntity<ReportCard> addReportCardDetails(@RequestBody ReportCard reportCard,
			@RequestParam int studentId) {
		return new ResponseEntity<ReportCard>(reportCardService.addDetails(reportCard, studentId), HttpStatus.CREATED);
	}

	/**
	 * 
	 * updating reportCard details by using PutMapping
	 * 
	 */
	@PutMapping("/reportCard/update")
	public ResponseEntity<ReportCard> updateReportCardDetails(@RequestBody ReportCard reportCard,
			@RequestParam int studentId) {
		return new ResponseEntity<ReportCard>(reportCardService.addDetails(reportCard, studentId), HttpStatus.OK);
	}

	/**
	 * 
	 * adding attendance details by using PostMapping
	 * 
	 */
	@PostMapping("/attendance/add")
	public ResponseEntity<Attendance> addAttendanceDetails(@RequestBody Attendance attendance,
			@RequestParam int studentId) {
		return new ResponseEntity<Attendance>(attendanceService.addAttendance(attendance, studentId),
				HttpStatus.CREATED);
	}

	/**
	 * 
	 * updating attendance details by using PutMapping
	 * 
	 */
	@PutMapping("/attendance/update")
	public ResponseEntity<Attendance> updateAttendanceDetails(@RequestBody Attendance attendance,
			@RequestParam int studentId) {
		return new ResponseEntity<Attendance>(attendanceService.updateAttendance(attendance, studentId), HttpStatus.OK);
	}

	/**
	 * 
	 * adding dailyDiary details by using PostMapping
	 * 
	 */
	@PostMapping("/dailyDiary/add")
	public ResponseEntity<Diary> addDailyDiaryDetails(@RequestBody Diary diary, @RequestParam int studentId) {
		return new ResponseEntity<Diary>(diaryService.addDiary(diary, studentId), HttpStatus.CREATED);
	}

	/**
	 * 
	 * updating dailyDiary details by using PuttMapping
	 * 
	 */
	@PutMapping("/dailyDiary/update")
	public ResponseEntity<Diary> updateDailyDiaryDetails(@RequestBody Diary diary, @RequestParam int studentId) {
		return new ResponseEntity<Diary>(diaryService.updateDiary(diary, studentId), HttpStatus.OK);
	}
	
	/**
	 * 
	 * updating concern status by using PutMapping
	 * 
	 */
	@PutMapping("/concern/status")
	public ResponseEntity<Concern> updateConcernDetails(@RequestBody Concern concern,@RequestParam int parentId) {
		if (concernService.updateConcern(concern, parentId) == null) {
			return new ResponseEntity<Concern>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Concern>(concern, HttpStatus.OK);
		}
	}

}
