/**
 * 
 */
package com.nguyenvando.Controller;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nguyenvando.Services.StudentManagementService;
import com.nguyenvando.Utils.StudentFormAdd;

/**
 * @author Nguyen Van Do
 *
 */
@Controller
@EnableWebMvc
public class LoginController {
	@Autowired
	private StudentManagementService studentService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error",required=false) String error,
			@RequestParam(value = "logout",required=false) String logout, HttpServletRequest request,Map<String, Object>map) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));	
			map.put("STF",new StudentFormAdd());
			map.put("schoolMap", studentService.mapSchool());	
			map.put("mapCity", studentService.mapCity());
			map.put("mapDistrict", studentService.mapDistrict(0));
			map.put("mapGender", studentService.mapGender());
		}
		if (logout != null) {
			map.put("STF",new StudentFormAdd());
			map.put("schoolMap", studentService.mapSchool());	
			map.put("mapCity", studentService.mapCity());
			map.put("mapDistrict", studentService.mapDistrict(0));
			map.put("mapGender", studentService.mapGender());
			model.addObject("msg", "You've been logged out successfully.");			
		}
		
		model.setViewName("login1");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {
		
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}
	
	@RequestMapping("/loginSuccessFull")
	public String surface(){
		
		@SuppressWarnings("unchecked")
		Collection<SimpleGrantedAuthority> roleCurrentUser = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();		

		if("[ADMIN]".equals(roleCurrentUser.toString().trim())){
			return "redirect:/AdminManagement";
		}
		if("[STUDENT]".equals(roleCurrentUser.toString().trim())){
			return "redirect:/student/home";
		}
		if("[TEACHER]".equals(roleCurrentUser.toString().trim())){
			return "redirect:/teacher/home";
		}
		return "redirect:/403";
	}
	
	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("403");
		return model;

	}

}
