package com.pgh.crowdfunding.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.pgh.crowdfunding.ajax.AjaxResult;
@Controller
@RequestMapping("portal")
public class PortalController {

	
	@RequestMapping("/indexMpage")
	public String indexManagerPage() {
		
		return "business/index_business";
	}
	
	@RequestMapping("fileUpfiles")
	@ResponseBody
	public Object fileUpFiles(@RequestParam(required=false)CommonsMultipartFile file,ServletRequest req) {
		AjaxResult result=new AjaxResult();
		System.out.println("realPath===>"+req.getServletContext().getRealPath(""));
//		String savaPath="D:\\eclipse-workspace\\crowdfunding-web\\src\\main\\webapp\\files/"+file.getOriginalFilename();
//		System.out.println("savaPath====>"+savaPath);
//		String originalFilename=file.getOriginalFilename();
//		System.out.println("originalFilename====>"+originalFilename);
//		try {
//			file.transferTo(new File(savaPath));
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("上传成功！");
		return null;
	}
	
	
}
