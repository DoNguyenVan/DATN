/**
 * 
 */
package com.nguyenvando.Utils;

/**
 * @author Nguyen Van Do
 *
 */
public class Statistic {

	private String activity;
	private String date;
	private float proceeds;
	private float payouts;
	private float interest;
	/**
	 * 
	 */
	public Statistic() {
	
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public float getProceeds() {
		return proceeds;
	}
	public void setProceeds(float proceeds) {
		this.proceeds = proceeds;
	}
	public float getPayouts() {
		return payouts;
	}
	public void setPayouts(float payouts) {
		this.payouts = payouts;
	}
	public float getInterest() {
		return interest;
	}
	public void setInterest(float interest) {
		this.interest = interest;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

		
}
