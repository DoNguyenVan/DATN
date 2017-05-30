/**
 * 
 */
package com.nguyenvando.Utils;

/**
 * @author Nguyen Van Do
 *
 */
public class STUDENT_SHEDULE {

	private String dayOfWeek;
	private String startTime;
	private String endtTime;
	private String className;
	/**
	 * 
	 */
	public STUDENT_SHEDULE() {
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndtTime() {
		return endtTime;
	}
	public void setEndtTime(String endtTime) {
		this.endtTime = endtTime;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
