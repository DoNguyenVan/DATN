/**
 * 
 */
package com.nguyenvando.Entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Nguyen Van Do
 *
 */
@Entity
@Table(name="COURSE")
public class Course {

	private Integer idCourse;
	private String courseName;
	private String timeline;
	private String note;
	private Set<Class> listClassOfCourse = new HashSet<>();

	public Course() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCourse")
	public Integer getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Integer idCourse) {
		this.idCourse = idCourse;
	}

	@Column(name="courseName",length=40,nullable=false)
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column(name="timeLine",nullable=false,length=30)
	public String getTimeline() {
		return timeline;
	}

	public void setTimeline(String timeline) {
		this.timeline = timeline;
	}

	
	
	@Column(name="note",nullable=true,length=100)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
	@Fetch(value = FetchMode.SUBSELECT)
	public Set<Class> getListClassOfCourse() {
		return listClassOfCourse;
	}

	public void setListClassOfCourse(Set<Class> listClassOfCourse) {
		this.listClassOfCourse = listClassOfCourse;
	}


}
