package com.nguyenvando.Controller;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nguyenvando.Entities.Address;
import com.nguyenvando.Entities.City;
import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.Course;
import com.nguyenvando.Entities.Student;
import com.nguyenvando.Entities.Teacher;
import com.nguyenvando.Entities.Time;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Entities.UserRole;
import com.nguyenvando.Services.ClassManagementService;
import com.nguyenvando.Services.FinanceManagementService;
import com.nguyenvando.Services.StudentManagementService;
import com.nguyenvando.Services.TeacherManagementService;
import com.nguyenvando.Utils.ClassFormAdd;
import com.nguyenvando.Utils.FormFinance;
import com.nguyenvando.Utils.StudentFormAdd;
import com.nguyenvando.Utils.TeacherFormAdd;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.sun.xml.internal.ws.encoding.XMLHTTPBindingCodec;

@Controller
@EnableWebMvc
public class AdminManageController {

	@Autowired
	private ClassManagementService classService;

	@Autowired
	private StudentManagementService studentService;

	@Autowired
	private TeacherManagementService teacherService;
	
	@Autowired
	private FinanceManagementService financeService;
	
	private Class currentClass;
	private int currentClassId;

	@RequestMapping(value = "/AdminManagement")
	public ModelAndView adminPageHome(Map<String, Object> map) {
		ModelAndView model = new ModelAndView();
		map.put("classtotal", classService.countAllClass());
		map.put("classList",classService.class_St_List());
		map.put("stTotal", studentService.countAllStudent());
		map.put("tcTotal", teacherService.countAllTeacher());
		model.setViewName("AdminHome");
		return model;
	}

	/*-------------------------------------------------------------------------*/
	// Class Management Controller Start
	/*-------------------------------------------------------------------------*/
	@RequestMapping(value = "/listClass")
	public ModelAndView adminPageViewListClass(Map<String, Object> map) {
		ModelAndView model = new ModelAndView();
		map.put("listClass", classService.getAllClass());
		model.setViewName("ListClass");
		return model;
	}

	@RequestMapping(value = "/addClass")
	public ModelAndView viewAddClassPage(Map<String, Object> map) {
		ModelAndView model = new ModelAndView();
		map.put("classfee", classService.mapClassFee());
		map.put("classLevelList", classService.mapClassLevel());
		map.put("Courses", classService.getCourses());
		map.put("Teachers", classService.getTeachers());
		map.put("classForm", new ClassFormAdd());
		model.setViewName("AddClass");
		return model;
	}

