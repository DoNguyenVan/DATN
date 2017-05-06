/**
 * 
 */
package com.nguyenvando.Services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvando.Dao.MyAppDao;
import com.nguyenvando.Entities.Address;
import com.nguyenvando.Entities.City;
import com.nguyenvando.Entities.District;
import com.nguyenvando.Entities.Skill;
import com.nguyenvando.Entities.Teacher;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Entities.UserRole;
import com.nguyenvando.Utils.TeacherFormAdd;

/**
 * @author Nguyen Van Do
 *
 */
@Service
public class TeacherManagementServiceImpl implements TeacherManagementService {

	@Autowired
	private MyAppDao myappdao;
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public List<Teacher> getAllTeacher() {
		return myappdao.getList(Teacher.class);
	}

	@Transactional
	@Override
	public Map<Integer, String> mapCity() {
		List<City> getList = myappdao.getList(City.class);
		Map<Integer, String> returnmap = new HashMap<>();
		returnmap.put(0, "--------Select City-------");
		for (City city : getList) {
			returnmap.put(city.getCityId(), city.getCityName());
		}
		return returnmap;

	}

	@Override
	public boolean isValidEmail(Teacher tc, String searchColum, String searchValue) {
		if(myappdao.IsValidObject(tc, searchColum , searchValue)){
			 return true;	
		}
		return false;

	}

	@Override
	public Teacher generateTeacher(TeacherFormAdd tc) {
		Teacher teacher = new Teacher();
		try{
			teacher.setFullName(tc.getFullName());
			teacher.setDateOfBirth(tc.getDateOfBirth());
			teacher.setPhoneNumber(tc.getPhoneNumber());
			teacher.setEmail(tc.getEmail());			
		}catch(Exception e){
			teacher = null;
		}	
		return teacher;
	}

	@Override
	public Address generateAddres(TeacherFormAdd tc) {
		Address address	= new Address();
		try{
			City city = generateCity(tc);
			District district = generateDitrict(tc);
			if(city != null){
				address.setCity(city);
				address.setDistrict(district);
			}					
		}catch(Exception e){
			address = null;
		}
				
		return address;
	}

	@Transactional
	@Override
	public City generateCity(TeacherFormAdd tc) {
		try{
			City  city = myappdao.getEntityById(City.class, tc.getCity());
			return city;
		}catch (Exception e){
			return null;
		}		
	}

	@Override
	public Skill generateSkills(TeacherFormAdd tc) {
		Skill skill = new Skill();
		try{
			skill.setSkillName(tc.getSkillName());
			skill.setNote(tc.getNote());
			skill.setExperience(tc.getExperiment());
			skill.setUrlCertificateSkill(tc.getUrlCertificate());		
			return skill;
		}catch(Exception e){
			return skill;
		}
		
	}

	@Override
	public User generateTCAccount(TeacherFormAdd tc) {
		User tcAccount = new User();
		try{
			tcAccount.setEnabled(true);
			passwordEncoder = new BCryptPasswordEncoder();
			tcAccount.setPassword(passwordEncoder.encode(tc.getPassword()));
			tcAccount.setUsername(tc.getUserName());
			Set<UserRole> roles = new HashSet<>();
			roles.add(generateUserRole(tcAccount));
			tcAccount.setUserRole(roles);
			return tcAccount;
		}catch(Exception e){
			return null;
		}	
	}

	@Override
	public UserRole generateUserRole(User tcAccount) {
		UserRole tcRole = new UserRole();
		try{
			tcRole.setRole("TEACHER");
			tcRole.setUser(tcAccount);			
			return tcRole;
		}catch(Exception e){
			return null;
		}	
	}

	@Transactional
	@Override
	public void saveOrUpdateTeacher(TeacherFormAdd tc) {
		try{
		    Teacher teacher =  generateTeacher(tc);
			User tcAccount = generateTCAccount(tc);
			UserRole tcRole = generateUserRole(tcAccount);
			City city = generateCity(tc);
			District district = generateDitrict(tc);
			List<Address> addressList = myappdao.getList(Address.class);
			Address tcAddress = new Address();
			for (Address addrr : addressList) {
				if(addrr.getCity() == city && addrr.getDistrict() == district){
					tcAddress = addrr;
					break;
				}else{
					tcAddress = generateAddres(tc);
				}
			}
			if(tcAddress !=null){
				myappdao.insertOrUpdate(tcAddress);
				teacher.setStAddress(tcAddress);
			}
			// Set tcAccount into DB
			setUserForTeacher(tcAccount);
			setRoleForUSer(tcRole);
			teacher.setTcAccount(tcAccount);
			// insert teacher into database
			myappdao.insertOrUpdate(teacher);	
			//Set Skill for teacher
			teacher.getTcSkill().add(generateSkills(tc));
			teacher.setTcSkill(teacher.getTcSkill());
			//Add Skill of teacher into db
			Skill skill = generateSkills(tc);
			skill.setTcSkill(teacher);
			myappdao.insertOrUpdate(skill);

		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}

	@Transactional
	@Override
	public void setUserForTeacher(User tcAccount) {
		myappdao.insertOrUpdate(tcAccount);
	}

	@Transactional
	@Override
	public void setRoleForUSer(UserRole tcRole) {
		myappdao.insertOrUpdate(tcRole);		
	}

	@Transactional
	@Override
	public Teacher getTeacherById(Integer id) {
		return myappdao.getEntityById(Teacher.class, id);
	}

	@Transactional 
	@Override
	public void deleteTeacher(Teacher tc) {
		Set<Skill> tcSkills = tc.getTcSkill();
		for (Skill skill : tcSkills) {
			myappdao.deleteEntity(skill);
		}		
		myappdao.deleteEntity(tc);
	}

	@Transactional
	@Override
	public Map<Integer, String> mapDistrict(Integer cityId) {
	   Map<Integer,String> map = new HashMap<>();
	   try{
		   map.put(0, "-------Select District-------");
		   if(cityId !=0){
			  City city = myappdao.getEntityById(City.class, cityId);
			  List<District> districtList = (List<District>) city.getDistrict();			 
			  for (District district : districtList) {
				map.put(district.getDistrictId(), district.getDistrictName());
			  }
		    }
		   }catch (Exception e) {
			e.printStackTrace();
	    }
	    return map;
	}

	@Transactional
	@Override
	public District generateDitrict(TeacherFormAdd tc) {
		return myappdao.getEntityById(District.class, tc.getDistrict());
	}

	@Transactional
	@Override
	public long countAllTeacher() {	
		return myappdao.countAllEntities(Teacher.class);
	}



}
