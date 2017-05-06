package com.nguyenvando.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Nguyen Van Do
 *
 */
@Entity
@Table(name = "SCHOOL")
public class School {
	
	private Integer schoolId;
	private String schoolName;
	
	
	public School() {		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="schoolId",unique=true,nullable=false)
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	@Column(name="schoolName",length=100,nullable=true)
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

}
