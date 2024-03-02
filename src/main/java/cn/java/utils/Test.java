package cn.java.utils;

public class Test {
	
	OaErpTool oaerp = new OaErpTool();
	
	public static void main(String[] args) {
		
		String firstDayofAppointMonth = OaErpTool.getFirstDayofAppointMonth("2022-08-15", "yyyy-MM-dd", -2);
		
		System.out.println("firstDayofAppointMonth=="+firstDayofAppointMonth);
		
	}

}
