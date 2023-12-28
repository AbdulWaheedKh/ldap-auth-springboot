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
				new ActiveDirectoryLdapAuthenticationProvider( "test.user@cas-somalia.local", "ldap://192.168.36.69");

		// to parse AD failed credentails error message due to account - expiry,lock, credentialis - expiry,lock
		activeDirectoryLdapAuthenticationProvider.setConvertSubErrorCodesToExceptions(true);

		return activeDirectoryLdapAuthenticationProvider;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
	}


//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups").contextSource()
//				.url("ldap://localhost:8349/dc=springframework,dc=org").and().passwordCompare()
//				.passwordEncoder(passwordEncoder).passwordAttribute("userPassword");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin().and().httpBasic().and().logout();
//	}








//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.ldapAuthentication().userDnPatterns("uid={0},ou=users").groupSearchBase("ou=users").contextSource()
//				.url("ldap://localhost:10389/dc=example,dc=com").and().passwordCompare()
//				.passwordEncoder(passwordEncoder).passwordAttribute("userPassword");
//	}





//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin().and().httpBasic().and().logout();
//	}

}
