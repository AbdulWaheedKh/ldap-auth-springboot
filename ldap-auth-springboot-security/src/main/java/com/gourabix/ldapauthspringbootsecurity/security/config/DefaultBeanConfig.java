package com.gourabix.ldapauthspringbootsecurity.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * @author Gourab Sarkar
 *
 */
@Configuration
public class DefaultBeanConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new LdapShaPasswordEncoder();
//	}

}
