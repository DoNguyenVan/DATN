package com.nguyenvando.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.Student;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Services.StudentManagementService;
import com.nguyenvando.Utils.FormForList;
import com.nguyenvando.Utils.StudentFormAdd;

@Controller
@EnableWebMvc
@RequestMapping(value = "/student")
public class StudentManageController {

	@Autowired
	private StudentManagementService studentService;
	private PasswordEncoder passwordEncoder;
	private Student currentStudent;
	
	@RequestMapping(value = "/home")
	public ModelAndView studentPage(HttpServletRequest request,Map<String, Object>map) {
	
		try{			
			currentStudent= studentService.getStudentByAccount(request.getUserPrincipal().getName());	
		
			if(currentStudent!=null){	
			//	List<Class> ClassList = new ArrayList<>();

				map.put("class_StList",currentStudent.getClassOfStudent());
				return this.viewStudentForm(map);
			}else{
				map.put("error","Cannot Found Student into database!");
				return this.errorForm(map);
			}
		}catch(Exception e){
			map.put("error", e.getMessage());
			return this.errorForm(map);
		}		

	}
	
	private ModelAndView errorForm(Map<String, Object>map){
		ModelAndView model = new ModelAndView();
		map.put("STF",new StudentFormAdd());
		map.put("schoolMap", studentService.mapSchool());	
		map.put("mapCity", studentService.mapCity());
		map.put("mapDistrict", studentService.mapDistrict(0));
		map.put("mapGender", studentService.mapGender());
		model.setViewName("login");
		return model;

	}
	
	private ModelAndView viewStudentForm(Map<String, Object>map){
		ModelAndView model = new ModelAndView();
		// sent to view page
	//	map.put("Student", currentStudent);
		Student st = studentService.getStudentById(currentStudent.getStudentId());
		StudentFormAdd stFormUpdate = studentService.generateFormStudent(st);
		map.put("StudentForm", stFormUpdate);
		map.put("ClassMap", studentService.mapClass(null,null)); 
		map.put("stclassList", studentService.generateSetToList(stFormUpdate.getClassListOfST()));
		map.put("cityMap", studentService.mapCity());
		map.put("districtMap", studentService.mapDistrict(stFormUpdate.getCity()));
		map.put("schoolMap", studentService.mapSchool());

		
		
		
		map.put("RegisterForm", new FormForList());
		map.put("mapCourse", studentService.mapCourse());			
		map.put("classList",studentService.getListClassByCourseId(0));
		map.put("ST_Schedule", studentService.generateST_Time(currentStudent));
		model.setViewName("student");
		return model;

	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerClass(@RequestParam("classid") String classId,Map<String, Object> map,
			final RedirectAttributes redirectAttributes){

		try{
			studentService.RegisterClassForStudent(currentStudent, classId);
			redirectAttributes.addFlashAttribute("msgRegister","You has been register one new class! See in Your Schedule");
			return "redirect:/student/home";
		}catch(Exception e){
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("msgRegister","Your register is not success! try again");
			return "redirect:/student/home";
		}
	}
	
	@RequestMapping(value="/updateProfile",method=RequestMethod.POST)
	public String updateProfile(@ModelAttribute("StudentForm")StudentFormAdd StudentForm,Map<String, Object> map,
			final RedirectAttributes redirectAttributes){
		
		try{
			boolean checkPoint = true;
			EmailValidator emailValidator = EmailValidator.getInstance();		
			 if(studentService.getInstanceUtilsApp().is_date1_affer_date2(studentService.getInstanceUtilsApp().
				 String_To_Date(StudentForm.getDateOfBirth()),studentService.getInstanceUtilsApp().getSystemDateTime())){
				 checkPoint = false;
				 map.put("timefuture", "The future time was choose, please choose time again!");
			 }
			 if(!emailValidator.isValid(StudentForm.getEmail())){
				 checkPoint = false;
				 map.put("emailformat", "Invalid Email! Try again!");
			 }
			 if(!studentService.isPhoneFormat(StudentForm.getPhoneNumber())){
				 checkPoint = false;
				 map.put("phoneformat", "Invalid phone number! Try again!");
			 }
			 if(checkPoint){	
				 try{	
						 studentService.saveorupdate(StudentForm);		
						 redirectAttributes.addFlashAttribute("message", "Student has been updated successfully!");
						 return "redirect:/student/home";
					 }catch(JDBCException jdbce){						 
						 redirectAttributes.addFlashAttribute("message", jdbce.getMessage());
						 return "redirect:/student/home";
					 }catch (Exception e) {
						 e.printStackTrace();
						 redirectAttributes.addFlashAttribute("message", e.getMessage());
						 return "redirect:/student/home";
					 }
			 }else{
				 redirectAttributes.addFlashAttribute("message", "fail to update student!");
				 return "redirect:/student/home";
			 }		 
		}
		catch(Exception e){
		 redirectAttributes.addFlashAttribute("message", e.getMessage());
		 return "redirect:/student/home";
		}
						
	}
	
	@RequestMapping(value="/ChangePassword",method=RequestMethod.POST)
	public String updatePassword(@RequestParam("username")String userName,@RequestParam("oldPassword")String oldPass,
			@RequestParam("newPassword")String newPass,final RedirectAttributes redirectAttributes){
		try{
			if(studentService.updatePassword(userName, oldPass, newPass)){
				redirectAttributes.addFlashAttribute("message", "New Password was update");
			}else{
				redirectAttributes.addFlashAttribute("message", "fail to update Password!");
			}
			
			return "redirect:/student/home";
		}catch(Exception e){
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/student/home";
		}
	}

}
