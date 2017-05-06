/**
 * 
 */
package com.nguyenvando.Entities;

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
@Table(name="SKILLS")
public class Skill {
	
	private Integer skillId;
	private String skillName;
	private String achieve;
	private String experience;
	private String UrlCertificateSkill;
	private String note;
	private Teacher tcSkill;

	public Skill() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="skillId",unique=true,nullable=false)
	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	@Column(name="skillName",length=40,nullable=false)
	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	@Column(name="achieve",length=40,nullable=true)
	public String getAchieve() {
		return achieve;
	}

	public void setAchieve(String achieve) {
		this.achieve = achieve;
	}

	@Column(name="experience",length=40,nullable=true)
	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	@Column(name="UrlCertificate",nullable=true)
	public String getUrlCertificateSkill() {
		return UrlCertificateSkill;
	}

	public void setUrlCertificateSkill(String urlCertificateSkill) {
		UrlCertificateSkill = urlCertificateSkill;
	}

	@Column(name="Note",length=200,nullable=true)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teacherId", nullable = false)
	public Teacher getTcSkill() {
		return tcSkill;
	}

	public void setTcSkill(Teacher tcSkill) {
		this.tcSkill = tcSkill;
	}	

}
