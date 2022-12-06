package com.cg.spc.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.spc.entities.Fee;
import com.cg.spc.entities.Student;
import com.cg.spc.exceptions.FeeNotFoundException;
import com.cg.spc.exceptions.StudentNotFoundException;
import com.cg.spc.repositories.IFeeRepository;
import com.cg.spc.repositories.IStudentRepository;


/**
 * 
 * 
 * Implementation class for FeeService
 *
 */

@Service
public class FeeServiceImpl implements IFeeService{

	@Autowired
	private IFeeRepository feeRepository;
	
	@Autowired
	private IStudentRepository studentRepository;
	
	Logger logger = LoggerFactory.getLogger(FeeServiceImpl.class);
	
	/**
	 * @param id
	 * 
	 * @return fee
	 * 
	 * 	- if the fee id matches then fee details will be retrieved.
	 * 
	 */
	@Override
	public Fee getFeeById(int id) {
		logger.info("Fee getFeeById");
		return feeRepository.findById(id).orElseThrow(() -> new FeeNotFoundException());
	}

	/**
	 * @param id, studentId
	 * 
	 * @return fee
	 * 
	 * 	- if the studentId matches then fee details will be retrieved.
	 * 
	 */
	@Override
	public Fee updateFeeDetails(Fee fee, int studentId) {
		logger.info("Fee updateFeeDetails");
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException());
		fee.setStudent(student);
		student.setFee(fee);
		return feeRepository.save(fee);
	}

	/**
	 * @param id
	 * 
	 * @return fee
	 * 
	 * 	- if the fee id matches then fee details will be deleted.
	 * 
	 */
	@Override
	public Fee deleteFeeDetails(int id) {
		logger.info("Fee deleteFeeDetails");
		Fee fee = feeRepository.findById(id).orElseThrow(() -> new FeeNotFoundException());
		feeRepository.deleteById(id);
		return fee;
	}
	
	/**
	 * @return fee
	 * 
	 * 	- all fee details will be retrieved.
	 * 
	 */
	@Override
	public List<Fee> getAllFee() {
		logger.info("Fee getAllFee");
		return feeRepository.findAll();
	}
	
	
	/**
	 * @param fee, studentId
	 * 
	 * @return fee
	 * 
	 * 	- if the fee id matches then fee details will be retrieved.
	 */
	@Override
	public Fee addFeeDetails(Fee fee, int studentId) {
		logger.info("Fee addFeeDetails");
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException());
		fee.setStudent(student);
		student.setFee(fee);
		return feeRepository.save(fee);
	}

	/**
	 * @param fee, studentId
	 * 
	 * @return fee
	 * 
	 * 	- if the fee id matches then fee details will be retrieved.
	 */
	@Override
    public Fee getFeeByStudentId(int id) {
		logger.info("Fee getFeeByStudentId");
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException());
		if(student.getFee() == null) {
			throw new FeeNotFoundException();
		}
        return feeRepository.findByStudentId(id);
    }
}