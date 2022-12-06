package com.cg.spc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.spc.entities.Standard;
import com.cg.spc.entities.Student;
import com.cg.spc.exceptions.StandardNotFoundException;
import com.cg.spc.exceptions.StudentNotFoundException;
import com.cg.spc.repositories.IStandardRepository;
import com.cg.spc.repositories.IStudentRepository;
import com.cg.spc.services.IStandardService;

@SpringBootTest
public class StandardServiceTest {

	@Autowired
	private IStandardService standardService;

	@MockBean
	private IStandardRepository standardRepository;
	
	@MockBean
	private IStudentRepository studentRepository;

	Standard standard;
	Standard standard2;
	Student student;
	List<Integer> studentIdList;
	List<Student> studentList;
	
	@BeforeEach
	public void init() {
		standard = new Standard();
		standard.setGrade("I");
		standard.setClassStrength(65);
		standard.setId(500);

		standard2 = new Standard();
		standard2.setGrade("III");
		standard2.setClassStrength(80);

		student = new Student();
		student.setId(101);
		student.setName("Venu Gopal Rao");
		
		studentIdList = new ArrayList<Integer>();
		studentIdList.add(101);
		
		studentList = new ArrayList<Student>();
		studentList.add(student);
		standard.setStudentList(studentList);
	}

	@Test
	@DisplayName("Test case to update standard by student ID")
	public void testUpdateStandard() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(standardRepository.save(standard)).thenReturn(standard);
		assertEquals(standard, standardService.updateDetails(standard,studentIdList));
	}

	@Test
	@DisplayName("Test case to update standard with wrong student ID")
	public void testUpdateStandardNegative() {
		studentIdList.add(102);
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(standardRepository.save(standard)).thenReturn(standard);
		Assertions.assertThrows(StudentNotFoundException.class,
				() -> standardService.updateDetails(standard, studentIdList));
	}

	@Test
	@DisplayName("Test case to add standard")
	public void testAddStandard() {
		Mockito.when(standardRepository.save(standard)).thenReturn(standard);
		assertEquals(standard, standardService.addDetails(standard));
	}

	@Test
	@DisplayName("Test case to add standard with wrong details")
	public void testAddStandardNegative() {
		Mockito.when(standardRepository.save(standard2)).thenReturn(standard2);
		assertNotEquals(standard2, standardService.addDetails(standard));
	}

	@Test
	@DisplayName("Test case to get standard by standard ID")
	public void testGetStandardById() {
		Mockito.when(standardRepository.findById(standard.getId())).thenReturn(Optional.of(standard));
		assertEquals(standard, standardService.getDetailsById(500));
	}

	@Test
	@DisplayName("Test case to get standard by wrng standard ID")
	public void testGetStandardByIdNegative() {
		Mockito.when(standardRepository.findById(standard.getId())).thenReturn(Optional.of(standard));
		Assertions.assertThrows(StandardNotFoundException.class, () -> standardService.getDetailsById(501));
	}
	
	@Test
	@DisplayName("Test case to delete standard details by standard ID")
	public void testDeleteDetailsById()
	{
		Mockito.when(standardRepository.findById(standard.getId())).thenReturn(Optional.of(standard));
		standardService.deleteDetailsById(500);
		Mockito.verify(standardRepository, Mockito.times(1)).deleteById(500);
	}
	
	@Test
	@DisplayName("Test case to delete standard details with wrong standard ID")
	public void testDeleteDetailsByIdNegative()
	{
		Mockito.when(standardRepository.findById(standard.getId())).thenReturn(Optional.of(standard));
		Assertions.assertThrows(StandardNotFoundException.class, ()->standardService.deleteDetailsById(501));
	}
}
