package com.nguyenvando.Utils;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.web.multipart.MultipartFile;

import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.Course;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class MyAppUtil {
	
	public Date dateFormat(Date date,String format){
		
		return date;
	}
	
	public  String Date_To_String(Date date){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 String datestring = dateFormat.format(date);
		 return datestring;
	}
	
	public Date String_To_Date(String dateString){
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	      try {

	         Date date =  formatter.parse(dateString);
	         return date;

	      } catch (Exception e) {
	         return null;
	      }

	}
	
	public Date String_To_Date(String dateString,String dateFormat){
		DateFormat formatter = new SimpleDateFormat(dateFormat);
	      try {

	         Date date =  formatter.parse(dateString);
	         return date;

	      } catch (Exception e) {
	         return null;
	      }

	}
	
	public  String  TimeFormat(String timeStr){
		String[] result = timeStr.split(":");
		try{
			int hour = Integer.parseInt(result[0]);
			if(hour > 12)
				return result[0]+":"+result[1] +" PM";
			else{
				return result[0]+":"+result[1] +" AM";
			}
		}catch(ParseException e){
			return "Can't parse to Integer!";
		}catch (Exception e) {
			return "Bug! hehe ";
		}
	
	}	
	
	public  Date getSystemDateTime(){ // get date and time System
		Date systemDate = new Date();		
		return systemDate;
	}
	
	public  boolean is_date1_affer_date2(Date date1, Date date2){
		if(date1.after(date2)){
			return true;
		}
		return false;
	}
	
	

	public static void uploadFile(MultipartFile file,String path){
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
	

	@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	public static List<Class>readfileExcel(String filePath) throws IOException{
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
				Course course = new Course();
				
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
	
	 private static void printToConsole(List cellDataList) {
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
	
	
	
}
