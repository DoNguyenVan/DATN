/**
 * 
 */
package com.nguyenvando.Utils;

/**
 * @author Nguyen Van Do
 *
 */
public class SchoolFeeForm {

	private String datePaid;
	private String feeValue;
	private Integer studentId;
	/**
	 * 
	 */
	public SchoolFeeForm() {
	}
	
	
	/**
	 * @param datePaid
	 * @param feeValue
	 */
	public SchoolFeeForm(String datePaid, String feeValue) {
		super();
		this.datePaid = datePaid;
		this.feeValue = feeValue;
	}


	public String getDatePaid() {
		return datePaid;
	}
	public void setDatePaid(String datePaid) {
		this.datePaid = datePaid;
	}
	public String getFeeValue() {
		return feeValue;
	}
	public void setFeeValue(String feeValue) {
		this.feeValue = feeValue;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

}