	@RequestMapping(value = "/doAddClass", method = RequestMethod.POST)
	public ModelAndView doAddClass(@ModelAttribute("classForm") ClassFormAdd classForm, Map<String, Object> map,
			final RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();
		
		try {
			// Generate Class Entity
			currentClass = classService.generateClassEntity(classForm);
			boolean checkPoint = true;

			if (classService.IsValidClass(currentClass, "className", classForm.getClassName().trim())) {
				checkPoint = false;
				map.put("validname", "Class Name has been existed!");				
			}

			if (!classService.getInstanceUtilsApp().is_date1_affer_date2(
					classService.getInstanceUtilsApp().String_To_Date(classForm.getStartDate()),
					classService.getInstanceUtilsApp().getSystemDateTime())) {
				checkPoint = false;
				map.put("timeover", "The time start has been over, please choose time again!");
			}

			if (classForm.getNumberOfSeats() < 15) {
				checkPoint = false;
				map.put("maxNum", "NumberOfSeat has to larger than 15 number!");
			}

			if (classForm.getNumberOfSeats() > 40) {
				checkPoint = false;
				map.put("maxNum", "NumberOfSeat has to smaller than 50 number!");
			}
			if (classForm.getFee() <= 0) {
				checkPoint = false;
				map.put("feemsg", "Please setup class fee!");
			}


			if (checkPoint) {
				try {
					classService.saveorupdate(currentClass);
					currentClassId = classService.getMaxClassId(currentClass, "classId");
					//classObject = classService.getClassById(currentClassId);
					redirectAttributes.addFlashAttribute("success", "Class has been created successfully!");
					model.setViewName("redirect:/SetSchedule");
				} catch (JDBCException jdbce) {
					map.put("errorMessage", "Failed to add database! Please check your connection and try again!");
					model.setViewName("AddClass");
					map.put("classfee", classService.mapClassFee());
					map.put("classLevelList", classService.mapClassLevel());
					map.put("Courses", classService.getCourses());
					map.put("Teachers", classService.getTeachers());
					map.put("classForm", classForm);
				} catch (Exception e) {
					map.put("errorMessage", e.getMessage());
					model.setViewName("AddClass");
					map.put("classfee", classService.mapClassFee());
					map.put("classLevelList", classService.mapClassLevel());
					map.put("Courses", classService.getCourses());
					map.put("Teachers", classService.getTeachers());
					map.put("classForm", classForm);
				}

			} else {
				model.setViewName("AddClass");
				map.put("classfee", classService.mapClassFee());
				map.put("classLevelList", classService.mapClassLevel());
				map.put("Courses", classService.getCourses());
				map.put("Teachers", classService.getTeachers());
				map.put("classForm", classForm);
			}
		} catch (Exception e) {
			map.put("errorMessage", "Failed to add database!Please check your connection and try again!");
			model.setViewName("AddClass");
			map.put("classfee", classService.mapClassFee());
			map.put("classLevelList", classService.mapClassLevel());
			map.put("Courses", classService.getCourses());
			map.put("Teachers", classService.getTeachers());
			map.put("classForm", classForm);
		}
		return model;

	}

	@RequestMapping(value = "/SetSchedule")
	public ModelAndView SetSchedulePage(Model model, HttpServletRequest request) {
		if (request.getParameter("classid") != null) {
			currentClassId = Integer.parseInt(request.getParameter("classid"));
		}
		return this.formSetSchedule(model, currentClassId);
	}

	@RequestMapping(value = "/doAddTime", method = RequestMethod.GET)
	public String doAddTime(@RequestParam(name = "selectTime") String timeStr,
			final RedirectAttributes redirectAttributes) {
		try {
			classService.setTimeForClass(
					classService.generateTimeEntity(classService.getClassById(currentClassId), timeStr));

			redirectAttributes.addFlashAttribute("success", "Time has been created successfully!");
			return "redirect:/SetSchedule";
		} catch (Exception e) {
			return "redirect:/SetSchedule";
		}

	}

	@RequestMapping(value = "/doDeleteTime")
	public ModelAndView doDeleteTime(Model model, HttpServletRequest request) {
		if (request.getParameter("timeid") != null) {
			int timeId = Integer.parseInt(request.getParameter("timeid"));
			classService.deleteTime(classService.getTimeById(timeId));
		}
		return this.formSetSchedule(model, currentClassId);
	}

	@RequestMapping(value = "/doDeleteClass", method = RequestMethod.GET)
	public ModelAndView doDeleteClass(@RequestParam(name = "classid") String classId,
			final RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();
		if (classId != null) {
			try {
				Class classObjectForDelete = classService.getClassById(Integer.parseInt(classId));
				for (Time timeitem : classObjectForDelete.getTimeList()) {
					classService.deleteTime(timeitem);
				}
				classService.deleteClass(classObjectForDelete);
				redirectAttributes.addFlashAttribute("message", "Class has been deleted successfully!");
				model.setViewName("redirect:/listClass");
			} catch (JDBCException jdbce) {
				redirectAttributes.addFlashAttribute("message", jdbce.getMessage());
				model.setViewName("redirect:/listClass");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				model.setViewName("redirect:/listClass");
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "Not found Class!");
			model.setViewName("redirect:/listClass");
		}

		return model;
	}

