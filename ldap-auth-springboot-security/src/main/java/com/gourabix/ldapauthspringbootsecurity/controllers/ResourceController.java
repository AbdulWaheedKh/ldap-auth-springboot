package com.gourabix.ldapauthspringbootsecurity.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;


/**
 * 
 * @author Gourab Sarkar
 *
 */
@RestController
@RequestMapping("/secure/resource/v1")
public class ResourceController {

	private static final Logger LOGGER = LogManager.getLogger(ResourceController.class);

	@GetMapping("/userGroup")
	public ResponseEntity<String> findUserGroup() {
		LOGGER.info("Executing findUserGroup()...");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LOGGER.info(auth.getName() + " authenticated successfully!");
		return new ResponseEntity<String>("Welcome to infinity network, " + auth.getName() + "!", HttpStatus.OK);
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "hello world";
	}
	@GetMapping("/user")
	public Authentication getLoggedUserDeatil() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//get username
		String username = auth.getName();
// concat list of authorities to single string seperated by comma
		String authorityString = auth
				.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
// check if the user have authority -roleA
		String role = "role_A";
		boolean isCurrentUserInRole = auth
				.getAuthorities()
				.stream()
				.anyMatch(role::equals);
//return Authentication object
		return auth;
	}

}
