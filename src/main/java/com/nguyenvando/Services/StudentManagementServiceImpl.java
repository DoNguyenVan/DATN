package com.nguyenvando.Services;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.integrator.spi.Integrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvando.Dao.MyAppDao;
import com.nguyenvando.Entities.Address;
import com.nguyenvando.Entities.City;
import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.District;
import com.nguyenvando.Entities.School;
import com.nguyenvando.Entities.Student;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Entities.UserRole;
import com.nguyenvando.Utils.MyAppUtil;
import com.nguyenvando.Utils.StudentFormAdd;

@Service
public class StudentManagementServiceImpl implements StudentManagementService {

	@Autowired
	private MyAppDao myappdao;
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public List<Student> getDistinctListStudent() {
		return myappdao.getDistinctList(Student.class);
	}
	
	@Transactional
	@Override
	public List<Student> getListStudent() {		
		return myappdao.getList(Student.class);
	}
	
	@Override
	public Student generateStudent(StudentFormAdd stObject) {

		Student st = new Student();
		try{
			// convert to UTF-8
			
			byte[] fullName = stObject.getFullName().getBytes(StandardCharsets.ISO_8859_1);
			byte[] gender = stObject.getGender().getBytes(StandardCharsets.ISO_8859_1);
			
			
			st.setFullName(new String(fullName,StandardCharsets.UTF_8));
			st.setGender(new String(gender,StandardCharsets.UTF_8));
			st.setDateOfBirth(stObject.getDateOfBirth());
			st.setPhoneNumber(stObject.getPhoneNumber());
			st.setEmail(stObject.getEmail());
			if(stObject.getStLevel()!=null){
				st.setStudentLevel(stObject.getStLevel());
			}				
		}catch(Exception e){
			st = null;
		}
		return st;
	}
	
	@Override
	public Address generateAddress(StudentFormAdd st) { // modifier code again
		City city = generateCity(st);
		District district = generateDistrict(st);
		Address address = new Address();
		if (city !=null) {
			address.setCity(city);
			address.setDistrict(district);
			return address;
		}
		return null;
	}
	
