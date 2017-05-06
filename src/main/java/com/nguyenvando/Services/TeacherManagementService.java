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
import com.nguyenvando.Entities.District;
import com.nguyenvando.Entities.Skill;
import com.nguyenvando.Entities.Student;
import com.nguyenvando.Entities.Teacher;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Entities.UserRole;
import com.nguyenvando.Utils.TeacherFormAdd;

public interface TeacherManagementService {

	public List<Teacher> getAllTeacher();
	public Map<Integer, String> mapCity();
	public Map<Integer, String> mapDistrict(Integer cityId);
	public boolean isValidEmail(Teacher	tc,String searchColum,String searchValue);
	public Teacher getTeacherById(Integer id);
	public Teacher generateTeacher(TeacherFormAdd tc);
	public Address generateAddres(TeacherFormAdd tc);
	public City generateCity(TeacherFormAdd tc);
	public District generateDitrict(TeacherFormAdd tc);
	public Skill generateSkills(TeacherFormAdd tc);
	public User generateTCAccount(TeacherFormAdd tc);
	public UserRole generateUserRole(User tcAccount);
	public long countAllTeacher();
	//Add table to DB
	public void saveOrUpdateTeacher(TeacherFormAdd tc);
	public void setUserForTeacher(User tcAccount);
	public void setRoleForUSer(UserRole tcRole);
	//delete form DB
	public void deleteTeacher(Teacher tc);
	
}
