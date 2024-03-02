package cn.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.java.service.SmsCodeService;

@Controller
public class SmsCodeController {

	@Autowired
	private SmsCodeService codeService;

	public int deleteSmsCode() {
		return codeService.clearSmsCode();
	}

}
