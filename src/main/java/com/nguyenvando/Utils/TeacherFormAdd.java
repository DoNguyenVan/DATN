/**
 * 
 */
package com.nguyenvando.Utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Nguyen Van Do
 *
 */
public class TeacherFormAdd {

	private String fullName;
	private String dateOfBirth;
	private String phoneNumber;
	private String email;
	private String gender;
	private Integer city;
	private Integer district;
	private String userName;
	private String password;
	private String skillName;
	private String experiment;
	private String urlCertificate;
	private String note;	
	private MultipartFile file;
	
	public TeacherFormAdd() {
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

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getExperiment() {
		return experiment;
	}

	public void setExperiment(String experiment) {
		this.experiment = experiment;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getUrlCertificate() {
		return urlCertificate;
	}

	public void setUrlCertificate(String urlCertificate) {
		this.urlCertificate = urlCertificate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
