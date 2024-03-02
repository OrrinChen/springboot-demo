package cn.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.entity.Computer;
import cn.java.service.ComputerJpaService;

@Controller
public class ComputerController {
	@Autowired
	private ComputerJpaService computerJpaService;
	
	@RequestMapping("/findByBrand.do")
	public @ResponseBody Computer findByBrand(String brand) {
		return computerJpaService.findByBrand(brand);
	}
	
	@RequestMapping("/findByBrandAndRunMem.do")
	public @ResponseBody Computer findByBrandAndRunMem(String brand, Float runMem) {
		return computerJpaService.findByBrandAndRunMem(brand, runMem);
	}

}
