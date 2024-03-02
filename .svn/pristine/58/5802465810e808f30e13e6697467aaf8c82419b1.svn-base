package cn.java.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.java.entity.QQ;
import cn.java.utils.UploadFile;

@Controller
public class ForntController {
	@RequestMapping("/test1.do")
	public void test1() {
		System.out.println("test1...");
	}

	@RequestMapping("/test2.do")
	@ResponseBody
	public String test2() {
		return "test2...";
	}

	@RequestMapping("/toHome.do")
	public String toHome() {
		return "fornt/toHome.jsp";
	}

	@RequestMapping("/registerQQ.do")
//	@ResponseBody
	public String registerQQ(@Valid QQ qqInfo, BindingResult br, HttpServletRequest request) {
		System.out.println("qqInfo..." + qqInfo);
		//BidingResult存放校验信息
		boolean hasErrors = br.hasErrors();//判断校验是否有错误信息
		if(hasErrors) {//校验不通过
			Map<String, Object> errorMap = new HashMap<String, Object>();
			List<FieldError> errorList = br.getFieldErrors();
			for (FieldError error : errorList) {
				String field = error.getField();
				String defaultMessage = error.getDefaultMessage();
				errorMap.put(field, defaultMessage);
			}
			//往请求request塞值，用于提校验不通过的信息以及填充校验通过的字段值
			request.setAttribute("errorMap", errorMap);
			request.setAttribute("qqInfo", qqInfo);
			
			for(Map.Entry<String, Object> m : errorMap.entrySet()) {
				System.out.println("key:" + m.getKey() + " value:" + m.getValue());
			}
			System.out.println("数据校验不通过，调回请求页面");
			return "fornt/registerQQ.jsp";
		} else {//校验通过
			System.out.println("数据校验通过，可以掉用service业务层处理了！！");
			return "/fornt/success.jsp";
		}
	}

	@RequestMapping("/uploadFile.do")
	public void uploadFile(MultipartFile bigHeadImg) throws IllegalStateException, IOException{
		// 调用文件上传工具类的静态方法
		UploadFile.uploadFile(bigHeadImg);
	}
}
