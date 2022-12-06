package com.cg.spc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.spc.entities.ReportCard;
import com.cg.spc.entities.Student;
import com.cg.spc.entities.Subject;
import com.cg.spc.exceptions.ReportCardNotFoundException;
import com.cg.spc.exceptions.StudentNotFoundException;
import com.cg.spc.repositories.IReportCardRepository;
import com.cg.spc.repositories.IStudentRepository;
import com.cg.spc.services.IReportCardService;

@SpringBootTest
public class ReportCardServiceTest {

	@Autowired
	private IReportCardService reportCardService;

	@MockBean
	private IReportCardRepository reportCardRepository;

	@MockBean
	private IStudentRepository studentRepository;

	ReportCard reportCard;

	Student student;

	Map<Subject, Integer> marksheet2;

	@BeforeEach
	public void init() {
		reportCard = new ReportCard();
		reportCard.setId(200);
		Map<Subject, Integer> marksheet = new HashMap<Subject, Integer>();
		marksheet.put(Subject.ENGLISH, 78);
		marksheet.put(Subject.HINDI, 98);
		marksheet.put(Subject.MATHS, 89);
		marksheet.put(Subject.SOCIAL_STUDIES, 78);
		marksheet.put(Subject.SCIENCE, 78);
		marksheet.put(Subject.HISTORY_CIVICS, 56);
		marksheet.put(Subject.GEOGRAPHY, 65);
		student = new Student();
		student.setId(300);
		student.setName("Rahul");
		reportCard.setMarksheet(marksheet);
		reportCard.setStudent(student);
		student.setReportCard(reportCard);

		marksheet2 = new HashMap<Subject, Integer>();
		marksheet2.put(Subject.ENGLISH, 75);
		marksheet2.put(Subject.HINDI, 92);
		marksheet2.put(Subject.MATHS, 85);
		marksheet2.put(Subject.SOCIAL_STUDIES, 73);
		marksheet2.put(Subject.SCIENCE, 72);
		marksheet2.put(Subject.HISTORY_CIVICS, 51);
		marksheet2.put(Subject.GEOGRAPHY, 62);
	}

	@Test
	@DisplayName("Test case to add details by student ID")
	public void testAddDetails() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(reportCardRepository.save(reportCard)).thenReturn(reportCard);
		assertEquals(reportCard, reportCardService.addDetails(reportCard, 300));
	}

	@Test
	@DisplayName("Test case to add details with wrong student ID")
	public void testAddDetailsNegative() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(reportCardRepository.save(reportCard)).thenReturn(reportCard);
		Assertions.assertThrows(StudentNotFoundException.class, () -> reportCardService.addDetails(reportCard, 19));
	}

	@Test
	@DisplayName("Test case to update details by student ID")
	public void testUpdateDetails() {
		reportCard.setMarksheet(marksheet2);
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(reportCardRepository.save(reportCard)).thenReturn(reportCard);
		assertEquals(reportCard, reportCardService.updateDetails(reportCard, 300));
	}

	@Test
	@DisplayName("Test case to update details with wrong student ID")
	public void testUpdateDetailsNegative() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(reportCardRepository.save(reportCard)).thenReturn(reportCard);
		Assertions.assertThrows(StudentNotFoundException.class, () -> reportCardService.updateDetails(reportCard, 101));
	}

	@Test
	@DisplayName("Test case to get Details by report card ID")
	public void testGetDetailsById() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(reportCardRepository.findById(reportCard.getId())).thenReturn(Optional.of(reportCard));
		assertEquals(reportCard, reportCardService.getDetailsById(200));
	}

	@Test
	@DisplayName("Test case to get Details with wrong report card ID")
	public void testGetDetailsByIdNegative() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(reportCardRepository.findById(reportCard.getId())).thenReturn(Optional.of(reportCard));
		Assertions.assertThrows(ReportCardNotFoundException.class, () -> reportCardService.getDetailsById(201));
	}

	@Test
	@DisplayName("Test case to get ReportCard by student ID")
	public void testGetReportCardByStudentId() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(reportCardRepository.findByStudentId(300)).thenReturn(reportCard);
		assertEquals(reportCard, reportCardService.getReportCardByStudentId(300));
	}

	@Test
	@DisplayName("Test case to get ReportCard with wrong student ID")
	public void testGetReportCardByStudentIdNegative() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(reportCardRepository.findByStudentId(reportCard.getId())).thenReturn(reportCard);
		Assertions.assertThrows(StudentNotFoundException.class, () -> reportCardService.getReportCardByStudentId(301));
	}
	
	@Test
	@DisplayName("Test case to find if report card has been generated for valid student ID")
	public void testGetReportCardByStudentIdException() {
		Student testStudent = new Student();
		testStudent.setId(301);
		testStudent.setName("Vivek");
		Mockito.when(studentRepository.findById(testStudent.getId())).thenReturn(Optional.of(testStudent));
		Mockito.when(reportCardRepository.findByStudentId(reportCard.getId())).thenReturn(reportCard);
		Assertions.assertThrows(ReportCardNotFoundException.class, () -> reportCardService.getReportCardByStudentId(301));
	}

	@Test
	@DisplayName("Test case to delete report card details by report card ID")
	public void testDeleteDetailsById() {
		Mockito.when(reportCardRepository.findById(reportCard.getId())).thenReturn(Optional.of(reportCard));
		reportCardService.deleteDetailsById(200);
		Mockito.verify(reportCardRepository, Mockito.times(1)).deleteById(200);
	}

	@Test
	@DisplayName("Test case to delete report card details with wrong report card ID")
	public void testDeleteDetailsByIdNegative() {
		Mockito.when(reportCardRepository.findById(reportCard.getId())).thenReturn(Optional.of(reportCard));
		Assertions.assertThrows(ReportCardNotFoundException.class, () -> reportCardService.deleteDetailsById(201));
	}

}
