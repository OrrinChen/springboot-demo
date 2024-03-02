package cn.java.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

public class QQ {
	@NotNull(message = "昵称格式错误！")
	@Pattern(regexp = ".{4,10}",message = "昵称格式错误！")
	private String nick;//昵称
	
	@NotNull(message = "密码格式错误！")
	@Pattern(regexp = "\\w{6,16}",message = "密码格式错误！")
	private String pwd;//密码
	
	@NotNull(message = "手机号格式错误！")
	@Pattern(regexp = "1[356789]\\d{9}",message = "手机号格式错误！")
	private String phone;//手机号
	
	// 正则表达式操作的对象是字符串
	@Email(message = "邮箱格式错误！")//校验不起作用
	@NotNull(message = "邮箱格式错误！")//校验不起作用
	private String email;//邮箱
	
	@NotNull(message="年龄格式错误！")
	@Max(value = 150,message = "亲，你没有这么老吧！！")
	@Min(value = 1, message = "亲，你没有这么小吧！！")
	private Integer age;//年龄
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "QQ [nick=" + nick + ", pwd=" + pwd + ", phone=" + phone + ", email=" + email + ", age=" + age + "]";
	}

}
