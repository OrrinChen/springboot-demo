package cn.java.utils;

import java.security.MessageDigest;

public class MD5Tool {

	// 16进制字符串数组
	private static final String[] digital = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
			"f" };

	/**
	 * 初始MD5加密，返回32位16进制字符串
	 * @param ctx 明文
	 * @return 32位16进制字符串
	 * @throws Exception
	 */
	private static String initMD5(String ctx) throws Exception {
		String miWen = "";
		// 获取封装MD5算法的核心类
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		// String字符串转换成byte[]数组（UTF-8编码）,对应的数组值为ASCII码值，如a=97 1=49
		// 2的7次或者8次方为一个ASCII码（0-127或者0-255），汉字需要3个或者4个ASCII码来定义，看具体编码规则要看具体定义
		byte[] ctxBytes = ctx.getBytes("UTF-8");
//		for (byte b : ctxBytes) {
//			System.out.print(b+"\t");
//		}
		// 明文经过md5加密后，返回16位数组(数组的值有正有负，绝对值不超过256)
		byte[] bytes = md5.digest(ctxBytes);
		for (byte b : bytes) {
			int temp = b;
			// 当数组的值为负数时加256变成正整数
			if (temp < 0) {
				temp += 256;
			}
			// 经过判断后index1|index2一定是正整数（0-15之间）
			int index1 = temp / 16; // 取商
			int index2 = temp % 16; // 取余
			// 每一位数组的值分别以16取值商和取余作为下标取值追加到密文字符串 16位数组--->32位字符串
			miWen += digital[index1] + digital[index2];
		}
		return miWen;
	}
	
	/**
	 * 最终MD5加密方法，返回32位16进制字符串，不要对外公布，验证时同样调用此方法逻辑
	 * @param ctx 明文
	 * @return 32位16进制字符串
	 * @throws Exception
	 */
	public static String finalMD5(String ctx) throws Exception {
		// MD5加密逻辑，不要对外公布，以防暴力破击（登录验证时调用同样方法加密逻辑）
		return initMD5(initMD5(initMD5(ctx)+"dingpeng"+ctx)+"dingpeng"+ctx);
//		return initMD5("陈");
	}
	
	public static void main(String[] args) throws Exception {
		String miWen = MD5Tool.finalMD5("123456");
		System.out.println("miWen="+miWen);
	}

}
