package com.nguyenvando.Controller;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.Salary;
import com.nguyenvando.Entities.SchoolFee;
import com.nguyenvando.Entities.Skill;
import com.nguyenvando.Entities.Student;
import com.nguyenvando.Entities.Teacher;
import com.nguyenvando.Entities.Time;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Services.ClassManagementService;
import com.nguyenvando.Services.FinanceManagementService;
import com.nguyenvando.Services.StudentManagementService;
import com.nguyenvando.Services.TeacherManagementService;
import com.nguyenvando.Utils.ClassFormAdd;
import com.nguyenvando.Utils.FormFinance;
import com.nguyenvando.Utils.FormForList;
import com.nguyenvando.Utils.MyAppUtil;
import com.nguyenvando.Utils.Statistic;
import com.nguyenvando.Utils.StudentFormAdd;
import com.nguyenvando.Utils.StudentFormUpdate;
import com.nguyenvando.Utils.TeacherFormAdd;
import com.nguyenvando.Utils.urlPathForController;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@Controller
@EnableWebMvc
@RequestMapping(value = "/admin")
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

	@RequestMapping(value = "/AdminManagement",method=RequestMethod.GET)
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
	//Check Attendance Controller Start
	@RequestMapping(value="/CheckAttendace")
	public ModelAndView admincheckAttendace(Map<String, Object> map,@RequestParam("classId") String classId){
		ModelAndView model = new ModelAndView();
		currentClassId = Integer.parseInt(classId);
		currentClass = classService.getClassById(currentClassId);	
		List<Student> list = classService.generateSetToList(currentClass.getStList());
		map.put("StOfClass",list );	
		map.put("ClassName", currentClass.getClassName());
		model.setViewName("CheckAttendance");
		return model;
	}
	@RequestMapping(value="/doDeleteSTFormClass",method=RequestMethod.GET)
	public String doDeleteSTFormClass(Map<String, Object> map,@RequestParam("studentid") String studentId,
			final RedirectAttributes redirectAttributes){
		try{
			classService.removeSTFormClass(currentClass, Integer.parseInt(studentId));
			redirectAttributes.addFlashAttribute("message", "Student has been deleted!");
			return "redirect:/admin/CheckAttendace?classId="+currentClassId;
		}catch(Exception e){
			redirectAttributes.addFlashAttribute("message", "cannot delete Student Form Class!");
			return "redirect:/admin/CheckAttendace?classId="+currentClassId;
		}
			
	}
	/*-------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------*/
	// Class Management Controller Start
	/*-------------------------------------------------------------------------*/
	@RequestMapping(value = "/listClass")
	public ModelAndView adminPageViewListClass(Map<String, Object> map) {
		ModelAndView model = new ModelAndView();
		map.put("listClass", classService.getDistinctListClass());
		map.put("search_course", new FormForList());
		map.put("listCourse",classService.getCourse());
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

	@RequestMapping(value="/addCourse",method=RequestMethod.POST)
	public String doAddCourse(@RequestParam("coursename") String courseName,@RequestParam("timeline")String timeLine,@RequestParam("note")String note,
		   Map<String, Object> map,final RedirectAttributes redirectAttributes){
		
		try{			
			classService.saveCourse(courseName, timeLine, note);
			redirectAttributes.addFlashAttribute("errorMessage", "New Course has been created!");
		}catch(Exception e){
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}				
		return "redirect:/admin/addClass";
	}
	
	@RequestMapping(value = "/doAddClass", method = RequestMethod.POST)
	public ModelAndView doAddClass(@ModelAttribute("classForm") ClassFormAdd classForm, Map<String, Object> map,
			final RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();
		
		try {
			// Generate Class Entity
			
			byte[]bytes = classForm.getClassName().getBytes(StandardCharsets.ISO_8859_1);
			classForm.setClassName(new String(bytes,StandardCharsets.UTF_8));		
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
					model.setViewName("redirect:/admin/SetSchedule");
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
					classService.generateTimeEntity(classService.getClassById(currentClassId), timeStr),currentClassId);

			redirectAttributes.addFlashAttribute("success", "Time has been created successfully!");
			return "redirect:/admin/SetSchedule";
		} catch (Exception e) {
			return "redirect:/admin/SetSchedule";
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
				model.setViewName("redirect:/admin/listClass");
			} catch (JDBCException jdbce) {
				redirectAttributes.addFlashAttribute("message", jdbce.getMessage());
				model.setViewName("redirect:/admin/listClass");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				model.setViewName("redirect:/admin/listClass");
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "Not found Class!");
			model.setViewName("redirect:/admin/listClass");
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
		map.put("listStudent", studentService.getDistinctListStudent());
		model.setViewName("ListStudent");
		return model;
	}

	@RequestMapping(value = "/addStudent")
	public ModelAndView viewAddStudentPage(Map<String, Object> map) {
		ModelAndView model = new ModelAndView();
		map.put("StudentForm", new StudentFormAdd());
		map.put("ClassMap", studentService.mapClass(null,null)); 
		map.put("classLevel", classService.mapClassLevel());
		map.put("cityMap", studentService.mapCity());
		map.put("districtMap", studentService.mapDistrict(0));
		map.put("schoolMap", studentService.mapSchool());
		map.put("genderMap", studentService.mapGender());
		map.put("search_course", new FormForList());
		map.put("listCourse",classService.getCourse());
		model.setViewName("AddStudent");
		return model;
	}
	
	@RequestMapping(value = "/doAddStudent", method = RequestMethod.POST)
	public ModelAndView doAddStudent(@ModelAttribute("StudentForm")StudentFormAdd st,
			@ModelAttribute("search_course")FormForList ff, Map<String, Object> map,
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
						 model.setViewName("redirect:/admin/listStudent");
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
	
	@RequestMapping(value="/editStudent",method=RequestMethod.GET)
	public ModelAndView editStudentForm(@RequestParam(name="studentid") String studentId,Map<String, Object> map){	
		
		Student st = studentService.getStudentById(Integer.parseInt(studentId));
		StudentFormAdd stFormUpdate = studentService.generateFormStudent(st);	
		
		return this.formEditStudent(map,stFormUpdate);
		
	}
	
	@RequestMapping(value="/updateStudent",method=RequestMethod.POST)
	public ModelAndView updateStudent(@ModelAttribute("StudentForm")StudentFormAdd StudentForm,Map<String, Object> map,
			final RedirectAttributes redirectAttributes){
		
		ModelAndView model = new ModelAndView();
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
						 model.setViewName("redirect:/admin/listStudent");
						 return model;
					 }catch(JDBCException jdbce){
						 map.put("message", jdbce.getMessage());
						 return this.formEditStudent(map, StudentForm);
					 }catch (Exception e) {
						map.put("message", e.getMessage());
						return this.formEditStudent(map, StudentForm);
					 }
			 }else{
				 redirectAttributes.addFlashAttribute("message", "fail to update student!");
				 return this.formEditStudent(map, StudentForm);
			 }		 
		}
		catch(Exception e){
		 map.put("message", e.getMessage());
		 return this.formEditStudent(map, StudentForm);
		}			
		
	}
	
	@RequestMapping(value="/doDeleteStudent",method=RequestMethod.POST)
	public ModelAndView doDeleteStudent(@RequestParam(name = "studentid") String studentid,
			final RedirectAttributes redirectAttributes){
		ModelAndView model = new ModelAndView();
		if (studentid != null) {
			try {
				Student st = studentService.getStudentById(Integer.parseInt(studentid));
				studentService.deleteStudent(st);
				redirectAttributes.addFlashAttribute("message", "Student has been deleted successfully!");
				model.setViewName("redirect:/admin/listStudent");
			} catch (JDBCException jdbce) {
				redirectAttributes.addFlashAttribute("message", jdbce.getMessage());
				model.setViewName("redirect:/admin/listStudent");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				model.setViewName("redirect:/admin/listStudent");
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "Not found Student!");
			model.setViewName("redirect:/admin/listStudent");
		}

		return model;
	}
	
	private ModelAndView formAddStudent(Map<String, Object> map,StudentFormAdd st ) {
		ModelAndView view = new ModelAndView();		
		map.put("StudentForm", st);
		map.put("ClassMap", studentService.mapClass(null,null)); 
		map.put("classLevel", classService.mapClassLevel());
		map.put("cityMap", studentService.mapCity());
		map.put("districtMap", studentService.mapDistrict(0));
		map.put("schoolMap", studentService.mapSchool());
		map.put("genderMap", studentService.mapGender());
		map.put("search_course", new FormForList());
		map.put("listCourse",classService.getCourse());
		view.setViewName("AddStudent");
		return view;
	}

	private ModelAndView formEditStudent(Map<String, Object> map,StudentFormAdd st ) {
		ModelAndView view = new ModelAndView();
		
		map.put("StudentForm", st);
		map.put("ClassMap", studentService.mapClass(null,null)); 
		map.put("stclassList", studentService.generateSetToList(st.getClassListOfST()));
		map.put("cityMap", studentService.mapCity());
		map.put("districtMap", studentService.mapDistrict(st.getCity()));
		map.put("schoolMap", studentService.mapSchool());
		view.setViewName("editStudent");
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
			 if (studentService.isValidAccount(new User(), "username", tc.getUserName())) {
				 checkPoint = false;
				 map.put("validAccount", "This Account has been registered!");
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
					 model.setViewName("redirect:/admin/listTeacher");
				 }catch(JDBCException jdbce){
					map.put("message", jdbce.getMessage());
					return this.formAddTeacher(map, tc);
				 }
				 catch(Exception e){
					 map.put("message", e.getMessage());
					 return this.formAddTeacher(map, tc);
				 }
				 
			 }else{
				 map.put("message", "cannot save into database");
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
				model.setViewName("redirect:/admin/listTeacher");
			} catch (JDBCException jdbce) {
				redirectAttributes.addFlashAttribute("message", jdbce.getMessage());
				model.setViewName("redirect:/admin/listTeacher");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				model.setViewName("redirect:/admin/listTeacher");
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "Not found Teacher!");
			model.setViewName("redirect:/admin/listTeacher");
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
		map.put("mapYear", financeService.mapYears());
		view.setViewName("Finance");
		return view;
	}	
	
	@RequestMapping(value="/statistics",method=RequestMethod.POST)
	public ModelAndView doSatistics(@ModelAttribute("FinanceForm")FormFinance financeForm,
			@RequestParam("searchValue")String searchValue,Map<String, Object> map){
		try{
			
			if("month".equals(searchValue)){
				if(financeForm.getMonth1()>financeForm.getMonth2()){
					map.put("error","Month Form < Month To!");
					map.put("searchValue", searchValue);
					return this.adminManageFinance_Error(map, new FormFinance());
				}else{
					String date1 = financeService.generate_Month_date1(financeForm);
					String date2 =  financeService.generate_Month_date2(financeForm);
					List<Salary> salary = financeService.getSalaryFrom_To("datePaid",date1,date2);
					List<SchoolFee> schoolFee = financeService.getSchoolFeeForm_To("datePaid", date1, date2);
					List<Statistic>statistics = new ArrayList<>();					
					float total1 = 0; float total2=0; 
					for (SchoolFee fee : schoolFee) {
						Statistic statistic = new Statistic();
						statistic.setActivity("Thu Học Phí");
						statistic.setProceeds(fee.getFeeValue());
						statistic.setDate(financeService.getInstanceUtilsApp().Date_To_String(fee.getDatePaid()));
						statistics.add(statistic);
						total1 += statistic.getProceeds();
					}
					
					for (Salary sa : salary) {
						Statistic statistic = new Statistic();
						statistic.setActivity("Trả Lương");
						statistic.setPayouts(sa.getMoney());
						statistic.setDate(financeService.getInstanceUtilsApp().Date_To_String(sa.getDatePaid()));
						statistics.add(statistic);
						total2 += statistic.getPayouts();
					}
					
					
					map.put("statistic", statistics);
					map.put("total1", total1);
					map.put("total2", total2);
					map.put("total3", total1-total2);
					map.put("searchValue", searchValue);
					return this.adminManageFinance_Month(map, financeForm);
				}
				
			}
			else if("year".equals(searchValue)){
				if(financeForm.getMonth3()>financeForm.getMonth4()){
					map.put("error","Month Form < Month To!");
					map.put("searchValue", searchValue);
					return this.adminManageFinance_Error(map, new FormFinance());
				}else{
					String date1 = financeService.generate_Year_date1(financeForm);
					String date2 = financeService.generate_Year_date2(financeForm);
					List<Salary> salary = financeService.getSalaryFrom_To("datePaid",date1,date2);
					List<SchoolFee> schoolFee = financeService.getSchoolFeeForm_To("datePaid", date1, date2);
					List<Statistic>statistics = new ArrayList<>();					
					float total1 = 0; float total2=0; 
					for (SchoolFee fee : schoolFee) {
						Statistic statistic = new Statistic();
						statistic.setActivity("Thu Học Phí");
						statistic.setProceeds(fee.getFeeValue());
						statistic.setDate(financeService.getInstanceUtilsApp().Date_To_String(fee.getDatePaid()));
						statistics.add(statistic);
						total1 += statistic.getProceeds();
					}
					
					for (Salary sa : salary) {
						Statistic statistic = new Statistic();
						statistic.setActivity("Trả Lương");
						statistic.setPayouts(sa.getMoney());
						statistic.setDate(financeService.getInstanceUtilsApp().Date_To_String(sa.getDatePaid()));
						statistics.add(statistic);
						total2 += statistic.getPayouts();
					}
										
					map.put("statistic", statistics);
					map.put("total1", total1);
					map.put("total2", total2);
					map.put("total3", total1-total2);
					map.put("searchValue", searchValue);					
					return this.adminManageFinance_Year(map, financeForm);

				}
			}			
			
		}catch(Exception e){
			map.put("searchValue", searchValue);
			map.put("error", e.getMessage());				
		}
		return this.adminManageFinance_Error(map, new FormFinance());		
	}
	
	private ModelAndView adminManageFinance_Error(Map<String, Object> map,FormFinance financeForm) {
		ModelAndView view = new ModelAndView();
		map.put("mapMonths", financeService.mapMonths());
		map.put("mapYear", financeService.mapYears());
		map.put("errors","Cannot find Data!");
		view.setViewName("Finance");
		return view;
	}
	
	private ModelAndView adminManageFinance_Month(Map<String, Object> map,FormFinance financeForm) {
		ModelAndView view = new ModelAndView();
		map.put("FinanceForm", financeForm);
		map.put("mapMonths", financeService.mapMonths());
		map.put("mapYear", financeService.mapYears());
		view.setViewName("Finance");
		return view;
	}
	private ModelAndView adminManageFinance_Year(Map<String, Object> map,FormFinance financeForm) {
		ModelAndView view = new ModelAndView();
		FormFinance fin = new FormFinance(0,0,0,financeForm.getMonth3(),financeForm.getMonth4());
		map.put("FinanceForm", fin);
		map.put("mapMonths", financeService.mapMonths());
		map.put("mapYear", financeService.mapYears());
		view.setViewName("Finance");
		return view;
	}
	
	

}
