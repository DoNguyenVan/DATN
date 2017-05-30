package com.nguyenvando.Controller;

import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nguyenvando.Entities.Student;
import com.nguyenvando.Entities.Teacher;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Services.StudentManagementService;
import com.nguyenvando.Services.TeacherManagementService;
import com.nguyenvando.Utils.StudentFormAdd;


@Controller
@EnableWebMvc
public class HomeController {
	@Autowired
	private StudentManagementService studentService;
	@Autowired 
	private TeacherManagementService teacherService;
	
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

	@RequestMapping(value="/doAddStudentFormLogin",method=RequestMethod.POST)
	public String formLoginDoAddStudent(@ModelAttribute("STF") StudentFormAdd studentForm,
			final RedirectAttributes redirectAttributes){

		try{
			EmailValidator emailValidator = EmailValidator.getInstance();	
			boolean checkpoint = true;
			if(null == studentForm.getGender()){
				checkpoint = false;
				redirectAttributes.addFlashAttribute("gender", "please choose gender!");
			}
			if(!studentService.isPhoneFormat(studentForm.getPhoneNumber())){
				checkpoint = false;
				redirectAttributes.addFlashAttribute("format_phone", "phone is not correct!");
			}	
			if(!emailValidator.isValid(studentForm.getEmail())){
				checkpoint = false;
				redirectAttributes.addFlashAttribute("format_email", "email is not correct!");
			}
			if(studentService.isValidEmail(new Student(), "email", studentForm.getEmail()) ||
					teacherService.isValidEmail(new Teacher(), "email", studentForm.getEmail())){
				checkpoint = false;
				redirectAttributes.addFlashAttribute("valid_email", "email has been existed!");
			}
			if(studentService.isValidAccount(new User(), "username",studentForm.getUserName().trim())){
				checkpoint = false;
				redirectAttributes.addFlashAttribute("valid_account", "account has been existed!");
			}
			if(studentService.getInstanceUtilsApp().is_date1_affer_date2(studentService.getInstanceUtilsApp().
				 String_To_Date(studentForm.getDateOfBirth()),studentService.getInstanceUtilsApp().getSystemDateTime())){
				checkpoint = false;
				redirectAttributes.addFlashAttribute("timeFuture", "birthday cannot the date in the future!");
			}
						
			// do if all condition is correct
			if(checkpoint){
				studentService.saveorupdate(studentForm);
				redirectAttributes.addFlashAttribute("msg_info", "new student has been created");				
				return "redirect:/";
			}else{
				redirectAttributes.addFlashAttribute("msg_info", "cannot create student!");
				return "redirect:/";
			}						
			
		}catch(Exception e){
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("msg_info", "cannot create student!");
			return "redirect:/";
		}
				
	}
}
