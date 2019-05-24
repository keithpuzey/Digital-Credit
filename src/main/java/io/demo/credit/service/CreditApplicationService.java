package io.demo.credit.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.demo.credit.model.CreditApplication;
import io.demo.credit.model.UserProfile;
import io.demo.credit.model.security.Role;
import io.demo.credit.model.security.Users;
import io.demo.credit.repository.CreditApplicationRepository;

@Service
@Transactional
public class CreditApplicationService {

	private static final Logger LOG = LoggerFactory.getLogger(CreditApplicationService.class);
	
	@Autowired
	private CreditApplicationRepository creditApplicationRepository;
	
	@Autowired
	private UserService userService;
	
	/*
	 * Submit Credit Application
	 */
	public void submitCreditApplication (CreditApplication app) {
		
		LOG.debug("Credit Application Service -> New Application Submitted");
	
		app.setApplicationStatus(CreditApplication.APP_STATUS_ACCEPTED);
		
		// Set the Application Date
		app.setApplicationDate(new Date());
		
		// save the application
		creditApplicationRepository.save(app);
	}
	
	/*
	 * Process Credit Application
	 */
	public void processCreditApplication (CreditApplication app) {
		
		// Process User Data
		processUserData(app);
		
		// TDDO
		// If the user is defined, check to see if they already have an account
		// If the user has an account, then reject the application
		
		
		// Determine criteria from Application to determine level of acceptance. i.e. analogous to credit rating
		// use criteria to determine accept or reject of application
		
		
	}
	
	/*
	 * Determine existing user or a new user
	 */
	private void processUserData (CreditApplication app) {
		
		Users user;
		boolean bDenied = false;
		String deniedMsg = "";
		
		
		// Check to see if the user already exists
		if (userService.checkEmailAdressExists(app.getEmailAddress()) || userService.checkSsnExists(app.getSsn())) { 
			
			// Get existing user
			user = userService.findByUsername(app.getEmailAddress());
			
			// Check SSN matches the provided SSN on the application
			if (!user.getUserProfile().getSsn().equals(userService.normalizeSSNFormat(app.getSsn()))) {
				
				deniedMsg = "Email Address and Social Security Number provided are not consistent and in direct conflict "
						  + "with existing accounts in the pur system. Contact customer service for further assistance.";
				
				LOG.debug(deniedMsg);
				
				bDenied = true;
			}
			
			LOG.debug("Process Credit Application -> Get existing user with email '" + user.getUserProfile().getEmailAddress() + "'");
			
		}
		else { // User does not exist in the system
			
			// Create user account
			user = new Users (app.getEmailAddress(), "password");
			UserProfile profile = new UserProfile();
			
			// Fill the User Profile with the Application details
			// Set Personal Details
			profile.setFirstName(app.getFirstName());
			profile.setLastName(app.getLastName());
			profile.setDob(app.getDob());
			profile.setSsn(app.getSsn());
			profile.setTitle(app.getTitle());
			profile.setGender(app.getGender());
			
			// Set Contact details
			profile.setEmailAddress(app.getEmailAddress());
			profile.setAddress(app.getAddress());
			profile.setCountry(app.getCountry());
			profile.setLocality(app.getLocality());
			profile.setPostalCode(app.getPostalCode());
			profile.setRegion(app.getRegion());
			profile.setMobilePhone(app.getMobilePhone());
			profile.setWorkPhone(app.getWorkPhone());
			profile.setHomePhone(app.getHomePhone());
		
			// Set the User Profile
			user.setUserProfile(profile);
			
			// Create the User
			userService.createUser(user, Role.ROLE_USER);
			
			// Add API Role
			userService.addRole(user, Role.ROLE_API);
			
			LOG.debug("Process Credit Application -> Created new user '" + user.getUserProfile().getEmailAddress() + "'");
		}
		
		// Set the Application status
		if (bDenied) {
			app.setApplicationStatus(CreditApplication.APP_STATUS_DENIED);
			app.setReason(deniedMsg);
		}
		
		// Set the user Id for the user of the application
		app.setUserId(user.getId());
		
		creditApplicationRepository.save(app);
		
	}
}
