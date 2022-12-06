package com.cg.spc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.spc.entities.Exam;
import com.cg.spc.entities.Standard;
import com.cg.spc.entities.Subject;
import com.cg.spc.exceptions.DateNotFoundException;
import com.cg.spc.exceptions.ExamNotFoundException;
import com.cg.spc.exceptions.StandardNotFoundException;
import com.cg.spc.repositories.IExamRepository;
import com.cg.spc.repositories.IStandardRepository;
import com.cg.spc.services.IExamService;

@SpringBootTest
public class ExamServiceTest {

	@Autowired
	private IExamService examService;

	@MockBean
	private IExamRepository examRepository;

	@MockBean
	private IStandardRepository standardRepository;

	Exam exam, exam1;
	Standard standard, standard2;
	List<Integer> standardIdListPositive;
	List<Integer> standardIdListNegative;
	List<Standard> standardList;

	@BeforeEach
	public void init() {

		exam = new Exam();
		exam.setDuration("3 hours");
		exam.setExamDate(LocalDate.of(2021, 04, 01));
		exam.setId(100);
		exam.setMarks(100);
		exam.setSubject(Subject.HINDI);

		exam1 = new Exam();
		exam1.setId(503);
		exam1.setDuration("2 Hours");
		exam1.setExamDate(LocalDate.of(2021, 06, 01));
		
		standard = new Standard();
		standard.setClassStrength(80);
		standard.setGrade("III");
		standard.setId(500);

		standard2 = new Standard();
		standard2.setClassStrength(60);
		standard2.setGrade("II");
		standard2.setId(509);

		standardIdListNegative = new ArrayList<Integer>();
		standardIdListNegative.add(510);
		standardIdListNegative.add(501);

		standardIdListPositive = new ArrayList<Integer>();
		standardIdListPositive.add(500);
		standardIdListPositive.add(509);

		standardList = new ArrayList<Standard>();
		standardList.add(standard);

		exam.setStandard(standardList);

	}

	@Test
	@DisplayName("Test case to get exam by Exam ID")
	public void testGetExamById() {
		Mockito.when(examRepository.findById(500)).thenReturn(Optional.of(exam));
		assertEquals(exam, examService.getExamById(500));
	}

	@Test
	@DisplayName("Test case to get exam by wrong Exam Id")
	public void testGetExamByIdNegative() {
		Mockito.when(examRepository.findById(exam.getId())).thenReturn(Optional.of(exam));
		Assertions.assertThrows(ExamNotFoundException.class, () -> examService.getExamById(609));
	}

	@Test
	@DisplayName("Test case to get exam by date")
	public void testGetExamByDate() {
		Mockito.when(examRepository.findAll()).thenReturn(Stream.of(exam, exam1).collect(Collectors.toList()));
		Mockito.when(examRepository.findByExamDate(exam.getExamDate())).thenReturn(exam);
		assertEquals(exam, examService.getExamByDate(LocalDate.of(2021, 04, 01)));
	}

	@Test
	@DisplayName("Test case to get exam by wrong date")
	public void testGetExamByDateNegative() {
		Mockito.when(examRepository.findByExamDate(exam.getExamDate())).thenReturn(exam);
		Assertions.assertThrows(DateNotFoundException.class,
				() -> examService.getExamByDate(LocalDate.of(2021, 06, 01)));
	}

	@Test
	@DisplayName("Test case to get all exam details")
	public void testGetAllExamDetails() {
		Mockito.when(examRepository.findAll()).thenReturn(Stream.of(exam, exam1).collect(Collectors.toList()));
		assertEquals(2, examService.getAllExamDetails().size());
	}

	@Test
	@DisplayName("Test case of get all exam with wrong exam details")
	public void testGetAllExamDetailsNegative() {
		Mockito.when(examRepository.findAll()).thenReturn(Stream.of(exam, exam1).collect(Collectors.toList()));
		assertNotEquals(1, examService.getAllExamDetails().size());
	}

	@Test
	@DisplayName("Test case to add exam by standard ID")
	public void testAddExam() {
		standardIdListPositive = new ArrayList<Integer>();
		standardIdListPositive.add(500);
		Mockito.when(standardRepository.findById(standard.getId())).thenReturn(Optional.of(standard));
		Mockito.when(examRepository.save(exam)).thenReturn(exam);
		assertEquals(exam, examService.addExam(exam, standardIdListPositive));
	}

	@Test
	@DisplayName("Test case to add exam with wrong standard ID")
	public void testAddExamNegative() {
		Mockito.when(standardRepository.findById(standard.getId())).thenReturn(Optional.of(standard));
		Mockito.when(standardRepository.findById(standard2.getId())).thenReturn(Optional.of(standard2));
		Assertions.assertThrows(StandardNotFoundException.class,
				() -> examService.addExam(exam, standardIdListNegative));
	}

	@Test
	@DisplayName("Test case to update exam by standard ID list")
	public void testUpdateExam() {
		Mockito.when(standardRepository.findById(standard.getId())).thenReturn(Optional.of(standard));
		Mockito.when(standardRepository.findById(standard2.getId())).thenReturn(Optional.of(standard2));
		Mockito.when(examRepository.save(exam)).thenReturn(exam);

		assertEquals(exam, examService.addExam(exam, standardIdListPositive));
	}

	@Test
	@DisplayName("Test case to update exam by wrong standard ID list")
	public void testUpdateExamNegative() {
		Mockito.when(standardRepository.findById(standard.getId())).thenReturn(Optional.of(standard));
		Mockito.when(standardRepository.findById(standard2.getId())).thenReturn(Optional.of(standard2));
		Mockito.when(examRepository.save(exam)).thenReturn(exam);
		Assertions.assertThrows(StandardNotFoundException.class,
				() -> examService.addExam(exam, standardIdListNegative));
	}

	@Test
	@DisplayName("Test case to delete by exam ID")
	public void testDeleteExamById() {
		Mockito.when(examRepository.findById(exam.getId())).thenReturn(Optional.of(exam));
		examService.deleteExamById(100);
		Mockito.verify(examRepository, Mockito.times(1)).deleteById(100);
	}

	@Test
	@DisplayName("Test case to delete with wrong exam ID")
	public void testDeleteExamByIdNegative() {
		Mockito.when(examRepository.findById(exam.getId())).thenReturn(Optional.of(exam));
		Assertions.assertThrows(ExamNotFoundException.class, () -> examService.deleteExamById(102));
	}
}