package com.cg.spc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import com.cg.spc.entities.Concern;
import com.cg.spc.entities.ConcernType;
import com.cg.spc.entities.Parent;
import com.cg.spc.exceptions.ConcernResolvedException;
import com.cg.spc.exceptions.ParentNotFoundException;
import com.cg.spc.repositories.IConcernRepository;
import com.cg.spc.repositories.IParentRepository;
import com.cg.spc.services.IConcernService;

@SpringBootTest
public class ConcernServiceTest {

	@Autowired
	private IConcernService concernService;

	@MockBean
	private IConcernRepository concernRepository;

	@MockBean
	private IParentRepository parentRepository;

	Concern concern;
	Concern concern2;

	Parent parent;
	Parent parent2;

	@BeforeEach
	public void init() {
		concern = new Concern();
		concern.setConcern("child getting low marks");
		concern.setConcernType(ConcernType.ACADEMIC);
		parent = new Parent();
		parent.setId(500);
		concern.setParent(parent);
		
		concern2 = new Concern();
		concern2.setConcern("Wrong Fee Details");
		concern2.setConcernType(ConcernType.FEES);
		parent2 = new Parent();
		parent2.setId(501);
		concern2.setParent(parent2);
	}

	@Test
	@DisplayName("Test case to add concern by parent ID")
	public void testAddConcern() {
		Mockito.when(parentRepository.findById(500)).thenReturn(Optional.of(parent));
		Mockito.when(concernRepository.save(concern)).thenReturn(concern);
		assertEquals("child getting low marks", concernService.addConcern(concern, 500).getConcern());
	}

	@Test
	@DisplayName("Test case to add concern with wrong parent ID")
	public void testAddConcernNegative() {
		Mockito.when(parentRepository.findById(500)).thenReturn(Optional.of(parent));
		Mockito.when(concernRepository.save(concern)).thenReturn(concern);
		Assertions.assertThrows(ParentNotFoundException.class, () -> concernService.updateConcern(concern, 501));
	}

	@Test
	@DisplayName("Test case for update concern by parent ID")
	public void testUpdateConcern() {
		concern.setConcern("Wrong Fee Details");
		concern.setConcernType(ConcernType.FEES);
		Mockito.when(parentRepository.findById(500)).thenReturn(Optional.of(parent));
		Mockito.when(concernRepository.save(concern)).thenReturn(concern);
		assertEquals("Wrong Fee Details", concernService.updateConcern(concern, 500).getConcern());
	}

	@Test
	@DisplayName("Test case for update concern with wrong parent ID")
	public void testUpdateConcernNegative() {
		Mockito.when(parentRepository.findById(500)).thenReturn(Optional.of(parent));
		Mockito.when(concernRepository.save(concern)).thenReturn(concern);
		Assertions.assertThrows(ParentNotFoundException.class, () -> concernService.updateConcern(concern, 501));
	}

	@Test
	@DisplayName("Test case to get all concerns")
	public void testGetAllConcerns() {
		Mockito.when(concernRepository.findAll()).thenReturn(Stream.of(concern,concern2).collect(Collectors.toList()));
		assertEquals(2, concernService.getAllConcerns().size());
	}
	
	@Test
	@DisplayName("Test case to get all concerns with wrong concern details")
	public void testGetAllConcernsNegative() {
		Mockito.when(concernRepository.findAll()).thenReturn(Stream.of(concern,concern2).collect(Collectors.toList()));
		assertNotEquals(3, concernService.getAllConcerns().size());
	}
	
	@Test
	@DisplayName("Test case to delete by concern ID")
	public void testDeleteById()
	{
		Mockito.when(concernRepository.findById(concern.getId())).thenReturn(Optional.of(concern));
		concernService.deleteById(concern.getId());
		Mockito.verify(concernRepository, Mockito.times(1)).deleteById(concern.getId());
	}
	
	@Test
	@DisplayName("Test case to delete by wrong concern ID")
	public void testDeleteByIdNegative()
	{
		Mockito.when(concernRepository.findById(concern.getId())).thenReturn(Optional.of(concern));
		Assertions.assertThrows(ConcernResolvedException.class,()-> concernService.deleteById(200));
	}
}