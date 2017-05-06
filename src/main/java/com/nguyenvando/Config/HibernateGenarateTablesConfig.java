/**
 * 
 */
package com.nguyenvando.Config;

import java.util.HashSet;
import java.util.Set;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nguyenvando.Entities.City;
import com.nguyenvando.Entities.Course;
import com.nguyenvando.Entities.District;
import com.nguyenvando.Entities.School;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Entities.UserRole;

/**
 *
 * @author Nguyen Van Do
 * @DateBegin Mar 19, 2017 1:49:17 PM
 *
 **/
public class HibernateGenarateTablesConfig {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	private static PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
	
		try {
			
				Configuration configuration=new Configuration();
				configuration.addResource("hibernate.cfg.xml");
				configuration.configure();
				serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		  
		   // -------Add Some Data into DB -----------------
				Set<Course> courses = getListCourse();
				
				Session ss = sessionFactory.openSession();
				ss.beginTransaction();
				
				for (Course course : courses) { //Create Course Table
					ss.save(course);
				}
				
				// create Admin Account
				User admin = getAdmin();
				ss.save(admin);
				UserRole role = getUserRole();
				role.setUser(admin);			
				ss.save(role);
				
				//--------------------
				Set<School> schools = getSchool();
				for (School school : schools) {
					school.setSchoolName(school.getSchoolName());
					ss.save(school);
				}
			
				//------------------------------
				Set<City> citys = getCity();
				citys = setDistrictForCity(citys);
				for (City city : citys) {
					ss.save(city);
					if(city.getDistrict().size()>0){
						for (District district : city.getDistrict()) {						
							ss.save(district);
						}						
					}
					
				}
				
				
				ss.getTransaction().commit();
				ss.close();
		        
		
		
		}
			catch (Exception e) {
		        System.err.println("Error creating Session: " + e);
		        throw new ExceptionInInitializerError(e);
			}
	}
	
	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }	

	//---------Create Data-------------------------------- 
	public static Set<Course> getListCourse(){// Create Course Data
		Set<Course> courses = new HashSet<>();

		Course course1 = new Course();
		course1.setCourseName("BASIC");
		course1.setTimeline("6 months");
		
		Course course2 = new Course();
		course2.setCourseName("TOIEC 600+");
		course2.setTimeline("6 months");
		
		Course course3 = new Course();
		course3.setCourseName("TOIEC 700+");
		course3.setTimeline("6 months");
		
		Course course4 = new Course();
		course4.setCourseName("IELTS");
		course4.setTimeline("6 months");
		
		Course course5 = new Course();
		course5.setCourseName("TALK PRACTICE");
		course5.setTimeline("6 months");
		
		courses.add(course1); courses.add(course2); courses.add(course3); courses.add(course4); courses.add(course5);
		return courses;
	}

	public static User getAdmin(){
		passwordEncoder = new BCryptPasswordEncoder();
		User admin = new User();
		admin.setUsername("vando");
		admin.setEnabled(true);
		admin.setPassword(passwordEncoder.encode("10212"));
		return admin;
	}
	public static UserRole getUserRole(){
		UserRole role = new UserRole();
		role.setRole("ADMIN");
		return role;
	}
	public static Set<School> getSchool(){ // Get School List
		Set<School> schools = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			School school = new School();
			switch (i) {
			case 0:
				school.setSchoolName("DH BACH KHOA");
				schools.add(school);
				break;
			case 1:	
				school.setSchoolName("DH KINH TE");
				schools.add(school);
				break;
			case 2:		
				school.setSchoolName("DH SU PHAM");
				schools.add(school);
				break;
			case 3:	
				school.setSchoolName("DH NGOAI NGU");
				schools.add(school);
				break;	
			case 4:	
				school.setSchoolName("DH DUY TAN");
				schools.add(school);
				break;	
			case 5:	
				school.setSchoolName("Khoa Y Dược ");
				schools.add(school);
				break;	
			case 6:	
				school.setSchoolName("CD CONG NGHE");
				schools.add(school);
				break;	
			case 7:	
				school.setSchoolName("CD BACH KHOA");
				schools.add(school);
				break;	
			default:
				break;
			}
		} 
		return schools;
	}
	
	public static Set<City> getCity(){ // Get City List
		Set<City> citys = new HashSet<>();
		for (int i = 0; i < 5; i++) {
			City city = new City();
			switch (i) {
			case 0:
				city.setCityName("Da Nang");				
				break;
			case 1:
				city.setCityName("Hue");				
				break;
			case 2:
				city.setCityName("Hoi An");				
				break;
			case 3:
				city.setCityName("Tam Ky");				
				break;
			case 4:
				city.setCityName("Dong Ha");				
				break;
			default:
				break;
			}
			citys.add(city);
		}
		return citys;
	}
	public static Set<City> setDistrictForCity(Set<City>citys){
		
		for (City city : citys) {
			Set<District> districts = new HashSet<>();
			
				if("Da Nang".equals(city.getCityName().trim())){		
					for (int i = 0; i < 5; i++) {
						District district = new District();	
						district.setCity(city);
						switch (i) {
						case 0:
							district.setDistrictName("Lien Chieu");						
							break;
						case 1:
							district.setDistrictName("Hai Chau");						
							break;
						case 2:
							district.setDistrictName("Thanh Khe");						
							break;						
						case 3:
							district.setDistrictName("Hoa Vang");						
							break;
						case 4:
							district.setDistrictName("Son Tra");						
							break;
						default:					
							break;
						}
						
						districts.add(district);
					}
								
			}// end loop
			
			city.setDistrict(districts);
		}
		
		return citys;
	}
	
	
}
