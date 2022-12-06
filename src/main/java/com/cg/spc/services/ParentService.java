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
public class ParentService implements UserDetailsService {

	private final static Logger log = LoggerFactory.getLogger(UserService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadParentByUsername");
		return new User("parent@parent.com", "parent", new ArrayList<>());
	}
}