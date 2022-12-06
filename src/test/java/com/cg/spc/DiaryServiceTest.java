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

import com.cg.spc.entities.Diary;
import com.cg.spc.entities.Student;
import com.cg.spc.exceptions.DiaryNotFoundException;
import com.cg.spc.exceptions.StudentNotFoundException;
import com.cg.spc.repositories.IDiaryRepository;
import com.cg.spc.repositories.IStudentRepository;
import com.cg.spc.services.IDiaryService;

@SpringBootTest
public class DiaryServiceTest {

	@Autowired
	private IDiaryService diaryService;

	@MockBean
	private IDiaryRepository diaryRepository;

	@MockBean
	private IStudentRepository studentRepository;

	Diary diary;
	Diary diary2;

	Student student;
	Student student2;

	@BeforeEach
	public void init() {
		diary = new Diary();
		diary.setId(200);
		diary.setGeneratedDate(LocalDate.of(2021, 03, 04));
		diary.setRemark("Good Student");
		student = new Student();
		student.setId(9);
		student.setName("Sparsh");
		student.setDiary(diary);
		diary.setStudent(student);

		diary2 = new Diary();
		diary2.setId(210);
		diary2.setGeneratedDate(LocalDate.of(2021, 04, 06));
		diary2.setRemark("Excellent Student");
		student2 = new Student();
		student2.setId(10);
		student2.setName("Sky");
		student2.setDiary(diary);
		diary2.setStudent(student);

	}

	@Test
	@DisplayName("Test case to add diary by student ID")
	public void testAddDiary() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(diaryRepository.save(diary)).thenReturn(diary);
		assertEquals(diary, diaryService.addDiary(diary, 9));
	}

	@Test
	@DisplayName("Test case to add diary with wrong student ID")
	public void testAddDiaryNegative() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(diaryRepository.save(diary)).thenReturn(diary);
		Assertions.assertThrows(StudentNotFoundException.class, () -> diaryService.addDiary(diary, 19));
	}

	@Test
	@DisplayName("Test case to update diary by student ID")
	public void testUpdateDiary() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(diaryRepository.save(diary)).thenReturn(diary);
		assertEquals(diary, diaryService.updateDiary(diary, 9));
	}

	@Test
	@DisplayName("Test case to update diary with wrong student ID")
	public void testUpdateDiaryNegative() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(diaryRepository.save(diary)).thenReturn(diary);
		Assertions.assertThrows(StudentNotFoundException.class, () -> diaryService.updateDiary(diary, 19));
	}

	@Test
	@DisplayName("Test case to get diary by diary ID")
	public void testGetDiaryById() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(diaryRepository.findById(diary.getId())).thenReturn(Optional.of(diary));
		assertEquals(diary, diaryService.getDiaryById(200));
	}

	@Test
	@DisplayName("Test case to get diary by wrong Diary ID")
	public void testGetDiaryByIdNegative() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(diaryRepository.findById(diary.getId())).thenReturn(Optional.of(diary));
		Assertions.assertThrows(DiaryNotFoundException.class, () -> diaryService.getDiaryById(201));
	}

	@Test
	@DisplayName("Test case to get all diary")
	public void testGetAllDiary() {
		Mockito.when(diaryRepository.findAll()).thenReturn(Stream.of(diary, diary2).collect(Collectors.toList()));
		assertEquals(2, diaryService.getAllDiaryDetails().size());
	}

	@Test
	@DisplayName("Test case for get all diary with wrong diary details")
	public void testGetAllDiaryNegative() {
		Mockito.when(diaryRepository.findAll()).thenReturn(Stream.of(diary, diary2).collect(Collectors.toList()));
		assertNotEquals(3, diaryService.getAllDiaryDetails().size());
	}

	@Test
	@DisplayName("Test case to get diary by student ID")
	public void testGetDiaryByStudent() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(diaryRepository.findByStudentId(9)).thenReturn(diary);
		assertEquals(diary, diaryService.getDiaryByStudentId(9));
	}

	@Test
	@DisplayName("Test case to get diary with wrong student ID")
	public void testGetDiaryByStudentNegative() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(diaryRepository.findByStudentId(9)).thenReturn(diary);
		Assertions.assertThrows(StudentNotFoundException.class, () -> diaryService.getDiaryByStudentId(12));
	}

	@Test
	@DisplayName("Test case to delete details by diary ID")
	public void testDeleteDetailsById() {
		Mockito.when(diaryRepository.findById(diary.getId())).thenReturn(Optional.of(diary));
		diaryService.deleteDiaryById(200);
		Mockito.verify(diaryRepository, Mockito.times(1)).deleteById(200);
	}

	@Test
	@DisplayName("Test case for delete details by wrong diary ID")
	public void testDeleteDetailsByIdNegative() {
		Mockito.when(diaryRepository.findById(diary.getId())).thenReturn(Optional.of(diary));
		Assertions.assertThrows(DiaryNotFoundException.class, () -> diaryService.deleteDiaryById(201));
	}

}
