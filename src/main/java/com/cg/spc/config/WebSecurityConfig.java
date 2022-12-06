package com.cg.spc.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.cg.spc.services.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

//	@Autowired
//	private UserService myAdminDetailsService;

	@Autowired
	private UserRequestFilter jwtFilter;
	
	@Autowired
	private TeacherRequestFilter jwtTeacherFilter;
	
	@Autowired
	private ParentRequestFilter jwtParentFilter;
	
	@Autowired
	private AccountantRequestFilter jwtAccountantFilter;
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		log.info("configureGlobal");
//		auth.userDetailsService(myAdminDetailsService);
//	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		log.info("authenticationManagerBean");
		return super.authenticationManagerBean();
	}

//	    @Bean
//	    CorsConfigurationSource corsConfigurationSource() {
//	    	CorsConfigurationSource source = new
//	    			CorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//	        return source;
//	    }

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception { 
//		httpSecurity.cors();
//		log.info("configure");
//		httpSecurity.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
////		.antMatchers("/").permitAll()
//				.antMatchers("/hello").permitAll().antMatchers("/login").permitAll().anyRequest().authenticated().and()
//				.exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		httpSecurity.addFilterBefore(userRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		httpSecurity.cors();
		log.info("configure");
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/token").permitAll().antMatchers("/login").permitAll()
		.antMatchers("/login/parent").permitAll().antMatchers("/login/accountant").permitAll().antMatchers("/login/teacher").permitAll()
		.anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.addFilterBefore(jwtTeacherFilter, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.addFilterBefore(jwtParentFilter, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.addFilterBefore(jwtAccountantFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}