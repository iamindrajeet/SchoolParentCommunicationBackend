package com.cg.spc.services;

import com.cg.spc.entities.Attendance;

public interface IAttendanceService {
	public Attendance addAttendance(Attendance attendance, int studentId);
	public Attendance updateAttendance(Attendance attendance, int studentId);
	public Attendance getAttendanceById(int id);
	public Attendance deleteById(int id);
	public Attendance getAttendanceByStudentId(int id);
}
