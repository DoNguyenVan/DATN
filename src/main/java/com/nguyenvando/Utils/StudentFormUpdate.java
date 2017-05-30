/**
 * 
 */
package com.nguyenvando.Utils;

import java.util.HashSet;
import java.util.Set;

import com.nguyenvando.Entities.Class;

/**
 * @author Nguyen Van Do
 *
 */
public class StudentFormUpdate {

	private Integer studentId;
	private String fullName;
	private String dateOfBirth;
	private String phoneNumber;
	private String email;
	private String gender;
	private String stLevel;
	private Integer city;
	private Integer district;
	private Integer school;
	private String userName;
	private String password;
	private String newUserName;
	private String newPassword;
	private Integer classId;
	private Set<Class> classOfST = new HashSet<>();
	/**
	 * 
	 */
	public StudentFormUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}


	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStLevel() {
		return stLevel;
	}
	public void setStLevel(String stLevel) {
		this.stLevel = stLevel;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getDistrict() {
		return district;
	}
	public void setDistrict(Integer district) {
		this.district = district;
	}
	public Integer getSchool() {
		return school;
	}
	public void setSchool(Integer school) {
		this.school = school;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Class> getClassOfST() {
		return classOfST;
	}
	public void setClassOfST(Set<Class> classOfST) {
		this.classOfST = classOfST;
	}
	public String getNewUserName() {
		return newUserName;
	}
	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
			
}
