/**
 * 
 */
package com.nguyenvando.Utils;

/**
 * @author Nguyen Van Do
 *
 */
public class FormFinance {
	
	private Integer month;
	private Integer year;
	private Integer month1;
	private Integer month2;
	private Integer month3;
	private Integer month4;

	/**
	 * 
	 */
	public FormFinance() {

	}
	
	/**
	 * @param year
	 * @param month1
	 * @param month2
	 * @param month3
	 * @param month4
	 */
	public FormFinance(Integer year, Integer month1, Integer month2, Integer month3, Integer month4) {
		super();
		this.year = year;
		this.month1 = month1;
		this.month2 = month2;
		this.month3 = month3;
		this.month4 = month4;
	}

	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth1() {
		return month1;
	}
	public void setMonth1(Integer month1) {
		this.month1 = month1;
	}
	public Integer getMonth2() {
		return month2;
	}
	public void setMonth2(Integer month2) {
		this.month2 = month2;
	}
	public Integer getMonth3() {
		return month3;
	}
	public void setMonth3(Integer month3) {
		this.month3 = month3;
	}
	public Integer getMonth4() {
		return month4;
	}
	public void setMonth4(Integer month4) {
		this.month4 = month4;
	}
			
}
