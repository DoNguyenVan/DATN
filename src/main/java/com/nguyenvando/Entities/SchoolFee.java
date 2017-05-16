/**
 * 
 */
package com.nguyenvando.Entities;


import java.util.Date;

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
@Table(name="SCHOOL_FEE")
public class SchoolFee {
	
	private Integer feeId;
	private Date datePaid;
	private float feeValue;
	private float remain;
	private Student student ;
	
	public SchoolFee() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="feeId",unique = true, nullable = false)
	public Integer getFeeId() {
		return feeId;
	}

	public void setFeeId(Integer feeId) {
		this.feeId = feeId;
	}

	@Column(name="datePaid",nullable = false)
	public Date getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}

	@Column(name="feeValue",nullable=false)
	public float getFeeValue() {
		return feeValue;
	}

	public void setFeeValue(float feeValue) {
		this.feeValue = feeValue;
	}

	@Column(name="remain",nullable=false)
	public float getRemain() {
		return remain;
	}

	public void setRemain(float remain) {
		this.remain = remain;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentId", nullable = true)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