	private ModelAndView formSetSchedule(Model model, int currentClassId) {
		currentClass = classService.getClassById(currentClassId);
		List<Time> timeList = classService.getTimeList(currentClass);
		ModelAndView view = new ModelAndView();
		model.addAttribute("className", currentClass.getClassName());
		model.addAttribute("schedule", timeList);
		view.setViewName("SetSchedule");
		return view;
	}

	/*-------------------------------------------------------------------------*/
	// Class Management Controller End
	/*-------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------*/
	// Student Management Controller Start
	/*-------------------------------------------------------------------------*/
	@RequestMapping(value = "/listStudent")
	public ModelAndView adminPageViewListStudent(Map<String, Object> map) {
		ModelAndView model = new ModelAndView();
		map.put("listStudent", studentService.getListStudent());
		model.setViewName("ListStudent");
		return model;
	}

	@RequestMapping(value = "/addStudent")
	public ModelAndView viewAddStudentPage(Map<String, Object> map) {
		ModelAndView model = new ModelAndView();
		map.put("StudentForm", new StudentFormAdd());
		map.put("ClassMap", studentService.mapClass(null)); 
		map.put("classLevel", classService.mapClassLevel());
		map.put("cityMap", studentService.mapCity());
		map.put("districtMap", studentService.mapDistrict(0));
		map.put("schoolMap", studentService.mapSchool());
		model.setViewName("AddStudent");
		return model;
	}

	@ResponseBody
	@RequestMapping(value="/changeClassList",method=RequestMethod.GET)
	public Map<Integer,String> getMapClass(@RequestParam(name="data")String data){		
		return studentService.mapClass(data);
	}
	
	@RequestMapping(value = "/doAddStudent", method = RequestMethod.POST)
	public ModelAndView doAddStudent(@ModelAttribute("StudentForm")StudentFormAdd st, Map<String, Object> map,
			final RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();		
		
		try{
			boolean checkPoint = true;
			EmailValidator emailValidator = EmailValidator.getInstance();		
			 if(studentService.getInstanceUtilsApp().is_date1_affer_date2(studentService.getInstanceUtilsApp().
				 String_To_Date(st.getDateOfBirth()),studentService.getInstanceUtilsApp().getSystemDateTime())){
				 checkPoint = false;
				 map.put("timefuture", "The future time was choose, please choose time again!");
			 }
			 if(!emailValidator.isValid(st.getEmail())){
				 checkPoint = false;
				 map.put("emailformat", "Invalid Email! Try again!");
			 }
			 if(studentService.isValidEmail(new Student(),"email",st.getEmail().trim()) ||
					 teacherService.isValidEmail(new Teacher(), "email", st.getEmail())){
				 checkPoint = false;
				 map.put("emailformat", "This email has been registered!");
			 }
			 if(!studentService.isPhoneFormat(st.getPhoneNumber())){
				 checkPoint = false;
				 map.put("phoneformat", "Invalid phone number! Try again!");
			 }
			 if(checkPoint){	
				 try{					 
						 studentService.saveorupdate(st);		
						 redirectAttributes.addFlashAttribute("message", "Student has been created successfully!");
						 model.setViewName("redirect:/listStudent");
					 }catch(JDBCException jdbce){
						 map.put("message", jdbce.getMessage());
						 return this.formAddStudent(map, st);
					 }catch (Exception e) {
						map.put("message", e.getMessage());
						return this.formAddStudent(map, st);
					 }
			 }else{
				 return this.formAddStudent(map, st);
			 }		 
		}
		catch(Exception e){
		 map.put("message", e.getMessage());
		 return this.formAddStudent(map, st);
		}
		return model;
	}
	
