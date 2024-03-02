package cn.java.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.entity.Good;
import cn.java.service.GoodService;

@Controller
@RequestMapping("/goods/")
public class GoodController {
	
	@Autowired
	private GoodService goodService;
	
	/**
	 * 获取所有商品(List集合封装)，返回到浏览器
	 * @return
	 */
	@RequestMapping("/getAllGoods.do")
	@ResponseBody
	public List<Map<String, Object>> getAllGoods() {
		return goodService.getAllGoods();
	}
	
	/**
	 * 获取所有商品(Goods对象封装)，返回到浏览器
	 * @return
	 */
	@RequestMapping("/selectAllGoods.do")
	@ResponseBody
	public List<Good> selectAllGoods() {
		return goodService.selectAllGoods();
	}

}
