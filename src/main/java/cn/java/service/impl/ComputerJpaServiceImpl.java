package cn.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.entity.Computer;
import cn.java.jpa.ComputerJpa;
import cn.java.service.ComputerJpaService;

@Service
public class ComputerJpaServiceImpl implements ComputerJpaService {
	@Autowired
	private ComputerJpa computerJpa;
	
	@Override
	public Computer findByBrand(String brand) {
		return computerJpa.findByBrand(brand);
	}

	@Override
	public Computer findByBrandAndRunMem(String brand, Float runMem) {
		return computerJpa.findByBrandAndRunMem(brand, runMem);
	}

}
