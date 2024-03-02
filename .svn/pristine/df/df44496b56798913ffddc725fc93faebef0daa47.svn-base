package cn.java.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
	
	@GetMapping("/menus")
	@ResponseBody
	public Map<String,Object> getMenus() {
		// 伪数据
		Map<String, Object> retMap = new HashMap<String, Object>();
//		List<E>
		List<Map<String,Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> metaMap = new HashMap<String, Object>();
		
		dataMap.put("id", 125);
		dataMap.put("authName", "交互管理");
		dataMap.put("path", "users");
		dataMap.put("order", 1);
		
		
		Map<String, Object> childrenMap = new HashMap<String, Object>();
		childrenMap.put("id", 110);
		childrenMap.put("authName", "交互列表");
		childrenMap.put("path", "oaerps");
		childrenMap.put("order", null);
		childrenMap.put("children", new ArrayList<>());
		
//		Map<String, Object> childrenMap2 = new HashMap<String, Object>();
//		childrenMap2.put("id", 120);
//		childrenMap2.put("authName", "交互列表2");
//		childrenMap2.put("path", "login");
//		childrenMap2.put("order", null);
//		childrenMap2.put("children", new ArrayList<>());
		
		List<Object> childrenList = new ArrayList<Object>();
		childrenList.add(childrenMap);
//		childrenList.add(childrenMap2);
		
		dataMap.put("children", childrenList);
		dataList.add(dataMap);
		
		metaMap.put("msg", "获取菜单列表成功");
		metaMap.put("status", 200);
		
		retMap.put("data", dataList);
		retMap.put("meta", metaMap);
		return retMap;	
	}

}
