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
@Table(name="SALARY")
public class Salary {

	private Integer salaryId;
	private float money;
	private Date datePaid;
//	private boolean isPaid;
	private Teacher teacher_Salary;

	public Salary() {		
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="salaryId",unique=true,nullable=false)
	public Integer getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Integer salaryId) {
		this.salaryId = salaryId;
	}

	@Column(name="money",nullable=false)
	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}


	

//	@Column(name="isPaid",nullable=true)
//	public boolean isPaid() {
//		return isPaid;
//	}
//
//	public void setPaid(boolean isPaid) {
//		this.isPaid = isPaid;
//	}
	
	@Column(name="datePaid",nullable=false)
	public Date getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacherId", nullable = true)
	public Teacher getTeacher_Salary() {
		return teacher_Salary;
	}

	public void setTeacher_Salary(Teacher teacher_Salary) {
		this.teacher_Salary = teacher_Salary;
	}

}
