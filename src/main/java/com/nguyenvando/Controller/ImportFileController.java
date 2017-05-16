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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nguyenvando.Entities.Class;
import com.nguyenvando.Services.ClassManagementService;
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

	@RequestMapping(value="/importClass",method=RequestMethod.POST)
	public List<Class> importClass (@RequestParam("file") MultipartFile[] data,final RedirectAttributes redirectAttributes,HttpServletRequest request
			)throws IOException,IllegalArgumentException{
		 String path = request.getSession().getServletContext().getRealPath("/") + "Upload/ImportFile/";
		List<Class> getList = new ArrayList<>();
		for (MultipartFile file : data) { // xu ly upload file len server
			if(!file.isEmpty()){
				MyAppUtil.uploadFile(file, path);
			}			
		}
		// Xu ly doc file va import vao DB
		for (MultipartFile file : data) { // xu ly upload file len server
			if(!file.isEmpty()){
				List<Class> list = MyAppUtil.readfileExcel(path+file.getOriginalFilename());
				//for (Class class1 : list) {
					//classService.saveorupdate(class1);
				//}
			}			
		}
		
		return getList;
				
	}
	
}
