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

import com.cg.spc.entities.Fee;
import com.cg.spc.entities.Student;
import com.cg.spc.exceptions.StudentNotFoundException;
import com.cg.spc.exceptions.FeeNotFoundException;
import com.cg.spc.repositories.IFeeRepository;
import com.cg.spc.repositories.IStudentRepository;
import com.cg.spc.services.IFeeService;

@SpringBootTest
public class FeeServiceTest {

	@Autowired
	private IFeeService feeService;

	@MockBean
	private IFeeRepository feeRepository;

	@MockBean
	private IStudentRepository studentRepository;

	Fee fee, fee1;

	Student student, student1;

	@BeforeEach
	public void init() {
		fee = new Fee();
		fee.setId(121);
		fee.setFeeDue(16000);
		fee.setFeePaid(8000);
		student = new Student();
		student.setId(101);
		student.setName("Pankaj");
		fee.setStudent(student);
		student.setFee(fee);

		fee1 = new Fee();
		fee1.setId(131);
		fee1.setFeeDue(18000);
		fee1.setFeePaid(6000);
		student1 = new Student();
		student1.setId(102);
		student1.setName("Robert");
		fee1.setStudent(student1);
		student1.setFee(fee1);
	}

	@Test
	@DisplayName("positive test case for add fee details")
	public void testAddFeeDetails() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(feeRepository.save(fee)).thenReturn(fee);
		assertEquals(fee, feeService.addFeeDetails(fee, 101));
	}

	@Test
	@DisplayName("negative test case for add fee details")
	public void testAddFeeDetailsNegative() {
		Mockito.when(feeRepository.save(fee)).thenReturn(fee);
		Assertions.assertThrows(StudentNotFoundException.class, () -> feeService.addFeeDetails(fee, 201));

	}

	@Test
	@DisplayName("positive test case for update fee details")
	public void testUpdateDetails() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(feeRepository.save(fee)).thenReturn(fee);
		assertEquals(fee, feeService.addFeeDetails(fee, 101));
	}

	@Test
	@DisplayName("negative test case for update fee details")
	public void testUpdateDetailsNegative() {
		Mockito.when(feeRepository.save(fee)).thenReturn(fee);
		Assertions.assertThrows(StudentNotFoundException.class, () -> feeService.addFeeDetails(fee, 201));
	}

	@Test
	@DisplayName("positive test case for get fee by fee ID")
	public void testGetFeeById() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(feeRepository.findById(fee.getId())).thenReturn(Optional.of(fee));
		assertEquals(fee, feeService.getFeeById(121));
	}

	@Test
	@DisplayName("negative test case for get fee by fee ID")
	public void testGetFeeByIdNegative() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(feeRepository.findById(fee.getId())).thenReturn(Optional.of(fee));
		Assertions.assertThrows(FeeNotFoundException.class, () -> feeService.getFeeById(128));
	}

	@Test
	@DisplayName("positive test case for get fee by student ID")
	public void testGetFeeByStudentId() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(feeRepository.findByStudentId(101)).thenReturn(fee);
		assertEquals(fee, feeService.getFeeByStudentId(101));
	}

	@Test
	@DisplayName("positive test case for get fee by student ID")
	public void testGetFeeByStudentIdNegative() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(feeRepository.findByStudentId(101)).thenReturn(fee);
		Assertions.assertThrows(StudentNotFoundException.class, () -> feeService.getFeeByStudentId(1001));
	}

	@Test
	@DisplayName("positive test for getAllFee")
	public void getAllFee() {
		Mockito.when(feeRepository.findAll()).thenReturn(Stream.of(fee,fee1).collect(Collectors.toList()));
		assertEquals(2, feeService.getAllFee().size());
	}
	
	@Test
	@DisplayName("negative test for getAllFee")
	public void getAllFeeNegative() {
		Mockito.when(feeRepository.findAll()).thenReturn(Stream.of(fee,fee1).collect(Collectors.toList()));
		assertNotEquals(3, feeService.getAllFee().size());
	}
	
	@Test
    @DisplayName("positive test case for delete fee details")
    public void testdeleteFeeDetails() {
        Mockito.when(feeRepository.findById(fee.getId())).thenReturn(Optional.of(fee));
        feeService.deleteFeeDetails(121);
        Mockito.verify(feeRepository, Mockito.times(1)).deleteById(121);
    }
	
	@Test
    @DisplayName("negative test case for delete fee details")
    public void testdeleteFeeDetailsNegative() {
        Mockito.when(feeRepository.findById(fee.getId())).thenReturn(Optional.of(fee));
        Assertions.assertThrows(FeeNotFoundException.class, () -> feeService.deleteFeeDetails(1001));
    }

}