	@RequestMapping(value="/doDeleteStudent",method=RequestMethod.GET)
	public ModelAndView doDeleteStudent(@RequestParam(name = "studentid") String studentid,
			final RedirectAttributes redirectAttributes){
		ModelAndView model = new ModelAndView();
		if (studentid != null) {
			try {
				Student st = studentService.getStudentById(Integer.parseInt(studentid));
				studentService.deleteStudent(st);
				redirectAttributes.addFlashAttribute("message", "Student has been deleted successfully!");
				model.setViewName("redirect:/listStudent");
			} catch (JDBCException jdbce) {
				redirectAttributes.addFlashAttribute("message", jdbce.getMessage());
				model.setViewName("redirect:/listStudent");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				model.setViewName("redirect:/listStudent");
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "Not found Student!");
			model.setViewName("redirect:/listStudent");
		}

		return model;
	}
	
	private ModelAndView formAddStudent(Map<String, Object> map,StudentFormAdd st ) {
		ModelAndView view = new ModelAndView();
		map.put("StudentForm", st);
		map.put("ClassMap", studentService.mapClass(null)); 
		map.put("classLevel", classService.mapClassLevel());
		map.put("cityMap", studentService.mapCity());
		map.put("districtMap", studentService.mapDistrict(0));
		map.put("schoolMap", studentService.mapSchool());
		view.setViewName("AddStudent");
		return view;
	}

	/*-------------------------------------------------------------------------*/
	// Student Management Controller End
	/*-------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------*/
	// Teacher Management Controller Start
	/*-------------------------------------------------------------------------*/
	@RequestMapping(value = "/listTeacher")
	public ModelAndView adminPageViewListTeacher(Map<String, Object> map) {
		ModelAndView model = new ModelAndView();
		map.put("listTeacher", teacherService.getAllTeacher() );
		model.setViewName("ListTeacher");
		return model;
	}
	
