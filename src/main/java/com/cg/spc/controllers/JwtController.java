package com.cg.spc.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spc.entities.AppUser;
import com.cg.spc.helper.JwtUtil;
import com.cg.spc.model.JwtRequest;
import com.cg.spc.model.JwtResponse;
import com.cg.spc.services.AccountantService;
import com.cg.spc.services.ParentService;
import com.cg.spc.services.TeacherService;
import com.cg.spc.services.UserService;
import org.slf4j.LoggerFactory;

//@CrossOrigin(origins = "http://localhost:4201")
@CrossOrigin(origins = "*")
@RestController
public class JwtController {

	private final Logger log = LoggerFactory.getLogger(JwtController.class);

	@Autowired
	private UserService userDetailsService;
	
	@Autowired
	private ParentService parentService;
	
	@Autowired
	private AccountantService accountantService;
	
	@Autowired
	private TeacherService teacherService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

		System.out.println(jwtRequest);

		try {

			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());

		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT " + token);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/login") // 1
	public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) {
		String token="";
		log.info("login");
		if (jwtRequest.getUsername().equals(userDetailsService.loadUserByUsername(jwtRequest.getUsername()).getUsername())
				&& jwtRequest.getPassword().equals(userDetailsService.loadUserByUsername(null).getPassword())) {
			log.info("user authenticated");
			//return jwtUtil.generateToken(userDetailsService.loadUserByUsername(myUser.getUsername()));
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
			token = this.jwtUtil.generateToken(userDetails);
			//return ResponseEntity.ok(new JwtResponse(token));
		} 
		return ResponseEntity.ok(new JwtResponse(token));
//		else {
//			return "thisIsNotTheValidToken";
//		}

	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/login/parent") // 1
	public ResponseEntity<?> loginParent(@RequestBody JwtRequest jwtRequest) {
		String token="";
		log.info("login");
		if (jwtRequest.getUsername().equals(parentService.loadUserByUsername(jwtRequest.getUsername()).getUsername())
				&& jwtRequest.getPassword().equals(parentService.loadUserByUsername(null).getPassword())) {
			log.info("user authenticated");
			UserDetails userDetails = this.parentService.loadUserByUsername(jwtRequest.getUsername());
			token = this.jwtUtil.generateToken(userDetails);
		} 
		return ResponseEntity.ok(new JwtResponse(token));
		//else {
//			return "thisIsNotTheValidToken";
//		}

	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/login/accountant") // 1
	public ResponseEntity<?> loginAccountant(@RequestBody JwtRequest jwtRequest) {
		String token="";
		log.info("login");
		if (jwtRequest.getUsername().equals(accountantService.loadUserByUsername(jwtRequest.getUsername()).getUsername())
				&& jwtRequest.getPassword().equals(accountantService.loadUserByUsername(null).getPassword())) {
			log.info("user authenticated");
			//return jwtUtil.generateToken(accountantService.loadUserByUsername(jwtRequest.getUsername()));
			UserDetails userDetails = this.accountantService.loadUserByUsername(jwtRequest.getUsername());
			token = this.jwtUtil.generateToken(userDetails);
		} 
		return ResponseEntity.ok(new JwtResponse(token));
//		else {
//			return "thisIsNotTheValidToken";
//		}

	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/login/teacher") // 1
	public ResponseEntity<?> loginTeacher(@RequestBody JwtRequest jwtRequest) {
		String token="";
		log.info("login");
		if (jwtRequest.getUsername().equals(teacherService.loadUserByUsername(jwtRequest.getUsername()).getUsername())
				&& jwtRequest.getPassword().equals(teacherService.loadUserByUsername(null).getPassword())) {
			log.info("user authenticated");
			//return jwtUtil.generateToken(teacherService.loadUserByUsername(jwtRequest.getUsername()));
			UserDetails userDetails = this.teacherService.loadUserByUsername(jwtRequest.getUsername());
			token = this.jwtUtil.generateToken(userDetails);
		} 
		return ResponseEntity.ok(new JwtResponse(token));
//		else {
//			return "thisIsNotTheValidToken";
//		}

	}
}