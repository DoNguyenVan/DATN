/**
 * 
 */
package com.nguyenvando.Utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Nguyen Van Do
 *
 */
@Controller
@RequestMapping(value="/test")
public class myTEST {

	@RequestMapping(value="/test1")
	public ModelAndView gettest(Map<String, Object> map){
		ModelAndView view =  new ModelAndView();
		view.setViewName("testUTF8");
		return view;
	}
	@RequestMapping(value="/dotest",method=RequestMethod.POST)
	public ModelAndView dotest(Map<String, Object> map,@RequestParam(name="test") String testtxt) throws UnsupportedEncodingException{
		ModelAndView view =  new ModelAndView();
	
		String s = getSTR();

		System.out.println(s);
		byte[] bytes = testtxt.getBytes("ISO-8859-1");
		testtxt = new String(bytes,"UTF-8");

		System.out.println(testtxt);
		
		view.setViewName("testUTF8");
		return view;
	}
	
	public String getSTR(){
		return new String("xin chào anh em văn ươm");
	}
	
	public static String getsts(){
		return new String("xin chào anh em văn ươm");
	}
	
	public static void main(String []args) {
		String s = getsts();
		System.out.println(s);
	}
	
}