	@RequestMapping(value="/addTeacher")
	public ModelAndView formAddTeacher(Map<String, Object> map){
		ModelAndView model = new ModelAndView();
		map.put("TeacherForm", new TeacherFormAdd());
		map.put("cityMap", teacherService.mapCity());
		map.put("districtMap", teacherService.mapDistrict(0));
		model.setViewName("AddTeacher");
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/ChangeDistrictList",method=RequestMethod.GET)
	public Map<Integer,String> changeDistrict (@RequestParam(name = "data") String data) throws ParseException {
		return  teacherService.mapDistrict(Integer.parseInt(data));
	}
	
	@RequestMapping(value="/doAddTeacher",method = RequestMethod.POST)
	public ModelAndView doAddTeacer(@ModelAttribute("TeacherForm")TeacherFormAdd tc, Map<String, Object> map,
			final RedirectAttributes redirectAttributes,HttpServletRequest request
			)throws IOException,IllegalArgumentException{
		
		ModelAndView model = new ModelAndView();
		try{
			boolean checkPoint = true;
			EmailValidator emailValidator = EmailValidator.getInstance();		
			 if(studentService.getInstanceUtilsApp().is_date1_affer_date2(studentService.getInstanceUtilsApp().
				 String_To_Date(tc.getDateOfBirth()),studentService.getInstanceUtilsApp().getSystemDateTime())){
				 checkPoint = false;
				 map.put("timefuture", "The future time was choose, please choose time again!");
			 }
			 if(!emailValidator.isValid(tc.getEmail())){
				 checkPoint = false;
				 map.put("emailformat", "Invalid Email! Try again!");
			 }
			 if(studentService.isValidEmail(new Student(),"email",tc.getEmail().trim()) || 
					 teacherService.isValidEmail(new Teacher(), "email",tc.getEmail().trim())){
				 checkPoint = false;
				 map.put("emailformat", "This email has been registered!");
			 }
			 if(!studentService.isPhoneFormat(tc.getPhoneNumber())){
				 checkPoint = false;
				 map.put("phoneformat", "Invalid phone number! Try again!");
			 }
			 if(!tc.getFile().isEmpty()){
				 MultipartFile file = tc.getFile();
				 try {
					    InputStream inputStream = file.getInputStream();    
					    if (inputStream == null)
					     System.out.println("File inputstream is null");  
					    // cach 2 - upload vao thu muc
					    String path = request.getSession().getServletContext().getRealPath("/") + "Upload/TeacherResource/";
					    System.out.println("path :"+path);
					    FileUtils.forceMkdir(new File(path));
					    File upload = new File (path + file.getOriginalFilename());
//					    if(!upload.exists()){
//					    	upload.mkdir();
//						}
					    file.transferTo(upload);
					    String imagePath = request.getContextPath() + "/Upload/TeacherResource/" + file.getOriginalFilename();
					    tc.setUrlCertificate(imagePath);
					    IOUtils.closeQuietly(inputStream);
					   } catch (IOException ex) {
					    System.out.println("Error saving uploaded file");
					   }
			 }
			 			 
			 if(checkPoint){
				 try{
					 teacherService.saveOrUpdateTeacher(tc);
					 redirectAttributes.addFlashAttribute("message", "Teacher has been created successfully!");
					 model.setViewName("redirect:/listTeacher");
				 }catch(JDBCException jdbce){
					map.put("message", jdbce.getMessage());
					return this.formAddTeacher(map, tc);
				 }
				 catch(Exception e){
					 map.put("message", e.getMessage());
					 return this.formAddTeacher(map, tc);
				 }
				 
			 }else{
				 map.put("message", "cannot save to database1");
				 return this.formAddTeacher(map, tc);
			 }		
		}catch(Exception e){
			 map.put("message", e.getMessage());
			return this.formAddTeacher(map, tc);
		}				
		return model;
	}
	
	@RequestMapping(value="/doDeleteTeacher",method=RequestMethod.GET)
	public ModelAndView doDeleteTeacher(@RequestParam(name = "teacherid") String teacherid,
			final RedirectAttributes redirectAttributes){
		ModelAndView model = new ModelAndView();
		if (teacherid != null) {
			try {
				Teacher  tc = teacherService.getTeacherById(Integer.parseInt(teacherid));
				teacherService.deleteTeacher(tc);
				redirectAttributes.addFlashAttribute("message", "Techer has been deleted successfully!");
				model.setViewName("redirect:/listTeacher");
			} catch (JDBCException jdbce) {
				redirectAttributes.addFlashAttribute("message", jdbce.getMessage());
				model.setViewName("redirect:/listTeacher");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				model.setViewName("redirect:/listTeacher");
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "Not found Teacher!");
			model.setViewName("redirect:/listTeacher");
		}

		return model;
	}
	
	private ModelAndView formAddTeacher(Map<String, Object> map,TeacherFormAdd tc ){
		ModelAndView view = new ModelAndView();
		map.put("TeacherForm",tc);
		map.put("cityMap", teacherService.mapCity());
		map.put("districtMap", teacherService.mapDistrict(0));
		view.setViewName("AddTeacher");
		return view;
	}
	/*-------------------------------------------------------------------------*/
	// Teacher Management Controller End
	/*-------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------*/
	// Register Class Management Controller Start
	/*-------------------------------------------------------------------------*/
	@RequestMapping(value="/Register")
	public ModelAndView getRegisterPage(){
		ModelAndView view =  new ModelAndView();
		view.setViewName("RegisterClass");
		return view;
	}
	/*-------------------------------------------------------------------------*/
	// Register Class Management Controller End
	/*-------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------*/
	//  Finance Management ControllerStart
	/*-------------------------------------------------------------------------*/
	@RequestMapping(value="/Finance")
	public ModelAndView getFinanceManagementPage(Map<String, Object> map){
		ModelAndView view =  new ModelAndView();
		map.put("FinanceForm", new FormFinance());
		map.put("mapMonths", financeService.mapMonths());
		view.setViewName("Finance");
		return view;
	}
	
}
