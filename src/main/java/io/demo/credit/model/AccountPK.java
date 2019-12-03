package io.demo.credit.model;

import java.io.Serializable;

public class AccountPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4024787554337641909L;
	
	private Long id;
	private Long accountNumber;
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
	 * @return the accountNumber
	 */
	public Long getAccountNumber() {
		return accountNumber;
	}
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	

}
