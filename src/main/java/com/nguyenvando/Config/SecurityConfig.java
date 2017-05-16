package com.nguyenvando.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("loginService")
	UserDetailsService loginService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());

	}
	
/*	 @Override
     protected void configure(HttpSecurity http) throws Exception {
             http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
     }

     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
             auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
     }
*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
//		http.authorizeRequests()
//		        .antMatchers("/", "/*.jsp").permitAll()
//		        .antMatchers("/admin/login").permitAll()
//		        .antMatchers("/user/login").permitAll()
//		        .antMatchers("/student/**").hasRole("STUDENT")
//		        .antMatchers("/teacher/**").hasRole("TEACHER")
//		        .antMatchers("/admin/**").hasRole("ADMIN")
//		        .and().formLogin()
//		        .usernameParameter("username")
//		        .passwordParameter("password")
//		        .and().csrf();
		
		http.authorizeRequests().antMatchers("/", "/login").access("permitAll");
		
		
		http.authorizeRequests().antMatchers("/admin/**").access("hasAuthority('ADMIN')");
		
		http.authorizeRequests().antMatchers("/student/**").access("hasAuthority('STUDENT')");
		
		http.authorizeRequests().antMatchers("/teacher/**").access("hasAuthority('TEACHER')");
		
		
		http.authorizeRequests().and().formLogin().loginProcessingUrl("/login").loginPage("/login")
		.defaultSuccessUrl("/loginSuccessFull")
		.failureUrl("/login?error")
		.usernameParameter("username")
		.passwordParameter("password")
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
		.and();//.csrf();
		
//		http.authorizeRequests().antMatchers("/admin/**")
//			.access("hasRole('ADMIN')").and().formLogin()
//			.loginPage("/login").failureUrl("/login?error")
//				.usernameParameter("username")
//				.passwordParameter("password")
//				.and().logout().logoutSuccessUrl("/login?logout")
//				.and().csrf()
//				.and().exceptionHandling().accessDeniedPage("/403");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}