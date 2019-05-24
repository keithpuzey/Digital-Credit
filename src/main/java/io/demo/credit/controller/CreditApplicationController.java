package io.demo.credit.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.demo.credit.model.CreditApplication;
import io.demo.credit.service.CreditApplicationService;
import io.demo.credit.util.Constants;
import io.demo.credit.util.Messages;
import io.demo.credit.util.Patterns;


@Validated
@RestController
public class CreditApplicationController extends CommonController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CreditApplicationController.class);
	
	@Autowired
	private CreditApplicationService creditApplicationService; 
	
	/*
	 * API Role
	 * Apply for a new Account
	 */
	@PostMapping(Constants.URI_API_CREDIT_APPLICATION)
	public ResponseEntity<?> submitCreditApplication(@RequestBody @Valid Application app) {	
		
		LOG.debug("Submit New Credit Application");
		
		CreditApplication creditApp = createCreditApplication(app);
		creditApplicationService.submitCreditApplication(creditApp);
		
		HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", creditApp.getId().toString());
        map.put("status", creditApp.getApplicationStatus());
        
		return ResponseEntity.accepted().body(map);
		
	}
	
	/*
	 * Create a Credit Application from the Application data
	 */
	private CreditApplication createCreditApplication (Application app) {
		CreditApplication creditApp = new CreditApplication();
		
		// Personal Info
		creditApp.setTitle(app.getTitle());
		creditApp.setFirstName(app.getFirstName());
		creditApp.setLastName(app.getLastName());
		creditApp.setDob(app.getDob());
		creditApp.setSsn(app.getSsn());
		creditApp.setGender(app.getGender());
		
		// Contact Info
		creditApp.setAddress(app.getAddress());
		creditApp.setLocality(app.getLocality());
		creditApp.setRegion(app.getRegion());
		creditApp.setPostalCode(app.getPostalCode());
		creditApp.setCountry(app.getCountry());
		creditApp.setHomePhone(app.getHomePhone());
		creditApp.setMobilePhone(app.getMobilePhone());
		creditApp.setWorkPhone(app.getWorkPhone());
		creditApp.setEmailAddress(app.getEmailAddress());
		
		// Financial Info
		creditApp.setAnnualIncome(app.getAnnualIncome());
		creditApp.setBankStatus(app.getBankStatus());
		creditApp.setEmploymentStatus(app.getEmploymentStatus());
		creditApp.setMonthlyMortgage(app.getMonthlyMortgage());
		creditApp.setMonthlySpend(app.getMonthlySpend());
		
		// Other
		creditApp.setBalanceTransfer(app.isBalanceTransfer());
		creditApp.setCashAdvance(app.isCashAdvance());
		creditApp.setAgreeTerms(app.isAgreeTerms());
		
		return creditApp;
	}
	
	
	/*
	 * Custom request body for credit application
	 */
	private static class Application {
		
		// Personal Info
		@NotEmpty (message=Messages.USER_FIRST_NAME_REQUIRED)
		private String firstName;
		
		@NotEmpty (message=Messages.USER_LAST_NAME_REQUIRED)
		private String lastName;
		
		@NotEmpty (message=Messages.USER_SSN_REQUIRED)
		@Pattern(regexp=Patterns.USER_SSN, message=Messages.USER_SSN_FORMAT)
		@Column(nullable=false, unique=true)
		private String ssn;
		
		@NotNull (message=Messages.USER_DOB_REQUIRED)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern=Patterns.DATE_FORMAT)
		@DateTimeFormat(pattern=Patterns.DATE_FORMAT)
		private Date dob;
		
		@NotEmpty (message=Messages.USER_TITLE_REQUIRED)
		@Pattern(regexp=Patterns.USER_TITLE, message=Messages.USER_TITLE_FORMAT)
		private String title;
		
		@NotEmpty (message=Messages.USER_GENDER_REQUIRED)
		@Pattern(regexp=Patterns.USER_GENDER, message=Messages.USER_GENDER_FORMAT)
		private String gender;
		
		// Contact Info
		@NotEmpty (message=Messages.USER_PHONE_HOME_REQUIRED)
		@Pattern(regexp=Patterns.USER_PHONE_REQ, message=Messages.USER_PHONE_HOME_FORMAT)
		private String homePhone;
		
		@Pattern(regexp=Patterns.USER_PHONE_NOT_REQ, message=Messages.USER_PHONE_MOBILE_FORMAT)
		private String mobilePhone;
		
		@Pattern(regexp=Patterns.USER_PHONE_NOT_REQ, message=Messages.USER_PHONE_WORK_FORMAT)
		private String workPhone;
		
		@NotEmpty (message=Messages.USER_ADDRESS_REQUIRED)
		private String address;
		
		@NotEmpty (message=Messages.USER_LOCALITY_REQUIRED)
		private String locality;
		
		@NotEmpty (message=Messages.USER_REGION_REQUIRED)
		private String region;
		
		@NotEmpty (message=Messages.USER_POSTAL_CODE_REQUIRED)
		private String postalCode;
		
		@NotEmpty (message=Messages.USER_COUNTRY_REQUIRED)
		private String country;
		
		@NotEmpty (message=Messages.USER_EMAIL_REQUIRED)
		@Pattern(regexp=Patterns.USER_EMAIL, message=Messages.USER_EMAIL_FORMAT)
		private String emailAddress;
		
		// Financial Info
		@NotEmpty (message=Messages.APP_EMP_STATUS_REQUIRED)
		@Pattern(regexp=Patterns.APP_EMP_STATUS, message=Messages.APP_EMP_STATUS_FORMAT)
		private String employmentStatus;
		
		@NotEmpty (message=Messages.APP_BANK_STATUS_REQUIRED)
		@Pattern(regexp=Patterns.APP_BANK_STATUS, message=Messages.APP_BANK_STATUS_FORMAT)
		private String bankStatus;
		
		@Positive (message=Messages.APP_ANNUAL_INCOME)
		private BigDecimal annualIncome;
		
		@Positive (message=Messages.APP_MONTHLY_MORTGAGE)
		private BigDecimal monthlyMortgage;
		
		private BigDecimal monthlySpend = BigDecimal.ZERO;
		private boolean cashAdvance = false;
		private boolean balanceTransfer = false;
		
		@AssertTrue (message=Messages.APP_AGREE_TERMS)
		private boolean agreeTerms;

		/**
		 * @return the firstName
		 */
		public String getFirstName() {
			return firstName;
		}

		/**
		 * @return the lastName
		 */
		public String getLastName() {
			return lastName;
		}

		/**
		 * @return the ssn
		 */
		public String getSsn() {
			return ssn;
		}

		/**
		 * @return the dob
		 */
		public Date getDob() {
			return dob;
		}

		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @return the gender
		 */
		public String getGender() {
			return gender;
		}

		/**
		 * @return the homePhone
		 */
		public String getHomePhone() {
			return homePhone;
		}

		/**
		 * @return the mobilePhone
		 */
		public String getMobilePhone() {
			return mobilePhone;
		}

		/**
		 * @return the workPhone
		 */
		public String getWorkPhone() {
			return workPhone;
		}

		/**
		 * @return the address
		 */
		public String getAddress() {
			return address;
		}

		/**
		 * @return the locality
		 */
		public String getLocality() {
			return locality;
		}

		/**
		 * @return the region
		 */
		public String getRegion() {
			return region;
		}

		/**
		 * @return the postalCode
		 */
		public String getPostalCode() {
			return postalCode;
		}

		/**
		 * @return the country
		 */
		public String getCountry() {
			return country;
		}

		/**
		 * @return the emailAddress
		 */
		public String getEmailAddress() {
			return emailAddress;
		}

		/**
		 * @return the employmentStatus
		 */
		public String getEmploymentStatus() {
			return employmentStatus;
		}

		/**
		 * @return the bankStatus
		 */
		public String getBankStatus() {
			return bankStatus;
		}

		/**
		 * @return the annualIncome
		 */
		public BigDecimal getAnnualIncome() {
			return annualIncome;
		}

		/**
		 * @return the monthlyMortgage
		 */
		public BigDecimal getMonthlyMortgage() {
			return monthlyMortgage;
		}

		/**
		 * @return the monthlySpend
		 */
		public BigDecimal getMonthlySpend() {
			return monthlySpend;
		}

		/**
		 * @return the cashAdvance
		 */
		public boolean isCashAdvance() {
			return cashAdvance;
		}

		/**
		 * @return the balanceTransfer
		 */
		public boolean isBalanceTransfer() {
			return balanceTransfer;
		}

		/**
		 * @return the agreeTerms
		 */
		public boolean isAgreeTerms() {
			return agreeTerms;
		}
		
		
	}

}
