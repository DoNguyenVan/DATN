package com.nguyenvando.Services;
/**
 * @author Nguyen Van Do
 *
 */
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nguyenvando.Entities.Address;
import com.nguyenvando.Entities.City;
import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.District;
import com.nguyenvando.Entities.School;
import com.nguyenvando.Entities.Student;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Entities.UserRole;
import com.nguyenvando.Utils.CLASS_ST_Object;
import com.nguyenvando.Utils.MyAppUtil;
import com.nguyenvando.Utils.STUDENT_SHEDULE;
import com.nguyenvando.Utils.StudentFormAdd;
import com.nguyenvando.Utils.StudentFormUpdate;

public interface StudentManagementService{

	public List<Student> getDistinctListStudent();
	public List<Student> getListStudent();
	public Student generateStudent(StudentFormAdd stObject);
	public Address generateAddress(StudentFormAdd stObject);
	public City generateCity(StudentFormAdd stObject);
	public District generateDistrict(StudentFormAdd stObject);
	public User generateSTAccount(StudentFormAdd stObject);
	public UserRole generateUserRole(User user);
	public School genrateSchool(StudentFormAdd st);
	public Student getStudentById(Integer id);
	public void saveorupdate(StudentFormAdd st);
	public void setUserForStudent(User user);
	public void setRoleForUser(UserRole role);
	public void deleteStudent(Student student);
	public boolean isPhoneFormat(String phone );
	public boolean isValidEmail(Student st,String searchColum,String searchValue);
	public boolean isValidAccount(User account,String searchColum,String searchValue);
	public long countAllStudent();
	public Map<Integer, String> mapClass(String level,String idCourse);
	public Map<Integer, String> mapCity();
	public Map<Integer, String> mapDistrict(Integer cityId);
	public Map<Integer, String> mapSchool();
	public Map<String, String> mapGender();
	public MyAppUtil getInstanceUtilsApp();
	public StudentFormAdd generateFormStudent(Student student);
	public List<Class> generateSetToList(Set<Class> setclass);	
	
	//For student page
	public Student getStudentByAccount(String username);
	public Map<Integer, String> mapCourse();
	public List<Class> getListClassByCourseId(Integer courseId);
	public Map<Integer,CLASS_ST_Object> getClassByCourse(Integer courseId);
	public Map<Integer,CLASS_ST_Object> getClassByCourse(String classLevel, String courseId);
	public List<STUDENT_SHEDULE> generateST_Time(Student st);
	public void RegisterClassForStudent(Student st , String classId);
	public void paidFee(Integer classId, float feeValue, Integer studentId);
	public boolean updatePassword(String username,String oldPassword,String newPassword);
	public boolean setProfileImg(User stAccount, String path);
}
