/**
 * 
 */
package com.nguyenvando.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.Student;
import com.nguyenvando.Entities.Teacher;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Entities.UserRole;

/**
 * @author Nguyen Van Do
 *
 */
public interface importFileService {

	public void uploadFile(MultipartFile file,String path);
	public  List<Class> readfileExcel_Class(String filePath);
	public  void printToConsole(List cellDataList);
	public  List<Student> readfileExcel_Student(String filePath);
	public List<Teacher> readfileExcel_Teacher(String filePath);
	
	// create user Account
	public User getUser(String username);
	public UserRole getUserRole(String roleName);
	
}
