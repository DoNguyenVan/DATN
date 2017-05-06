/**
 * 
 */
package com.nguyenvando.Utils;

/**
 * @author Nguyen Van Do
 *
 */
public class ClassFormAdd {

	private String className;
	private String startDate;
	private int numberOfSeats;
	private String classLevel;
	private float fee;
	private Integer teacherId;
	private Integer idCourse;
	/**
	 * 
	 */
	public ClassFormAdd() {		
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	
	public String getClassLevel() {
		return classLevel;
	}
	public void setClassLevel(String classLevel) {
		this.classLevel = classLevel;
	}
	
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	public Integer getIdCourse() {
		return idCourse;
	}
	public void setIdCourse(Integer idCourse) {
		this.idCourse = idCourse;
	}

}
