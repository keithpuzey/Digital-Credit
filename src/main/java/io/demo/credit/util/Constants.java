package io.demo.credit.util;

import io.demo.credit.util.Patterns;

public class Constants {
	
	
	// Has Role
	public static final String HAS_ROLE_USER				= "hasRole('" + Patterns.ROLE_USER + "')";
	public static final String HAS_ROLE_ADMIN				= "hasRole('" + Patterns.ROLE_ADMIN + "')";
	public static final String HAS_ROLE_API					= "hasRole('" + Patterns.ROLE_API + "')";
	
	// API
	public static final String API_SECRET					= "%C&F)J@NcRfUjXn2r5u8x/A?D(G-KaPd";
	public static final String API_AUTH_HEADER				= "Authorization";
	public static final String API_TOKEN_BEGIN				= "Bearer ";
	
	// Path Variables
	public static final String PATH_VARIABLE_ID				= "id";
	
	// Public resources
	public static final String URI_WEBJARS_RES 				= "/webjars/**";
	public static final String URI_CSS_RES 					= "/css/**";
	public static final String URI_SCSS_RES 				= "/scss/**";
	public static final String URI_FONTS_RES 				= "/fonts/**";
	public static final String URI_JS_RES 					= "/js/**";
	public static final String URI_IMAGES_RES 				= "/images/**";
	public static final String URI_ABOUT_RES 				= "/about/**";
	public static final String URI_CONTACT_RES 				= "/contact/**";
	public static final String URI_ERROR_RES 				= "/error/**";
	public static final String URI_H2_CONSOLE				= "/h2-console/**";
	public static final String URI_SWAGGER_UI				= "/swagger-ui.html";
	public static final String URI_SWAGGER_V2				= "/v2/api-docs";
	public static final String URI_SWAGGER_RES				= "/swagger-resources/**";
	public static final String URI_SWAGGER_CONF				= "/configuration/**";
	public static final String URI_SWAGGER_DOC				= "/swagger-ui.html";
	public static final String URI_API_AUTH					= "/api/v1/auth";
		
	// Authenitcated URI
	public static final String URI_ALL						= "/**";
	public static final String URI_API_ALL					= "/api/**";
	public static final String URI_API_USR_ALL				= "/api/v1/users";
	public static final String URI_API_USR					= "/api/v1/user";
	public static final String URI_API_USR_ID				= "/api/v1/user/{id}";
	public static final String URI_API_USR_PROF				= "/api/v1/user/{id}/profile";
	public static final String URI_API_USR_PROF_CURR		= "/api/v1/user/profile";
	public static final String URI_API_USR_ROLE				= "/api/v1/user/{id}/role";
	public static final String URI_API_USR_ROLE_CURR		= "/api/v1/user/role";
	public static final String URI_API_USR_SET_PASS			= "/api/v1/user/{id}/password";
	public static final String URI_API_USR_CHG_PASS			= "/api/v1/user/password";
	public static final String URI_API_USR_ENABLE			= "/api/v1/user/{id}/state/enable";
	public static final String URI_API_USR_UNEXPIRE			= "/api/v1/user/{id}/state/unexpire";
	public static final String URI_API_USR_UNLOCK			= "/api/v1/user/{id}/state/unlock";
	public static final String URI_API_USR_PASS_UNEXPIRE	= "/api/v1/user/{id}/password/unexpire";

}
