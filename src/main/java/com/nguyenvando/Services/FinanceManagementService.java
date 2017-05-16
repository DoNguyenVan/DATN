/**
 * 
 */
package com.nguyenvando.Services;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
public interface FinanceManagementService {
	public Map<Integer, String> mapMonths();
	public Map<Integer, String> mapYears();
	public MyAppUtil getInstanceUtilsApp();
	public String generate_Month_date1(FormFinance fin);
	public String generate_Month_date2(FormFinance fin);
	public String generate_Year_date1(FormFinance fin);
	public String generate_Year_date2(FormFinance fin);
	public boolean isLeapYear(int year);
	public List<Salary> getSalaryFrom_To(String property,String beginDate,String endDate);
	public List<SchoolFee> getSchoolFeeForm_To(String property,String beginDate,String endDate);
	
}
