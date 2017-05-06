/**
 * 
 */
package com.nguyenvando.Entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Nguyen Van Do
 *
 */
@Entity
@Table(name="EXAMINATION")
public class Examination {
	
	private Integer examId;
	private String examName;
	private String certificateUrl;
	private Collection<Score> score;
	private Collection<Student> student;

	public Examination() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="examId")
	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	@Column(name="examName",length=40,nullable=false,unique=true)
	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}
	

	@Column(name="certificateUrl",nullable=true)
	public String getCertificateUrl() {
		return certificateUrl;
	}

	public void setCertificateUrl(String certificateUrl) {
		this.certificateUrl = certificateUrl;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "exam")
	@Fetch(value = FetchMode.SUBSELECT)	
	public Collection<Score> getScore() {
		return score;
	}

	public void setScore(Collection<Score> score) {
		this.score = score;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "examList")
	public Collection<Student> getStudent() {
		return student;
	}

	public void setStudent(Collection<Student> student) {
		this.student = student;
	}

}