	@Transactional
	@Override
	public void saveorupdate(StudentFormAdd st) {		
	 try{
			Student student = generateStudent(st);
			User stAccount = generateSTAccount(st);
			UserRole stRole = generateUserRole(stAccount);
			School stSchool = genrateSchool(st);
			if (stSchool !=null) {
				student.setSchool(stSchool);
			}
			City city = generateCity(st);
			District district = generateDistrict(st);
			List<Address> addressList = myappdao.getList(Address.class);
			Address stAddress = new Address();
			for (Address addrr : addressList) {
				if(addrr.getCity() == city && addrr.getDistrict() == district){
					stAddress = addrr;
					break;
				}else{
					stAddress = generateAddress(st);
				}
			}
			if(stAddress !=null){
				myappdao.insertOrUpdate(stAddress);
				student.setStAddress(stAddress);
			}
			// save into db
			setUserForStudent(stAccount);
			setRoleForUser(stRole);
			student.setStAccount(stAccount);
			myappdao.insertOrUpdate(student);

			// Add Student To Class and add class for Student
			Class classObject = myappdao.getEntityById(Class.class, st.getClassOfST());
			student.getClassOfStudent().add(classObject);
			classObject.getStList().add(student);	
			myappdao.insertOrUpdate(classObject);

			
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	}

	@Override
	public boolean isPhoneFormat(String phone) {
	  String MOBILE_PATTERN = "[0-9]{10}";  
	  Pattern pattern = Pattern.compile(MOBILE_PATTERN); 
	  Matcher matcher = pattern.matcher(phone);  
	  if (!matcher.matches()) {  
		   return false;
		   }  
	  
		return true;
	}
	
	@Transactional
	@Override
	public long countAllStudent() {		
		return myappdao.countAllEntities(Student.class);
	}

	@Transactional
	@Override
	public Map<Integer, String> mapClass(String level,String idCourse) {
		List<Class>getList = myappdao.getDistinctList(Class.class);
		Map<Integer,String>returnMap = new HashMap<>();
		returnMap.put(0, "---------Select Class---------");
		if(level!=null && !"0".equals(idCourse)){
			if("Beginner".equals(level.trim())){
				
				for (Class item : getList) {
					if("Beginner".equals(item.getClassLevel().trim())&& item.getCourse().getIdCourse()== Integer.parseInt(idCourse)
							&& item.getStList().size() < item.getNumberOfSeats()){
						returnMap.put(item.getClassId(), item.getClassName());
					}					
				}
				
			}else if("Intermediate".equals(level.trim())){
				
				for (Class item : getList) {
					if("Intermediate".equals(item.getClassLevel().trim()) && item.getCourse().getIdCourse()== Integer.parseInt(idCourse)
							&& item.getStList().size() < item.getNumberOfSeats()){
						returnMap.put(item.getClassId(), item.getClassName());
					}
					
				}				
			
			}else if("Advance".equals(level.trim())){
				
				for (Class item : getList) {
					if("Advance".equals(item.getClassLevel().trim()) && item.getCourse().getIdCourse()== Integer.parseInt(idCourse)
							&& item.getStList().size() < item.getNumberOfSeats()){
						returnMap.put(item.getClassId(), item.getClassName());
					}
					
				}
				
			}else{
				for (Class item : getList) {
					if(item.getStList().size() < item.getNumberOfSeats()){
						returnMap.put(item.getClassId(), item.getClassName());
					}
					
				}
			}
			
			return returnMap;
		}else{
			for (Class item : getList) {
				if(item.getStList().size() < item.getNumberOfSeats()){
					returnMap.put(item.getClassId(), item.getClassName());
				}
				
			}
		}
		
		return returnMap;	 

	}
	
	@Override
	public MyAppUtil getInstanceUtilsApp() {
		return new MyAppUtil();
	}

	@Override
	public User generateSTAccount(StudentFormAdd stObject) {
		User stAccount = new User();
		try{
			stAccount.setEnabled(true);
			stAccount.setUsername(stObject.getUserName());
			passwordEncoder = new BCryptPasswordEncoder();
			stAccount.setPassword(passwordEncoder.encode(stObject.getPassword()));
			Set<UserRole> roles = new HashSet<>();
			roles.add(generateUserRole(stAccount));
			stAccount.setUserRole(roles);
			return stAccount;
		}catch(Exception e){
			return stAccount;
		}
		
	}

	@Override
	public UserRole generateUserRole(User user) {
		UserRole stRole = new UserRole();
		try{
			stRole.setRole("STUDENT");
			stRole.setUser(user);
			return stRole;
		}catch(Exception e){
			return stRole;
		}
	}

	@Transactional
	@Override
	public void setUserForStudent(User user) {
		myappdao.insertOrUpdate(user);
	}

	@Transactional
	@Override
	public void setRoleForUser(UserRole role) {
		myappdao.insertOrUpdate(role);	
	}

	@Override
	public boolean isValidEmail(Student st, String searchColum, String searchValue) {
		if(myappdao.IsValidObject(st, searchColum , searchValue)){
			 return true;	
		}
		return false;
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
	public Map<Integer, String> mapSchool() {
		List<School> getList = myappdao.getList(School.class);
		Map<Integer, String> returnmap = new HashMap<>();
		returnmap.put(0, "-------Select School-----");
		for (School school : getList) {
			returnmap.put(school.getSchoolId(), school.getSchoolName());
		}
		return returnmap;
	}

	@Override
	public School genrateSchool(StudentFormAdd st) {
		if(st.getSchool() !=0){
			School school = myappdao.getEntityById(School.class, st.getSchool());
			return school;
		}
		return null;
	}

	@Transactional
	@Override
	public City generateCity(StudentFormAdd st) {
			
		if(st.getCity() !=0){
			City city = myappdao.getEntityById(City.class, st.getCity());
			return city;
		}
		return null;
	}

	@Transactional
	@Override
	public void deleteStudent(Student student) {	
	
//		Class c = myappdao.getEntityById(Class.class, 1);
//		Set<Student> sts = c.getStList();
//		System.out.println(sts.size());

		Set<Class> clist = student.getClassOfStudent();		
		System.out.println(clist.size());
		
		for(Iterator<Class> object = clist.iterator(); object.hasNext();){
			Class cObject = object.next();
			cObject.getStList().remove(student);
			myappdao.insertOrUpdate(cObject);
		}
		
		myappdao.deleteEntity(student);
	}

	@Transactional
	@Override
	public Student getStudentById(Integer id) {
		return myappdao.getEntityById(Student.class, id);
	}

	@Transactional
	@Override
	public District generateDistrict(StudentFormAdd stObject) {	
		return myappdao.getEntityById(District.class, stObject.getDistrict());
	}

	@Override
	public Map<String, String> mapGender() {
		Map<String, String> mapGender = new HashMap<>();
		mapGender.put("Female", "Nữ");
		mapGender.put("Male", "Nam");
		return mapGender;
	}

	@Override
	public boolean isValidAccount(User account, String searchColum, String searchValue) {
		if(myappdao.IsValidObject(account, searchColum , searchValue)){
			 return true;	
		}
		return false;
	}


}
