/**
 * 
 */
package com.nguyenvando.Entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Nguyen Van Do
 *
 **/
@Entity
@Table(name = "USERS")
public class User {

	private String username;
	private String password;
	private boolean enabled;
	private String profileImgUrl;
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	// Constructor

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String username, String password,
		boolean enabled, Set<UserRole> userRole) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	// Constructor	
	public User(String username2, String password2, boolean enabled2, List<GrantedAuthority> authorities) {
		this.username = username2;
		this.password = password2;
		this.enabled = enabled2;
		this.userRole = new HashSet<UserRole>();
	}

	@Id
	@Column(name = "username", unique = true,
		nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password",
		nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = true)
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Column(name="profileImg",length=50,nullable=true)
	public String getProfileImgUrl() {
		return profileImgUrl;
	}

	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public Set<UserRole> getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

}
