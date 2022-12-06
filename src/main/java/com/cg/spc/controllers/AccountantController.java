package com.cg.spc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spc.entities.Fee;
import com.cg.spc.services.IFeeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/accountant")
public class AccountantController {

	@Autowired
	private IFeeService feeService;
	

	/**
	 * 
	 * adding fee details by using PostMapping
	 * 
	 */

	@PostMapping("/fee/add")
	public ResponseEntity<Fee> addFeeDetails(@RequestBody Fee fee, @RequestParam int studentId) {
		return new ResponseEntity<Fee>(feeService.addFeeDetails(fee, studentId), HttpStatus.CREATED);
	}
	
	/**
	 * 
	 * updating fee details by using PutMapping
	 * 
	 */

	@PutMapping("/fee/update")
	public ResponseEntity<Fee> updateFeeDetails(@RequestBody Fee fee, @RequestParam int studentId) {
		return new ResponseEntity<Fee>(feeService.updateFeeDetails(fee, studentId), HttpStatus.OK);
	}

}
