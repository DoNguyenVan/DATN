/**
 * 
 */
package com.nguyenvando.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nguyenvando.Entities.Class;
import com.nguyenvando.Services.ClassManagementService;
import com.nguyenvando.Services.StudentManagementService;

/**
 * @author Nguyen Van Do
 *
 */
@Controller
@EnableWebMvc
@RequestMapping(value="/myAPI")
public class MyAppAPI {
	@Autowired
	private ClassManagementService classService;
	@Autowired StudentManagementService studentService;
	
	@ResponseBody
	@RequestMapping(value="/ChangeClassTable",method=RequestMethod.GET)
	public Map<Integer,Class> listClassTable(@RequestParam(name="data") String data){
		return classService.getClassByCourse(Integer.parseInt(data));		
	}
	
	@ResponseBody
	@RequestMapping(value="/changeClassList",method=RequestMethod.GET)
	public Map<Integer,String> getMapClass(@RequestParam(name="data")String[] data){	
		return studentService.mapClass(data[0],data[1]);
	}

}
