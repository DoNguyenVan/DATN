package com.nguyenvando.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nguyenvando.Services.StudentManagementService;
import com.nguyenvando.Utils.StudentFormAdd;


@Controller
@EnableWebMvc
public class HomeController {
	@Autowired
	private StudentManagementService studentService;
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView defaultPage(Map<String,Object> map) {
		ModelAndView model = new ModelAndView();
		map.put("STF",new StudentFormAdd());
		map.put("schoolMap", studentService.mapSchool());	
		map.put("mapCity", studentService.mapCity());
		map.put("mapDistrict", studentService.mapDistrict(0));
		map.put("mapGender", studentService.mapGender());
		model.setViewName("login");
		return model;
	}

}
