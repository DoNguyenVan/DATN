/**
 * 
 */
package com.nguyenvando.Controller;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.Student;
import com.nguyenvando.Services.ClassManagementService;
import com.nguyenvando.Services.StudentManagementService;
import com.nguyenvando.Utils.CLASS_ST_Object;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

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
	@Autowired 
	private StudentManagementService studentService;
	
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
	
	@ResponseBody
	@RequestMapping(value="/student_Register_ClassList",method=RequestMethod.GET)
	public Map<Integer,CLASS_ST_Object> student_register(@RequestParam(name="data")String data){	
		return studentService.getClassByCourse(Integer.parseInt(data));
	}
	
	@ResponseBody
	@RequestMapping(value="/filterClass",method=RequestMethod.GET)
	public Map<Integer,CLASS_ST_Object> filter_Class(@RequestParam(name="data")String[] data){		
		return studentService.getClassByCourse(data[0], data[1]);
	}
	
	@ResponseBody
	@RequestMapping(value="/paidFeeByMoney",method=RequestMethod.GET)
	public Map<String,Object>  paidFeeByMoney(@RequestParam(name="data")String[] data){		
		System.out.println(data[0] +" "+ data[1]+" "+data[2]);
		Map<String, Object> map = new HashMap<>();
		try{
			studentService.paidFee(Integer.parseInt(data[0]),Float.parseFloat(data[1]),Integer.parseInt(data[2]));
			map.put("msg", "You were paid fee success!");
			return map;
		}catch(Exception e){
			map.put("msg", "You were paid fee failed!");
			return map;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/ChangeDistrictList",method=RequestMethod.GET)
	public Map<Integer,String> changeDistrict (@RequestParam(name = "data") String data) throws ParseException {
		return  studentService.mapDistrict(Integer.parseInt(data));			
	}

	@ResponseBody
	@RequestMapping(value="/registerClassFormAddmin",method=RequestMethod.GET)
	public Map<String,Object>  registerClass(@RequestParam(name="data")String[] data){		

		Student st = studentService.getStudentById(Integer.parseInt(data[0]));				
		Map<String, Object> map = new HashMap<>();
		studentService.RegisterClassForStudent(st, data[1]);
		
		return map;
	}	

}
