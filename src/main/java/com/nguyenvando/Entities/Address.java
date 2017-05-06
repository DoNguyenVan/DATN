package com.nguyenvando.Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Nguyen Van Do
 *
 */
@Entity
@Table(name="ADDRESS")
public class Address {
	
	private Integer addressId;
	private City city;
	private District district;
//	private Student student;
//	private Teacher teacher;
	
	public Address() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="addressId",unique = true, nullable = false)
	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cityId",nullable=true)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="districtId",nullable=true)
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	

//	@OneToOne(fetch = FetchType.EAGER)
//	@PrimaryKeyJoinColumn
//	public Student getStudent() {
//		return student;
//	}
//
//	public void setStudent(Student student) {
//		this.student = student;
//	}
//
//	

}
