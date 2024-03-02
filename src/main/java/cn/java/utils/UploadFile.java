package cn.java.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

  
/**
 * 文件上传工具类
 * @author Administrator
 *
 */
public class UploadFile {
	
	/**
	 * 文件上传静态方法
	 * @param bigHeadImg 请求的文件对象的名字，即页面上file类型的name值
	 * 页面from表单的enctype必须为multipart即(enctype="multipart/form-data"multipart
	 * 
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static void uploadFile(MultipartFile bigHeadImg) throws IllegalStateException, IOException {

		String name = bigHeadImg.getName();
		String originalFilename = bigHeadImg.getOriginalFilename();
		System.out.println("name=" + name);
		System.out.println("originalFilename=" + originalFilename);

		// UUID生成随机字符串（唯一性：网卡+时间戳动态生成）
		String randomUUID = UUID.randomUUID().toString();
		// 获取文件名最后一个"."的下标
		int index = originalFilename.lastIndexOf(".");
		// 获取文件后缀名(通过最后一个"."的下标分割)
		String exc = originalFilename.substring(index);
//		System.out.println("index="+index+",exc="+exc);

		// 根据当前的系统时间生成对应的文件夹：格式为yyyyMMdd\HH\mm\ss
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd\\HH\\mm\\ss\\");
		Date date = new Date();
		String dateStr = sdf.format(date);
		String filePath = "d:\\upload\\"+dateStr;
		
		// 判断文件夹是否存在,不存在则创建
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		// 文件路径及文件名(UUID+文件文件后缀)
		filePath = filePath + randomUUID + exc ;
		// 保存文件
		bigHeadImg.transferTo(new File(filePath));
	}

}
