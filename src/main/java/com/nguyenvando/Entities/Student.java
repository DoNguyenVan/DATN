package com.nguyenvando.Entities;

import java.util.Collection;
import java.util.HashSet;
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
@Table(name="STUDENT")
public class Student {

	private Integer studentId;
	private String fullName;
	private String dateOfBirth;
	private String phoneNumber;
	private String email;
	private String studentLevel;
	private String gender;
	private Address stAddress;
	private School school;
	private User stAccount;
	private Set<Class> classOfStudent = new HashSet<>();
	private Collection<Examination> examList;
	private Collection<SchoolFee> feePaidList;
	public Student() {
	}

	
	/**
	 * @param studentId
	 * @param fullName
	 * @param dateOfBirth
	 * @param phoneNumber
	 * @param email
	 * @param studentLevel
	 * @param gender
	 * @param stAddress
	 * @param school
	 * @param stAccount
	 * @param classOfStudent
	 * @param examList
	 * @param feePaidList
	 */
	public Student(Student st) {
		this.studentId = st.studentId;
		this.fullName = st.fullName;
		this.dateOfBirth = st.dateOfBirth;
		this.phoneNumber = st.phoneNumber;
		this.email = st.email;
		this.studentLevel = st.studentLevel;
		this.gender = st.gender;
		this.stAddress = st.stAddress;
		this.school = st.school;
		this.stAccount = st.stAccount;
		this.classOfStudent = st.classOfStudent;
		this.examList = st.examList;
		this.feePaidList = st.feePaidList;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="studentId", unique = true,nullable = false)
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Column(name="fullName",length = 50,nullable = false)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	
	@Column(name="dateOfBirth",length = 20,nullable = false)
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name="phoneNumber",length = 15,nullable = false)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name="email",length = 50,nullable = false,unique=true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="stLevel",nullable=true,length=40)
	public String getStudentLevel() {
		return studentLevel;
	}

	public void setStudentLevel(String studentLevel) {
		this.studentLevel = studentLevel;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="addressId",nullable=true)
	public Address getStAddress() {
		return stAddress;
	}

	public void setStAddress(Address stAddress) {
		this.stAddress = stAddress;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="schoolId",nullable=true)
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ac_student",nullable=true)
	public User getStAccount() {
		return stAccount;
	}

	public void setStAccount(User stAccount) {
		this.stAccount = stAccount;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
	@Fetch(value = FetchMode.SUBSELECT)
	public Collection<SchoolFee> getFeePaidList() {
		return feePaidList;
	}

	public void setFeePaidList(Collection<SchoolFee> feePaidList) {
		this.feePaidList = feePaidList;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "stList")
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "CLASS_STUDENT", joinColumns = {
//			@JoinColumn(name = "studentId", nullable = false, updatable = false) },
//			inverseJoinColumns = { @JoinColumn(name = "classId",
//					nullable = false, updatable = false) })
	public Set<Class> getClassOfStudent() {
		return classOfStudent;
	}

	public void setClassOfStudent(Set<Class> classOfStudent) {
		this.classOfStudent = classOfStudent;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "STUDENT_EXAM", joinColumns = {
			@JoinColumn(name = "studentId", nullable = true, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "examId",
					nullable = true, updatable = false) })
	public Collection<Examination> getExamList() {
		return examList;
	}

	public void setExamList(Collection<Examination> examList) {
		this.examList = examList;
	}

	@Column(name="gender",nullable=true)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
