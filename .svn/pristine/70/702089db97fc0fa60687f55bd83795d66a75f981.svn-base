package cn.java.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.java.controller.SmsCodeController;

@Component//需要配置包扫描
public class Task {
	
	@Autowired
	private SmsCodeController smsCodeController;
	
	//@Scheduled(cron = "* * * * * *")
	public void printDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		System.out.println("定时任务："+dateStr);
	}
	
//	@Scheduled(cron = "0/5 * 8 * * 7")//秒 分 时 日 月 周 先注释掉，避免OA-ERP数据交互干扰
	// 0/5代表从0秒开始每间隔5秒执行一次 *通配所有 8在时位，代表8时；7在周位代表日
	// "0/5 * 8 * * 7"代表每周日的8时0秒开始执行，每间隔5秒执行一次
	public void deleteSmsCode() {
		int deleteSmsCode = smsCodeController.deleteSmsCode();
		printDate();
		System.out.println("定时任务：操作条目："+deleteSmsCode);
		System.out.println(deleteSmsCode > 0 ? "清除过期短信验证码成功，工具：" + deleteSmsCode + "条" : "清除过期短信验证码失败或者无过期短信");
	}
}
