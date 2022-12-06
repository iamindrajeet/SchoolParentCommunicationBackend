package com.cg.spc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.spc.entities.Attendance;
import com.cg.spc.entities.Student;
import com.cg.spc.exceptions.StudentNotFoundException;
import com.cg.spc.exceptions.AttendanceNotFoundException;
import com.cg.spc.exceptions.FeeNotFoundException;
import com.cg.spc.repositories.IAttendanceRepository;
import com.cg.spc.repositories.IStudentRepository;
import com.cg.spc.services.IAttendanceService;

@SpringBootTest
public class AttendanceServiceTest {

	@Autowired
	private IAttendanceService attendanceService;

	@MockBean
	private IAttendanceRepository attendanceRepository;
	
	@MockBean
	private IStudentRepository studentRepository;

	Attendance attendance;
	
	Student student;

	@BeforeEach
	public void init() {
		attendance = new Attendance();
		attendance.setId(301);
		attendance.setAttendanceDate(LocalDate.of(2021, 03, 19));
		attendance.setPresent(true);
		student = new Student();
		student.setId(9);
		student.setAttendance(attendance);
		attendance.setStudent(student);
	}

	@Test
	@DisplayName("Test case to add attendance")
	public void testAddAttendance() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(attendanceRepository.save(attendance)).thenReturn(attendance);
		assertEquals(attendance, attendanceService.addAttendance(attendance, 9));
	}
	
	@Test
	@DisplayName("Test case to add attendance with wrong student ID")
	public void testAddAttendancetNegative() {
		Mockito.when(attendanceRepository.save(attendance)).thenReturn(attendance);
		Assertions.assertThrows(StudentNotFoundException.class, () -> attendanceService.addAttendance(attendance, 100));
	}
	
	@Test
	@DisplayName("Test case to update attendance")
	public void testUpdateAttendance() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(attendanceRepository.save(attendance)).thenReturn(attendance);
		assertEquals(attendance, attendanceService.updateAttendance(attendance, 9));
	}
	
	@Test
	@DisplayName("Test case to update attendance with wrong student ID")
	public void testUpdateAttendanceNegative() {
		Mockito.when(attendanceRepository.save(attendance)).thenReturn(attendance);
		Assertions.assertThrows(StudentNotFoundException.class, () -> attendanceService.updateAttendance(attendance, 100));
	}
	
	@Test
	@DisplayName("Test case to get attendance by attendance ID")
	public void testGetAttendanceById() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(attendanceRepository.findById(attendance.getId())).thenReturn(Optional.of(attendance));
		assertEquals(attendance, attendanceService.getAttendanceById(301));
	}
	
	@Test
	@DisplayName("Test case to get attendance by wrong attendance ID")
	public void testGetAttendanceByIdNegative() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(attendanceRepository.findById(attendance.getId())).thenReturn(Optional.of(attendance));
		Assertions.assertThrows(AttendanceNotFoundException.class, () -> attendanceService.getAttendanceById(128));
	}
	
	@Test
	@DisplayName("Test case to get attendance by student ID")
	public void testGetAttendanceByStudentId() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(attendanceRepository.findByStudentId(9)).thenReturn(attendance);
		assertEquals(attendance, attendanceService.getAttendanceByStudentId(9));
	}
	
	@Test
	@DisplayName("Test case to get attendance by wrong student ID")
	public void testGetAttendanceByStudentIdNegative() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Mockito.when(attendanceRepository.findByStudentId(9)).thenReturn(attendance);
		Assertions.assertThrows(StudentNotFoundException.class, () -> attendanceService.getAttendanceByStudentId(1001));
	}
	
	@Test
    @DisplayName("Test case to delete attendance by attendance ID")
    public void testdeleteFeeDetails() {
        Mockito.when(attendanceRepository.findById(attendance.getId())).thenReturn(Optional.of(attendance));
        attendanceService.deleteById(301);
        Mockito.verify(attendanceRepository, Mockito.times(1)).deleteById(301);
    }
	
	@Test
    @DisplayName("Test case to delete attendance with wrong attendance ID")
    public void testdeleteFeeDetailsNegative() {
        Mockito.when(attendanceRepository.findById(attendance.getId())).thenReturn(Optional.of(attendance));
        Assertions.assertThrows(AttendanceNotFoundException.class, () -> attendanceService.deleteById(1001));
    }


}