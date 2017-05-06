package com.nguyenvando.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvando.Dao.MyAppDao;
import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.Course;
import com.nguyenvando.Entities.Teacher;
import com.nguyenvando.Entities.Time;
import com.nguyenvando.Utils.CLASS_ST_Object;
import com.nguyenvando.Utils.ClassFormAdd;
import com.nguyenvando.Utils.MyAppUtil;

@Service
public class ClassManagementServiceImpl implements ClassManagementService{
	
	@Autowired
	private MyAppDao myappdao;
	
	@Transactional
	@Override
	public List<Class> getAllClass() {	
		return myappdao.getList(Class.class);
	}

	@Transactional
	@Override
	public List<Class> getClassIsAvailableSeat(String level) {
	
		List<Class>getList = myappdao.getDistinctList(Class.class);
		List<Class>returnList = new ArrayList<>();
		
		if(level!=null){
			if("Beginner".equals(level.trim())){
				
				for (Class item : getList) {
					if("Beginner".equals(item.getClassLevel().trim()) && item.getStList().size() < item.getNumberOfSeats()){
						returnList.add(item);
					}					
				}
				
			}else if("Intermediate".equals(level.trim())){
				
				for (Class item : getList) {
					if("Intermediate".equals(item.getClassLevel().trim()) && item.getStList().size() < item.getNumberOfSeats()){
						returnList.add(item);
					}
					
				}				
			
			}else if("Advance".equals(level.trim())){
				
				for (Class item : getList) {
					if("Advance".equals(item.getClassLevel().trim()) && item.getStList().size() < item.getNumberOfSeats()){
						returnList.add(item);
					}
					
				}
				
			}else{
				for (Class item : getList) {
					if(item.getStList().size() < item.getNumberOfSeats()){
						returnList.add(item);
					}
					
				}
			}
			
			return returnList;
		}else{
			for (Class item : getList) {
				if(item.getStList().size() < item.getNumberOfSeats()){
					returnList.add(item);
				}
				
			}
		}
		
		return returnList;	 
	}
	
	@Transactional
	@Override
	public List<Class> getDistinctListClass() {
		return myappdao.getDistinctList(Class.class);
	}
	
	@Override
	public List<Time> getTimeList(Class classObject) {
		return classObject.getTimeList();
	}
	
	@Transactional
	@Override
	public Map<Integer, String> getCourses() {		
	
		List<Course> getList = myappdao.getList(Course.class);
		Map<Integer,String> courses = new HashMap<>();
		for (Course course : getList) {
			courses.put(course.getIdCourse(), course.getCourseName());
		}
		
		return courses ;
	}

	@Transactional
	@Override
	public Class getClassById(int id) {
		return myappdao.getEntityById(Class.class, id);
	}
	
	@Transactional
	@Override
	public Time getTimeById(int id) {
		return myappdao.getEntityById(Time.class, id);
	}
	
	@Override
	public Time generateTimeEntity(Class classObject, String timeStr) {

		String result[] = timeStr.split("[|-]");
		Time timeObject = new Time();
		timeObject.setDateOfWeek(result[0]);
		timeObject.setStartTime(result[1]);
		timeObject.setEndTime(result[2]);
		timeObject.setClassTime(classObject);
		return timeObject;
	}
	
	@Transactional
	@Override
	public void saveorupdate(Class classObject) {
		myappdao.insertOrUpdate(classObject);
	}

	@Transactional
	@Override
	public void setTimeForClass(Time time) {
		myappdao.insertOrUpdate(time);		
	}
	
	@Transactional
	@Override
	public void deleteTime(Time time) {
		myappdao.deleteEntity(time);
	}
	
	@Transactional
	@Override
	public void deleteClass(Class classObject) {
		myappdao.deleteEntity(classObject);		
	}
	
	@Transactional
	@Override
	public long countAllClass() {
		return myappdao.countAllEntities(Class.class);
	}
	
	@Transactional
	@Override
	public int getMaxClassId(Class classObject, String colum) {
		return myappdao.getMaxId(classObject, colum);
	}
	
	@Transactional
	@Override
	public boolean IsValidClass(Class classObject, String searchColum, String searchValue) {
		if(myappdao.IsValidObject(classObject, searchColum , classObject.getClassName().trim())){
		 return true;	
		}
		return false;
	}

	@Override
	public Map<String, String> mapClassLevel() {
		Map<String, String>classLevel = new HashMap<>();
		classLevel.put("Beginner", "Beginner");
		classLevel.put("Intermediate", "Intermedia");
		classLevel.put("Advance", "Advance");
		return classLevel;
	}

	@Override
	public Map<Float, String> mapClassFee() {
		Map<Float, String>classFee = new HashMap<>();
		classFee.put((float) 3000000,"3.000.000 VND");
		classFee.put((float) 4000000,"4.000.000 VND");
		classFee.put((float) 5000000,"5.000.000 VND");
		return classFee;
	}

	@Override
	public MyAppUtil getInstanceUtilsApp() {
		return new MyAppUtil();
	}

	@Transactional
	@Override
	public Class generateClassEntity(ClassFormAdd object) {
		
	try{	
			Course course = getCourseById(object.getIdCourse());
			Teacher teacher = getTeacherById(object.getTeacherId());
			Class classObject = new Class();
			classObject.setClassName(object.getClassName());
			classObject.setStartDate(object.getStartDate());
			classObject.setNumberOfSeats(object.getNumberOfSeats());
			classObject.setClassLevel(object.getClassLevel());
			classObject.setFee(object.getFee());
			classObject.setCourse(course);
			if(teacher!=null){
				classObject.setTeacher(teacher);
			}	
			return classObject;
		}catch(Exception e){
			return null;
		}
		
	}

	@Transactional
	@Override
	public Course getCourseById(int id) {
		return myappdao.getEntityById(Course.class, id);
	}

	@Transactional
	@Override
	public Teacher getTeacherById(int id) {
		return myappdao.getEntityById(Teacher.class, id);
	}

	@Override
	public Map<Integer, String> getTeachers() {
		
		List<Teacher> getList = myappdao.getList(Teacher.class);
		Map<Integer, String> teachers = new HashMap<>();
		teachers.put(0, "------------ Select Teacher------------");
		for (Teacher teacher : getList) {
			teachers.put(teacher.getTeacherId(), teacher.getFullName());
		}
		
		return teachers;
	}

	@Transactional
	@Override
	public List<CLASS_ST_Object> class_St_List() {
		List<Class> getList = myappdao.getList(Class.class);
		List<CLASS_ST_Object> returnList = new LinkedList<CLASS_ST_Object>();
		for (Class class1 : getList) {
			CLASS_ST_Object Object = new CLASS_ST_Object();
			Object.setClassId(class1.getClassId());
			Object.setClassName(class1.getClassName());
			Object.setNumberSTofCLASS(class1.getStList().size());
			returnList.add(Object);
		}
		return returnList;
	}

}
