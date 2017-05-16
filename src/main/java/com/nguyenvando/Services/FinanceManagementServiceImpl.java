/**
 * 
 */
package com.nguyenvando.Services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvando.Dao.MyAppDao;
import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.Course;
import com.nguyenvando.Entities.Salary;
import com.nguyenvando.Entities.SchoolFee;
import com.nguyenvando.Entities.Skill;
import com.nguyenvando.Utils.FormFinance;
import com.nguyenvando.Utils.MyAppUtil;

/**
 * @author Nguyen Van Do
 *
 */
@Service
public class FinanceManagementServiceImpl implements FinanceManagementService {
	@Autowired
	private MyAppDao myappdao;
	
	private MyAppUtil myUtil;
	
	@Override
	public Map<Integer, String> mapMonths() {
		Map<Integer, String> map =  new HashMap<>();
		for(int i=0;i<13;i++){
			switch (i) {
			case 0: 
				map.put(i, "--Month--");				
				break;
			case 1:
				map.put(i, "January");				
				break;
			case 2:
				map.put(i, "February");				
				break;
			case 3: 
				map.put(i, "March");				
				break;
			case 4:
				map.put(i, "April");				
				break;
			case 5:
				map.put(i, "May");				
				break;
			case 6:
				map.put(i, "June");				
				break;
			case 7:
				map.put(i, "July");				
				break;
			case 8:
				map.put(i, "August");				
				break;
			case 9: 
				map.put(i, "September");				
				break;
			case 10:
				map.put(i, "October");				
				break;
			case 11: 
				map.put(i, "November");				
				break;
			case 12:
				map.put(i, "December");				
				break;
			default:
				break;
			}
		}
		return map;
	}

	@Override
	public Map<Integer, String> mapYears() {
		Map<Integer, String> map =  new HashMap<>();
		map.put(0, "----Year---");
		for(int i=2013;i<2030;i++){
			map.put(i, "Năm "+i);
		}
		return map;

	}

	@Override
	public MyAppUtil getInstanceUtilsApp() {
		return new MyAppUtil();
	}

	@Transactional
	@Override
	public List<Salary> getSalaryFrom_To(String property, String beginDate, String endDate) {
		myUtil = new MyAppUtil();
		Date begin = myUtil.String_To_Date(beginDate, "yyyy/MM/dd");
		Date end = myUtil.String_To_Date(endDate, "yyyy/MM/dd");				
		return myappdao.getListBetween(Salary.class, property, begin, end);
	}

	@Override
	public String generate_Month_date1(FormFinance fin) {
		
		if(fin.getMonth1() == fin.getMonth2()){
			int year = fin.getYear();
			int month = fin.getMonth1();
			String date1 = year +"/"+month+"/"+"01";
			return date1;
		}else{
			int year = fin.getYear();
			int month = fin.getMonth1();
			String date1 = year +"/"+month+"/"+"01";
			return date1;
		}
		
	}

	@Override
	public String generate_Month_date2(FormFinance fin) {
	
		int[] years1 = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		int[] years2 = {0,31,29,31,30,31,30,31,31,30,31,30,31};
		
		if((fin.getMonth1() == fin.getMonth2()) && isLeapYear(fin.getYear())){
			int year = fin.getYear();
			int month = fin.getMonth2();
			String date2 = year +"/"+month+"/"+years2[fin.getMonth2()];
			return date2;
		}if((fin.getMonth1() == fin.getMonth2()) && !isLeapYear(fin.getYear())){
			int year = fin.getYear();
			int month = fin.getMonth2();
			String date2 = year +"/"+month+"/"+years1[fin.getMonth2()];
			return date2;			
		}
		else{
			int year = fin.getYear();
			int month = fin.getMonth2();
			String date2 = year +"/"+month+"/"+"01";
			return date2;
		}

	}

	@Override
	public String generate_Year_date1(FormFinance fin) {
		Date d = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		int year = calendar.get(Calendar.YEAR);
		int month = fin.getMonth3();
		String date1 = year +"/"+ month +"/"+"01";		
		return date1;
	}

	@Override
	public String generate_Year_date2(FormFinance fin) {
		Date d = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		int year = calendar.get(Calendar.YEAR);
		int[] years1 = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		int[] years2 = {0,31,29,31,30,31,30,31,31,30,31,30,31};
		int month = fin.getMonth4();
		if((fin.getMonth3() == fin.getMonth4()) && isLeapYear(year)){
			String date2 = year+"/"+month+"/"+years2[month];
			return date2;
		}
		if((fin.getMonth3() == fin.getMonth4()) && !isLeapYear(year)){
			String date2 = year+"/"+month+"/"+years1[month];
			return date2;			
		}else{
			String date2 = year+"/"+month+"/"+"01";
			return date2;
		}

	}

	@Override
	public boolean isLeapYear(int year) {
		if((year%4==0 && year%100!=0) || year%400==0) return true; 
		return false;
	}

	@Transactional
	@Override
	public List<SchoolFee> getSchoolFeeForm_To(String property, String beginDate, String endDate) {
		myUtil = new MyAppUtil();
		Date begin = myUtil.String_To_Date(beginDate, "yyyy/MM/dd");
		Date end = myUtil.String_To_Date(endDate, "yyyy/MM/dd");				
		return myappdao.getListBetween(SchoolFee.class, property, begin, end);
	}



}
