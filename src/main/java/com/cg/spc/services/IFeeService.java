package com.cg.spc.services;

import com.cg.spc.entities.Fee;

import java.util.List;

public interface IFeeService {

	public Fee getFeeById(int id);
	public Fee updateFeeDetails(Fee fee, int studentId);
	public Fee deleteFeeDetails(int id);
	public List<Fee> getAllFee();
	public Fee addFeeDetails(Fee fee, int studentId);
	public Fee getFeeByStudentId(int id);
}
