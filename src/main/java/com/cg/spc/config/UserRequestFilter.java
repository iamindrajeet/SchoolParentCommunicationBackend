package com.cg.spc.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cg.spc.services.UserService;
import com.cg.spc.helper.JwtUtil;

@Component
public class UserRequestFilter extends OncePerRequestFilter {

	private final Logger log = LoggerFactory.getLogger(UserRequestFilter.class);

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		log.info("doFilterInternal");
		String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			log.info("authorizationHeader is not null");
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			log.info("username is null");
			UserDetails userDetails = this.userService.loadUserByUsername(username);

			if (jwtUtil.validateToken(jwt, userDetails)) {
				response.addHeader("Access-Control-Allow-Origin", "*");
				response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
				response.setHeader("Access-Control-Allow-Credentials", "true");
				response.setHeader("Access-Control-Allow-Headers",
						"content-type, x-gwt-module-base, x-gwt-permutation, clientid, longpush");
				response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
				response.setHeader("Access-Control-Allow-Credentials", "true");
				response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
				response.setHeader("Access-Control-Max-Age", "3600");
				response.setHeader("Access-Control-Allow-Headers",
						"Content-Type, Accept, X-Requested-With, remember-me");
				log.info("userDetails extracted");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		log.info("UserPassAuth");
		chain.doFilter(request, response);
		log.info("chain");
	}
}