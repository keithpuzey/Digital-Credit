package io.demo.credit.config;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.demo.credit.security.JwtTokenFilterConfigurer;
import io.demo.credit.security.JwtTokenProvider;
import io.demo.credit.util.Constants;
import io.demo.credit.util.Patterns;
import io.demo.credit.service.UserSecurityService;


@Configuration
@EnableWebSecurity(debug=false)
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	private static final String[] PUBLIC = {
			
			Constants.URI_WEBJARS_RES, 
	    	Constants.URI_CSS_RES, 
	    	Constants.URI_SCSS_RES, 
	    	Constants.URI_FONTS_RES, 
	    	Constants.URI_JS_RES, 
	    	Constants.URI_IMAGES_RES, 
	    	Constants.URI_ABOUT_RES, 
	    	Constants.URI_CONTACT_RES, 
	    	Constants.URI_ERROR_RES,
	    	Constants.URI_H2_CONSOLE,
	    	Constants.URI_SWAGGER_UI,
	    	Constants.URI_SWAGGER_V2,
	    	Constants.URI_SWAGGER_RES,
	    	Constants.URI_SWAGGER_CONF,
	    	Constants.URI_SWAGGER_DOC
	};
	
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
	}
  
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
  
	protected void configure(HttpSecurity http) throws Exception {
    
		http.csrf().disable()
		.exceptionHandling().accessDeniedPage(Constants.URI_API_AUTH)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.antMatcher("/**")
		.authorizeRequests()
			.antMatchers(PUBLIC).permitAll()
			.antMatchers(HttpMethod.POST, Constants.URI_API_AUTH).permitAll()
			.anyRequest()
			.hasRole(Patterns.ROLE_API)
			.and()
			.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    
	
		// Needed to support the h2-console interface
		http.headers().frameOptions().disable();
  }
  
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(encoder());
	}
}