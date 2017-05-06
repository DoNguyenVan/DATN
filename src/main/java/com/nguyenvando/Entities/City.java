package com.nguyenvando.Entities;

import java.util.Collection;

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
@Table(name="CITY")
public class City {
	
	private Integer cityId;
	private String cityName;
	private Collection<District> district;

	public City() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cityId")
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name="cityName",length=40,nullable=true)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	

//	@OneToOne(fetch = FetchType.EAGER)
//	@PrimaryKeyJoinColumn
//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "city")
	@Fetch(value = FetchMode.SUBSELECT)
	public Collection<District> getDistrict() {
		return district;
	}

	public void setDistrict(Collection<District> district) {
		this.district = district;
	}	

}
