package org.prj.arachne.configuration;

import org.prj.arachne.application.MemberAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Profile({"dev","product"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	MemberAuthenticationService memberService;


	@Bean 
	public HttpSessionStrategy httpSessionStrategy() { 
		return new HeaderHttpSessionStrategy(); 
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
			
			.and()
			
			.exceptionHandling().accessDeniedPage("/403")
			.and()

			
			
			.authorizeRequests()


				//.antMatchers("/**").permitAll()
				.antMatchers(
						HttpMethod.GET,
						"/v2/api-docs","/swagger-ui.html", "/webjars/**", "favicon.ico"
				).permitAll()
				.antMatchers("/aaa").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/api").permitAll()
				.antMatchers("/api/v2/api-docs").permitAll()
				.antMatchers("/v2/api-docs").permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/api/swagger-ui.html").permitAll()

				.antMatchers("/auth/authorize").permitAll()
				.antMatchers("/test/**").permitAll()
				.antMatchers(HttpMethod.POST,"/api/v1/members").anonymous() /* 회원가입 */
				.antMatchers("/403").permitAll()
				
				
				
					.antMatchers("/api/document").permitAll()
					.antMatchers("/user").permitAll()
					.antMatchers("/admin").hasAuthority("ADMIN")
					.antMatchers("/api/**").hasAuthority("NORMAL_USER")
					.anyRequest().authenticated()

				
			.and()
			
			.logout();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService).passwordEncoder(this.passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	@Override
	  public void configure(WebSecurity web) throws Exception {
	      //spring security 제외 경로설정 
		  web.ignoring()
		  .antMatchers("/static/**")
		  .antMatchers("/error/**")
		  .antMatchers("/swagger-ui.html")
		  .antMatchers("/webjars/**")
		  .antMatchers("/swagger-resources/**")
		  .antMatchers("/swagger-resources")
		  .antMatchers("/v2/api-docs");
	  }
	
	
	

	
}
