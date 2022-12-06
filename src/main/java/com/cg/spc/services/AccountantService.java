package com.cg.spc.services;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class AccountantService implements UserDetailsService {

	private final static Logger log = LoggerFactory.getLogger(UserService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadAccountantByUsername");
		return new User("accountant@accountant.com", "accountant", new ArrayList<>());
	}
}