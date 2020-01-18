package com.newlecture.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityContextConfig 
		extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	/*
	 * @Autowired private AuthenticationSuccessHandler successHandler;
	 */
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		
		AuthenticationSuccessHandler successHandler 
			= new MyHomeRedirectionHandler();
		
		return successHandler;
	}
	
	/*
	<http>
		<intercept-url pattern="/admin/board/notice/reg" access="hasRole('ROLE_ADMIN')"/>
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		<form-login 
			login-page="/member/login"
			login-processing-url="/member/login"  
			default-target-url="/index"/>
		
		<logout
			logout-url="/member/logout" 
			logout-success-url="/index"/>
		
		<csrf disabled="true"/>
	</http>
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/teacher/**").hasRole("TEACHER")
			.antMatchers("/student/**").hasRole("STUDENT")
			.antMatchers("/member/home").authenticated()
			.and()
		.formLogin()
			.loginPage("/member/login")
			.loginProcessingUrl("/member/login")
			.defaultSuccessUrl("/index")
			.successHandler(successHandler())
			.and()
		.logout()
			.logoutUrl("/member/logout")
			.logoutSuccessUrl("/index")
			.and()
		.csrf()
			.disable();
		
	}
	
	/*
	<authentication-manager>
		<authentication-provider>
			<password-encoder hash="bcrypt"/>
			<jdbc-user-service 
				data-source-ref="dataSource"
				users-by-username-query="SELECT id, pwd password, 1 disabled FROM MEMBER WHERE id=?"
				authorities-by-username-query="SELECT MEMBER_ID ID, ROLE_ID roleId FROM MEMBER_ROLE WHERE MEMBER_ID=?"
			/>
			<!-- <user-service>
				<user name="newlec" password="{noop}111" authorities="ROLE_ADMIN,ROLE_TEACHER,ROLE_STUDENT"/>
				<user name="dragon" password="{noop}111" authorities="ROLE_STUDENT"/>
			</user-service> -->
		</authentication-provider>
	</authentication-manager>
	 */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		auth
			.inMemoryAuthentication()
				.withUser("newlec")
					.password("{noop}111")
					.roles("ROLE_ADMIN,ROLE_TEACHER,ROLE_STUDENT")
					.and()
				.withUser("dragon")
					.password("{noop}111")
					.roles("ROLE_STUDENT");*/
		
		auth
			.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("SELECT id, pwd password, 1 disabled FROM Member WHERE id=?")
				.authoritiesByUsernameQuery("SELECT memberId id, roleId roleId FROM MemberRole WHERE memberId=?")
				.passwordEncoder(new BCryptPasswordEncoder());
	}
}





