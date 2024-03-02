package cn.java.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.entity.Food;
import cn.java.service.FoodService;

@Controller
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@RequestMapping("/insertFood.do")
	@ResponseBody
	public boolean insertFood(Food food) {
		return foodService.saveFood(food);
	}
	
	@RequestMapping("/deleteFood.do")
	@ResponseBody
	public boolean deleteFood(Long id) {
		return foodService.delFood(id);
	}
	
	@RequestMapping("/selectFood.do")
	public String selectFood(HttpServletRequest request){
		List<Map<String, Object>> foodList = foodService.findFood();
		request.setAttribute("foodList", foodList);
		return "admin/listFoods.jsp";
	}

}
