/**
 * 
 */
package com.nguyenvando.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nguyenvando.Utils.MyAppUtil;

/**
 * @author Nguyen Van Do
 *
 */
@Service
public class FinanceManagementServiceImpl implements FinanceManagementService {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyAppUtil getInstanceUtilsApp() {
		// TODO Auto-generated method stub
		return null;
	}

}
