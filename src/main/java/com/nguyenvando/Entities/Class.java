/**
 * 
 */
package com.nguyenvando.Entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Nguyen Van Do
 *
 */
@Entity
@Table(name="CLASS")
public class Class {
	
	private Integer classId;
	private String className;
	private String startDate;
	private int numberOfSeats;
	private String classLevel;
	private float fee;
	private float feeRemain;
	private Set<Student>stList = new HashSet<>();
	private List<Time>timeList = new ArrayList<Time>();
	private Teacher teacher;
	private Course course;
	
    public Class() {		
	
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "classId", unique = true,nullable = false)
	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Column(name="className",length=20,nullable=false)
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name="startDate",nullable=false)
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name="numberOfSeats",nullable=false)
	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	@Column(name="classLevel",nullable=true)
	public String getClassLevel() {
		return classLevel;
	}

	public void setClassLevel(String classLevel) {
		this.classLevel = classLevel;
	}
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "classTime")
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Time> getTimeList() {
		return timeList;
	}

	public void setTimeList(List<Time> timeList) {
		this.timeList = timeList;
	}

	@Column(name="fee",nullable=true)
	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "CLASS_STUDENT", joinColumns = {
			@JoinColumn(name = "classId", nullable = false, updatable = true) },
			inverseJoinColumns = { @JoinColumn(name = "studentId",
					nullable = false, updatable = true) })
	public Set<Student> getStList() {
		return stList;
	}

	public void setStList(Set<Student> stList) {
		this.stList = stList;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teacherId", nullable = true)
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCourse", nullable = false)
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Column(name="feeRemain",nullable=true)
	public float getFeeRemain() {
		return feeRemain;
	}

	public void setFeeRemain(float feeRemain) {
		this.feeRemain = feeRemain;
	}
	
}
