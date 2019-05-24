package io.demo.credit.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.demo.credit.util.Messages;
import io.demo.credit.util.Patterns;

@Entity
public class CreditApplication {
	
	// Banking Status
	public static final String BK_ST_CHK_AND_SAV 		= "Checking and Savings";
	public static final String BK_ST_CHK_ONLY 			= "Checking Only";
	public static final String BK_ST_SAV_ONLY 			= "Savings Only";
	public static final String BK_ST_NEITHER 			= "Neither";
	
	// Employment Status
	public static final String EMP_ST_EMPLOYED 			= "Employed";
	public static final String EMP_ST_SELF_EMPLOYED 	= "Self-Employed";
	public static final String EMP_ST_RETIRED 			= "Retired";
	public static final String EMP_ST_STUDENT 			= "Student";
	public static final String EMP_ST_UNEMPLOYED 		= "Unemployed";
	
	// Credit Application Status
	public static final String APP_STATUS_ACCEPTED		= "Accepted";
	public static final String APP_STATUS_DENIED		= "Denied";
	public static final String APP_STATUS_IN_PROCESS	= "In Process";
	public static final String APP_STATUS_COMPLETE		= "Complete";
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(nullable=false, updatable=false)
	@JsonProperty (access = Access.READ_ONLY)
	private Long id;
	
	@JsonProperty (access = Access.READ_ONLY)
	private String applicationStatus;
	
	@JsonProperty (access = Access.READ_ONLY)
	private String reason;
	
	@JsonProperty (access = Access.READ_ONLY)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern=Patterns.DATE_FORMAT)
	@DateTimeFormat(pattern=Patterns.DATE_FORMAT)
	private Date applicationDate;
	
	@JsonProperty (access = Access.READ_ONLY)
	private Long userId;
	
	// Personal Info
	@NotEmpty (message=Messages.USER_FIRST_NAME_REQUIRED)
	private String firstName;
	
	@NotEmpty (message=Messages.USER_LAST_NAME_REQUIRED)
	private String lastName;
	
	@NotEmpty (message=Messages.USER_SSN_REQUIRED)
	@Pattern(regexp=Patterns.USER_SSN, message=Messages.USER_SSN_FORMAT)
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
	
	/*
	 * Empty Application
	 */
	public CreditApplication(){}
	
	/*
	 * Prefilled Application
	 */
	public CreditApplication(UserProfile profile){
		
		this.firstName = profile.getFirstName();
		this.lastName = profile.getLastName();
		this.ssn = profile.getSsn();
		this.dob = profile.getDob();
		this.homePhone = profile.getHomePhone();
		this.workPhone = profile.getWorkPhone();
		this.mobilePhone = profile.getMobilePhone();
		this.address = profile.getAddress();
		this.locality = profile.getLocality();
		this.region = profile.getRegion();
		this.postalCode = profile.getPostalCode();
		this.emailAddress = profile.getEmailAddress();
	}
	
	/**
	 * @return the employmentStatus
	 */
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	/**
	 * @param employmentStatus the employmentStatus to set
	 */
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	/**
	 * @return the bankStatus
	 */
	public String getBankStatus() {
		return bankStatus;
	}
	/**
	 * @param bankStatus the bankStatus to set
	 */
	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}
	/**
	 * @return the annualIncome
	 */
	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}
	/**
	 * @param annualIncome the annualIncome to set
	 */
	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
	}
	/**
	 * @return the monthlyMortgage
	 */
	public BigDecimal getMonthlyMortgage() {
		return monthlyMortgage;
	}
	/**
	 * @param monthlyMortgage the monthlyMortgage to set
	 */
	public void setMonthlyMortgage(BigDecimal monthlyMortgage) {
		this.monthlyMortgage = monthlyMortgage;
	}
	/**
	 * @return the monthlySpend
	 */
	public BigDecimal getMonthlySpend() {
		return monthlySpend;
	}
	/**
	 * @param monthlySpend the monthlySpend to set
	 */
	public void setMonthlySpend(BigDecimal monthlySpend) {
		this.monthlySpend = monthlySpend;
	}
	/**
	 * @return the cashAdvance
	 */
	public boolean isCashAdvance() {
		return cashAdvance;
	}
	/**
	 * @param cashAdvance the cashAdvance to set
	 */
	public void setCashAdvance(boolean cashAdvance) {
		this.cashAdvance = cashAdvance;
	}
	/**
	 * @return the balanceTransfer
	 */
	public boolean isBalanceTransfer() {
		return balanceTransfer;
	}
	/**
	 * @param balanceTransfer the balanceTransfer to set
	 */
	public void setBalanceTransfer(boolean balanceTransfer) {
		this.balanceTransfer = balanceTransfer;
	}
	/**
	 * @return the agreeTerms
	 */
	public boolean isAgreeTerms() {
		return agreeTerms;
	}
	/**
	 * @param agreeTerms the agreeTerms to set
	 */
	public void setAgreeTerms(boolean agreeTerms) {
		this.agreeTerms = agreeTerms;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	/**
	 * @return the homePhone
	 */
	public String getHomePhone() {
		return homePhone;
	}
	/**
	 * @param homePhone the homePhone to set
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	/**
	 * @return the workPhone
	 */
	public String getWorkPhone() {
		return workPhone;
	}
	/**
	 * @param workPhone the workPhone to set
	 */
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}
	/**
	 * @param locality the locality to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the applicationStatus
	 */
	public String getApplicationStatus() {
		return applicationStatus;
	}

	/**
	 * @param applicationStatus the applicationStatus to set
	 */
	@JsonIgnore
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the applicationDate
	 */
	public Date getApplicationDate() {
		return applicationDate;
	}

	/**
	 * @param applicationDate the applicationDate to set
	 */
	@JsonIgnore
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	@JsonIgnore
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
