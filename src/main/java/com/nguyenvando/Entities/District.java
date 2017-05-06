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
@Table(name="DISTRICT")
public class District {
	
	private Integer districtId;
	private String districtName;
	private City city;

	public District() {

	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="districtId",unique=true,nullable=false)
	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	@Column(name="districtName",length=50,nullable=true)
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cityId", nullable = false)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
