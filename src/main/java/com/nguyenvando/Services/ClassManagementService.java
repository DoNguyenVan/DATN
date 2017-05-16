package com.nguyenvando.Services;
/**
 * @author Nguyen Van Do
 *
 */
import java.util.List;
import java.util.Map;

import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.Course;
import com.nguyenvando.Entities.Teacher;
import com.nguyenvando.Entities.Time;
import com.nguyenvando.Utils.CLASS_ST_Object;
import com.nguyenvando.Utils.ClassFormAdd;
import com.nguyenvando.Utils.MyAppUtil;

public interface ClassManagementService {

	public List<Class> getAllClass();
	public List<Class> getClassIsAvailableSeat(String level);
	public List<Class> getDistinctListClass();
	public List<Time> getTimeList(Class classObject);
	public List<CLASS_ST_Object> class_St_List();
	public Map<Integer, String> getCourses();
	public Class getClassById(int id);
	public Time getTimeById(int id);	
	public Time generateTimeEntity(Class classObject, String timeStr);
	public void saveorupdate(Class classObject);
	public void setTimeForClass(Time time);
	public void deleteTime (Time time);
	public void deleteClass(Class classObject);
	public long countAllClass();
	public int getMaxClassId(Class classObject,String colum);
	public boolean IsValidClass(Class classObject,String searchColum,String searchValue);
	public Map<String, String>mapClassLevel();
	public Map<Float, String>mapClassFee();
	public MyAppUtil getInstanceUtilsApp();
	public Class generateClassEntity(ClassFormAdd object);
	public Course getCourseById(int id);
	public Teacher getTeacherById(int id);
	public Map<Integer, String> getTeachers();
	public Map<Integer,String> getCourse();
	public Map<Integer,Class> getClassByCourse(Integer courseId);
}
