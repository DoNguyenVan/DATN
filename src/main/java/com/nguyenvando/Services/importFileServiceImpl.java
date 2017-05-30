/**
 * 
 */
package com.nguyenvando.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nguyenvando.Dao.MyAppDao;
import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.Course;
import com.nguyenvando.Entities.Student;
import com.nguyenvando.Entities.Teacher;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Entities.UserRole;

/**
 * @author Nguyen Van Do
 *
 */
@Service
public class importFileServiceImpl implements importFileService{
	@Autowired
	private MyAppDao myappdao;

	@Override
	public void uploadFile(MultipartFile file, String path) {
		 try {
			    InputStream inputStream = file.getInputStream();    
			    if (inputStream == null)
			     System.out.println("File inputstream is null");  
			    // cach 2 - upload vao thu muc
			    FileUtils.forceMkdir(new File(path));
			    File upload = new File (path + file.getOriginalFilename());
			    file.transferTo(upload);
			    IOUtils.closeQuietly(inputStream);
			   } catch (IOException ex) {
			    System.out.println("Error saving uploaded file");
			   }
	}

	@Override
	public List<Class> readfileExcel_Class(String filePath) {
		  List<Class> getList = new ArrayList<>();
		     List cellDataList = new ArrayList();
		     try {
		         /**
		          * Create a new instance for FileInputStream class
		          */
		         FileInputStream fileInputStream = new FileInputStream(filePath);

		         /**
		          * Create a new instance for POIFSFileSystem class
		          */
		         POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

		         /*
		          * Create a new instance for HSSFWorkBook Class
		          */
		         HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
		          
		         for (int i = 0; i < workBook.getNumberOfSheets(); i++) {  
		              
		             HSSFSheet hssfSheet = workBook.getSheetAt(i);

		             /**
		              * Iterate the rows and cells of the spreadsheet to get all the
		              * datas.
		              */
		             Iterator rowIterator = hssfSheet.rowIterator();

		             while (rowIterator.hasNext()) {
		                 HSSFRow hssfRow = (HSSFRow) rowIterator.next();
		                 if(hssfRow.getRowNum()!=0){// cho nay code lai
			                  Iterator iterator = hssfRow.cellIterator();
			                  List cellTempList = new ArrayList();
			                  while (iterator.hasNext()) {
			                      HSSFCell hssfCell = (HSSFCell) iterator.next();
			                      cellTempList.add(hssfCell);
			                  }
			                  cellDataList.add(cellTempList);
		                 }
		                 
		             }
		         } 
		          
		          
		     } catch (Exception e) {
		         e.printStackTrace();
		     }
		     /**
		      * Call the printToConsole method to print the cell data in the console.
		      */
		   //  printToConsole(cellDataList);
		     
		     // generate class list
			 for (int i = 0; i < cellDataList.size(); i++) {
		         List cellTempList = (List) cellDataList.get(i);
		         Class cObject = new Class();
		         for (int j = 0; j < cellTempList.size(); j++) {
		             HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
		             String stringCellValue = hssfCell.toString();
		             System.out.print(stringCellValue + "\t");
		             switch (j) {
					case 0:
						cObject.setClassName(stringCellValue);
						break;
					case 1: 
						cObject.setStartDate(stringCellValue);
						break;
					case 2: 
						cObject.setNumberOfSeats((int) Math.round(Double.parseDouble(stringCellValue)));
						break;
					case 3: 
						cObject.setClassLevel(stringCellValue);
						break;
					case 4: 
						cObject.setFee(Float.parseFloat(stringCellValue));
						cObject.setFeeRemain(Float.parseFloat(stringCellValue));
						break;
					case 5: 
						Course course =  myappdao.getEntityById(Course.class,(int) Math.round(Double.parseDouble(stringCellValue)));
						cObject.setCourse(course);
						break;
					default:		
						break;
					}
		         }
		         System.out.println();
		         getList.add(cObject);
		     }

		     
			  
			  return getList;
	}

	@Override
	public void printToConsole(List cellDataList) {
		 for (int i = 0; i < cellDataList.size(); i++) {
	            List cellTempList = (List) cellDataList.get(i);
	            for (int j = 0; j < cellTempList.size(); j++) {
	                HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
//	                String s1 =" vccvsnjk";
//	                byte[] decBytes1 = ByteConverter(hssfCell.toString().getBytes());
//	                byte[]in =  .getBytes();
//	                String out = new String(in,StandardCharsets.UTF_8);
	                String stringCellValue = hssfCell.toString();
	                System.out.print(stringCellValue + "\t");
	            }
	            System.out.println();
	        }
	}

