/**
 * 
 */
package com.nguyenvando.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Nguyen Van Do
 *
 */
@Entity
@Table(name="TIME")
public class Time {
	
	private Integer timeId;
	private String dateOfWeek;
	private String startTime;
	private String endTime;
	private Class classTime;
	
	public Time() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="timeId",unique = true, nullable = false)
	public Integer getTimeId() {
		return timeId;
	}

	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
	}

	@Column(name="dateOfWeek",nullable=false)
	public String getDateOfWeek() {
		return dateOfWeek;
	}

	public void setDateOfWeek(String dateOfWeek) {
		this.dateOfWeek = dateOfWeek;
	}

	@Column(name="startTime",nullable=false)
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name="endTime",nullable=false)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "classId", nullable = false)
	public Class getClassTime() {
		return classTime;
	}

	public void setClassTime(Class classTime) {
		this.classTime = classTime;
	}

}
