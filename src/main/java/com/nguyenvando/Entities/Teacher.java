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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Nguyen Van Do
 *
 */
@Entity
@Table(name="TEACHER")
public class Teacher {
	
	private Integer teacherId;
	private String fullName;
	private String dateOfBirth;
	private String phoneNumber;
	private String email;
	private Address stAddress;
	private User tcAccount;
	private Set<Skill> tcSkill = new HashSet<>();
	private Collection<Salary> tcSalary ;
	private Collection<Class> tcClassList;
	/**
	 * 
	 */
	public Teacher() {	
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="teacherId",unique=true,nullable=false)
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	@Column(name="fullName",length=40,nullable=false)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name="dateOfBirth",length=20,nullable=false)
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Column(name="phoneNumber",length=20,nullable=false)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name="email",length=30,nullable=false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="addressId",nullable=true)
	public Address getStAddress() {
		return stAddress;
	}
	public void setStAddress(Address stAddress) {
		this.stAddress = stAddress;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ac_teacher")
	public User getTcAccount() {
		return tcAccount;
	}
	public void setTcAccount(User tcAccount) {
		this.tcAccount = tcAccount;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tcSkill")
	@Fetch(value = FetchMode.SUBSELECT)	
	public Set<Skill> getTcSkill() {
		return tcSkill;
	}

	public void setTcSkill(Set<Skill> tcSkill) {
		this.tcSkill = tcSkill;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher_Salary")
	@Fetch(value = FetchMode.SUBSELECT)
	public Collection<Salary> getTcSalary() {
		return tcSalary;
	}

	public void setTcSalary(Collection<Salary> tcSalary) {
		this.tcSalary = tcSalary;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
	@Fetch(value = FetchMode.SUBSELECT)
	public Collection<Class> getTcClassList() {
		return tcClassList;
	}

	public void setTcClassList(Collection<Class> tcClassList) {
		this.tcClassList = tcClassList;
	}
		
}
