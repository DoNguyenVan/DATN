package com.nguyenvando.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@RequestMapping(value = "/student")
public class StudentManageController {

	@RequestMapping(value = "/home")
	public ModelAndView studentPage() {
		ModelAndView model = new ModelAndView();
	
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This page is for STUDENT only!");
	
		model.setViewName("student");
		return model;

	}

}
