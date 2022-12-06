package com.cg.spc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import com.cg.spc.entities.Parent;
import com.cg.spc.entities.Student;
import com.cg.spc.exceptions.ParentNotFoundException;
import com.cg.spc.exceptions.StudentNotFoundException;
import com.cg.spc.repositories.IParentRepository;
import com.cg.spc.repositories.IStudentRepository;
import com.cg.spc.services.IParentService;

@SpringBootTest
public class ParentServiceTest {

	@Autowired
	private IParentService parentService;

	@MockBean
	private IParentRepository parentRepository;
	
	@MockBean
	private IStudentRepository studentRepository;
	
	Student student;
	Parent parent;
	List<Integer> studentIdList;
	List<Student> studentList;
	
	@BeforeEach
	public void init() {
		parent = new Parent();
		parent.setId(101);
		parent.setName("Elon Musk");
		parent.setContact("9876543210");
		student = new Student();
		student.setId(401);
		student.setName("X Æ A-Xii");
		studentIdList = new ArrayList<>();
		studentIdList.add(401);
		studentList = new ArrayList<Student>();
		studentList.add(student);
		parent.setStudent(studentList);
	}

	@Test
	@DisplayName("Test case to add parent")
	public void testAddParent() {
		Mockito.when(parentRepository.save(parent)).thenReturn(parent);
		assertEquals("Elon Musk", parentService.addParentDetails(parent).getName());
	}

	@Test
	@DisplayName("Test case to add parent with wrong details")
	public void negativeTestAddParent() {
		Mockito.when(parentRepository.save(parent)).thenReturn(parent);
		assertNotEquals("Elon", parentService.addParentDetails(parent).getName());
	}

	@Test
	@DisplayName("Test case to update parent by student ID")
	public void testUpdateParentDetails() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(parentRepository.save(parent)).thenReturn(parent);
		assertEquals(parent, parentService.updateParentDetails(parent, studentIdList));
	}
	
	@Test
	@DisplayName("Test case to update parent with wrong student ID")
	public void testUpdateParentDetailsNegative() {
		studentIdList.add(402);
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(parentRepository.save(parent)).thenReturn(parent);
		Assertions.assertThrows(StudentNotFoundException.class, () -> parentService.updateParentDetails(parent, studentIdList));
	}

	@Test
	@DisplayName("Test case to get all parent details")
	public void testGetAllParent() {
		Mockito.when(parentRepository.findAll()).thenReturn(Stream.of(parent).collect(Collectors.toList()));
		assertEquals(1, parentService.getAllParent().size());
	}
	
	@Test
	@DisplayName("Test case to get all parent with wrong details")
	public void testGetAllParentNegative() {
		Mockito.when(parentRepository.findAll()).thenReturn(Stream.of(parent).collect(Collectors.toList()));
		assertNotEquals(2, parentService.getAllParent().size());
	}
	
	@Test
	@DisplayName("Test case to get parent by parent ID")
	public void testGetParentById() {
		Mockito.when(parentRepository.findById(parent.getId())).thenReturn(Optional.of(parent));
		assertEquals(parent, parentService.getParentById(101));
	}
	
	@Test
	@DisplayName("Test case to get parent with wrong parent ID")
	public void testGetParentByIdNegative() {
		Mockito.when(parentRepository.findById(parent.getId())).thenReturn(Optional.of(parent));
		Assertions.assertThrows(ParentNotFoundException.class, () -> parentService.getParentById(102));
	}
	
	@Test
	@DisplayName("Test case to delete parent details by parent ID")
	public void testDeleteParentDetails() {
		Mockito.when(parentRepository.findById(parent.getId())).thenReturn(Optional.of(parent));
		parentService.deleteParentDetails(101);
		Mockito.verify(parentRepository, Mockito.times(1)).deleteById(101);
	}
	
	@Test
	@DisplayName("Test case to delete parent details with wrong parent ID")
	public void testDeleteParentDetailsNegative() {
		Mockito.when(parentRepository.findById(parent.getId())).thenReturn(Optional.of(parent));
		Assertions.assertThrows(ParentNotFoundException.class, () -> parentService.deleteParentDetails(102));
	}

}