package com.nguyenvando.Services;
/**
 * @author Nguyen Van Do
 *
 */
import java.util.List;
import java.util.Map;


import com.nguyenvando.Entities.Address;
import com.nguyenvando.Entities.City;
import com.nguyenvando.Entities.District;
import com.nguyenvando.Entities.School;
import com.nguyenvando.Entities.Student;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Entities.UserRole;
import com.nguyenvando.Utils.MyAppUtil;
import com.nguyenvando.Utils.StudentFormAdd;

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
	public long countAllStudent();
	public Map<Integer, String> mapClass(String level);
	public Map<Integer, String> mapCity();
	public Map<Integer, String> mapDistrict(Integer cityId);
	public Map<Integer, String> mapSchool();
	public Map<String, String> mapGender();
	public MyAppUtil getInstanceUtilsApp();
	
}
