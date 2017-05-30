/**
 * 
 */
package com.nguyenvando.Controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.formula.functions.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nguyenvando.Entities.Class;
import com.nguyenvando.Entities.Course;
import com.nguyenvando.Entities.Student;
import com.nguyenvando.Entities.Teacher;
import com.nguyenvando.Services.ClassManagementService;
import com.nguyenvando.Services.importFileService;
import com.nguyenvando.Utils.MyAppUtil;

/**
 * @author Nguyen Van Do
 *
 */
@Controller
@EnableWebMvc
public class ImportFileController {
	
	@Autowired
	private ClassManagementService classService;
	
	@Autowired
	private  importFileService importFileService;

	@RequestMapping(value="/importClass",method=RequestMethod.POST)
	public String importClass (@RequestParam("file") MultipartFile[] data,final RedirectAttributes redirectAttributes,HttpServletRequest request
			)throws IOException,IllegalArgumentException{
		 String path = request.getSession().getServletContext().getRealPath("/") + "Upload/ImportFile/";

		for (MultipartFile file : data) { // xu ly upload file len server
			if(!file.isEmpty()){
				MyAppUtil.uploadFile(file, path);
			}			
		}
		// Xu ly doc file va import vao DB
		int count =0;
		for (MultipartFile file : data) { // xu ly upload file len server
			if(!file.isEmpty()){
				List<Class> list = importFileService.readfileExcel_Class(path+file.getOriginalFilename());							
				for (Class class1 : list) {
					try{
						if(!classService.IsValidClass(class1, "className", class1.getClassName().trim())){
							classService.saveorupdate(class1);
							count++;
						}
						
					}catch(Exception e){
						continue;
					}
				}
			}			
		}
		redirectAttributes.addFlashAttribute("message", "you have imported "+count +" Class into database.");
		return "redirect:/admin/listClass";
				
	}
	
	@RequestMapping(value="/importStudent",method=RequestMethod.POST)
	public String importStudent (@RequestParam("file") MultipartFile[] data,final RedirectAttributes redirectAttributes,HttpServletRequest request
			)throws IOException,IllegalArgumentException{
		 String path = request.getSession().getServletContext().getRealPath("/") + "Upload/ImportFile/";
		List<Class> getList = new ArrayList<>();
		for (MultipartFile file : data) { // xu ly upload file len server
			if(!file.isEmpty()){
				MyAppUtil.uploadFile(file, path);
			}			
		}
		// Xu ly doc file va import vao DB
		int count =0;
		for (MultipartFile file : data) { // xu ly upload file len server
			if(!file.isEmpty()){
				List<Student> list = importFileService.readfileExcel_Student(path+file.getOriginalFilename());
				System.out.println("StudentLIST" + list.size());				
//				for (Student class1 : list) {
//					try{
//						classService.saveorupdate(class1);
//						count++;
//					}catch(Exception e){
//						continue;
//					}
//				}
			}			
		}
		redirectAttributes.addFlashAttribute("success", "you have imported "+count +" Class into database.");
		return "redirect:/admin/listStudent";
				
	}

	@RequestMapping(value="/importTeacher",method=RequestMethod.POST)
	public String importTeacher (@RequestParam("file") MultipartFile[] data,final RedirectAttributes redirectAttributes,HttpServletRequest request
			)throws IOException,IllegalArgumentException{
		 String path = request.getSession().getServletContext().getRealPath("/") + "Upload/ImportFile/";
		List<Class> getList = new ArrayList<>();
		for (MultipartFile file : data) { // xu ly upload file len server
			if(!file.isEmpty()){
				MyAppUtil.uploadFile(file, path);
			}			
		}
		// Xu ly doc file va import vao DB
		int count =0;
		for (MultipartFile file : data) { // xu ly upload file len server
			if(!file.isEmpty()){
				List<Teacher> list = importFileService.readfileExcel_Teacher(path+file.getOriginalFilename());
				System.out.println("TeacherLIST" + list.size());				
//				for (Student class1 : list) {
//					try{
//						classService.saveorupdate(class1);
//						count++;
//					}catch(Exception e){
//						continue;
//					}
//				}
			}			
		}
		redirectAttributes.addFlashAttribute("success", "you have imported "+count +" Class into database.");
		return "redirect:/admin/listTeacher";
				
	}

}
