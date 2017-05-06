/**
 * 
 */
package com.nguyenvando.Services;

import java.util.Map;

import com.nguyenvando.Utils.MyAppUtil;

/**
 * @author Nguyen Van Do
 *
 */
public interface FinanceManagementService {
	public Map<Integer, String> mapMonths();
	public Map<Integer, String> mapYears();
	public MyAppUtil getInstanceUtilsApp();
}