	@Override
	public List<Student> readfileExcel_Student(String filePath) {
		  List<Student> getList = new ArrayList<>();
		     List cellDataList = new ArrayList();
		     try {
		         /**
		          * Create a new instance for FileInputStream class
		          */
		         FileInputStream fileInputStream = new FileInputStream(filePath);

		         /**
		          * Create a new instance for POIFSFileSystem class
		          */
		         POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

		         /*
		          * Create a new instance for HSSFWorkBook Class
		          */
		         HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
		          
		         for (int i = 0; i < workBook.getNumberOfSheets(); i++) {  
		              
		             HSSFSheet hssfSheet = workBook.getSheetAt(i);

		             /**
		              * Iterate the rows and cells of the spreadsheet to get all the
		              * datas.
		              */
		             Iterator rowIterator = hssfSheet.rowIterator();

		             while (rowIterator.hasNext()) {
		                 HSSFRow hssfRow = (HSSFRow) rowIterator.next();
		                 if(hssfRow.getRowNum()!=0){// cho nay code lai
			                  Iterator iterator = hssfRow.cellIterator();
			                  List cellTempList = new ArrayList();
			                  while (iterator.hasNext()) {
			                      HSSFCell hssfCell = (HSSFCell) iterator.next();
			                      cellTempList.add(hssfCell);
			                  }
			                  cellDataList.add(cellTempList);
		                 }
		                 
		             }
		         } 
		          
		          
		     } catch (Exception e) {
		         e.printStackTrace();
		     }
		     /**
		      * Call the printToConsole method to print the cell data in the console.
		      */
		   //  printToConsole(cellDataList);
		     
		     // generate class list
			 for (int i = 0; i < cellDataList.size(); i++) {
		         List cellTempList = (List) cellDataList.get(i);
		         Student stObject = new Student();
		         for (int j = 0; j < cellTempList.size(); j++) {
		             HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
		             String stringCellValue = hssfCell.toString();
		             System.out.print(stringCellValue + "\t");
		             switch (j) {
					case 0:
						stObject.setFullName(stringCellValue);
						break;
					case 1: 
						stObject.setDateOfBirth(stringCellValue);
						break;
					case 2: 
						stObject.setGender(stringCellValue);
						break;
					case 3: 
						stObject.setPhoneNumber(stringCellValue);
						break;
					case 4: 
						stObject.setEmail(stringCellValue);
						break;
					case 5: 
						try{
							User ac_student = getUser(stringCellValue);
							myappdao.insertOrUpdate(ac_student);
							UserRole role = getUserRole("STUDENT");
							role.setUser(ac_student);
							myappdao.insertOrUpdate(role);
							stObject.setStAccount(ac_student);
							myappdao.insertOrUpdate(stObject);
						}catch (Exception e) {
							break;
						}
						
						break;
					default:		
						break;
					}
		         }
		         System.out.println();
		         getList.add(stObject);		         

		     }

		     
			  
			  return getList;
	}

	@Override
	public List<Teacher> readfileExcel_Teacher(String filePath) {
		  List<Teacher> getList = new ArrayList<>();
		     List cellDataList = new ArrayList();
		     try {
		         /**
		          * Create a new instance for FileInputStream class
		          */
		         FileInputStream fileInputStream = new FileInputStream(filePath);

		         /**
		          * Create a new instance for POIFSFileSystem class
		          */
		         POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

		         /*
		          * Create a new instance for HSSFWorkBook Class
		          */
		         HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
		          
		         for (int i = 0; i < workBook.getNumberOfSheets(); i++) {  
		              
		             HSSFSheet hssfSheet = workBook.getSheetAt(i);

		             /**
		              * Iterate the rows and cells of the spreadsheet to get all the
		              * datas.
		              */
		             Iterator rowIterator = hssfSheet.rowIterator();

		             while (rowIterator.hasNext()) {
		                 HSSFRow hssfRow = (HSSFRow) rowIterator.next();
		                 if(hssfRow.getRowNum()!=0){// cho nay code lai
			                  Iterator iterator = hssfRow.cellIterator();
			                  List cellTempList = new ArrayList();
			                  while (iterator.hasNext()) {
			                      HSSFCell hssfCell = (HSSFCell) iterator.next();
			                      cellTempList.add(hssfCell);
			                  }
			                  cellDataList.add(cellTempList);
		                 }
		                 
		             }
		         } 
		          
		          
		     } catch (Exception e) {
		         e.printStackTrace();
		     }
		     /**
		      * Call the printToConsole method to print the cell data in the console.
		      */
		   //  printToConsole(cellDataList);
		     
		     // generate class list
			 for (int i = 0; i < cellDataList.size(); i++) {
		         List cellTempList = (List) cellDataList.get(i);
		         Teacher tcObject = new Teacher();
		         for (int j = 0; j < cellTempList.size(); j++) {
		             HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
		             String stringCellValue = hssfCell.toString();
		             System.out.print(stringCellValue + "\t");
		             switch (j) {
					case 0:
						tcObject.setFullName(stringCellValue);
						break;
					case 1: 
						tcObject.setDateOfBirth(stringCellValue);
						break;
					case 2: 
						tcObject.setGender(stringCellValue);
						break;
					case 3: 
						tcObject.setPhoneNumber(stringCellValue);
						break;
					case 4: 
						tcObject.setEmail(stringCellValue);
						break;
					case 5: 
						try{
							User ac_teacher = getUser(stringCellValue);
							myappdao.insertOrUpdate(ac_teacher);
							UserRole role = getUserRole("TEACHER");
							role.setUser(ac_teacher);
							myappdao.insertOrUpdate(role);
							tcObject.setTcAccount(ac_teacher);
							myappdao.insertOrUpdate(tcObject);
						}catch (Exception e) {
							break;
						}
						
						break;
					default:		
						break;
					}
		         }
		         System.out.println();
		         getList.add(tcObject);
		     }

		     
			  
			  return getList;
	}

	@Override
	public User getUser(String username) {
		PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
		User user = new User();
		user.setUsername(username);
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode("102120"));
		return user;
	}

	@Override
	public UserRole getUserRole(String roleName) {
		UserRole role = new UserRole();
		role.setRole(roleName);
		return role;
	}

}
