/**
 * 
 */
package com.nguyenvando.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nguyenvando.Dao.UserDao;
import com.nguyenvando.Entities.UserRole;

/**
 * @author Nguyen Van Do
 *
 */
@Service("loginService")
public class LoginService implements UserDetailsService{
	
	@Autowired
	private UserDao userdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.nguyenvando.Entities.User user = userdao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User " + username + "not found exception ");
		}		
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());	
				
		return (UserDetails) buildUserForAuthentication(user, authorities);

	}

	// Converts com.nguyenvando.Entities.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.nguyenvando.Entities.User user,
		List<GrantedAuthority> authorities) {
		return  new User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}
		
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}		

}
