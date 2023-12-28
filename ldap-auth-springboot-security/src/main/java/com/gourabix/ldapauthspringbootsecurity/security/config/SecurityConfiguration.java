package com.gourabix.ldapauthspringbootsecurity.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

/**
 * 
 * @author Gourab Sarkar
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.anyRequest().fullyAuthenticated()
				.and()
				.formLogin()
				.and()
				.csrf().disable(); // Disable CSRF protection;
	}
	@Bean
	public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {

		ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider =
				new ActiveDirectoryLdapAuthenticationProvider( "cas-dfasdfasdl", "ldap://192.89234809");

		// to parse AD failed credentails error message due to account - expiry,lock, credentialis - expiry,lock
		activeDirectoryLdapAuthenticationProvider.setConvertSubErrorCodesToExceptions(true);
		activeDirectoryLdapAuthenticationProvider.setUseAuthenticationRequestCredentials(true);

		return activeDirectoryLdapAuthenticationProvider;
	}




	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
	}

}
