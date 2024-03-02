package cn.java.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import cn.java.entity.Computer;

/**
 * Jpa接口 继承JpaRepository<持久持久化类,主键类型>
 * 
 * @author Administrator
 */
@Component
public interface ComputerJpa extends JpaRepository<Computer, Long> {

	Computer findByBrand(String brand);
	
	Computer findByBrandAndRunMem(String brand, Float runMem);

}
