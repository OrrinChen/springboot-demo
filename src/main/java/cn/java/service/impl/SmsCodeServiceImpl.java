package cn.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.mapper.SmsCodeMapper;
import cn.java.service.SmsCodeService;

@Service
public class SmsCodeServiceImpl implements SmsCodeService {
	
	@Autowired
	private SmsCodeMapper smsCodeMapper;
	
	@Override
	public int clearSmsCode() {
		return smsCodeMapper.deleteSmsCode();
	}
}
