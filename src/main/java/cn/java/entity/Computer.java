package cn.java.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // POJO变成持久类
@Table(name = "computers") // 对应数据库表明
public class Computer implements Serializable {
	private static final long serialVersionUID = -2028617857889504577L; // 序列化接口
	@Id // 主键
	@Column(name = "id")
	@GeneratedValue // 自增长
	private long id;
	@Column(name = "")
	private String brand;
	@Column(name = "system")
	private String system;
	@Column(name = "cpu")
	private String cpu;
	@Column(name = "runMem")//-->对应到了数据库run_mem字段
	private Float runMem;
	@Column(name = "price")
	private Float price;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public Float getRunMem() {
		return runMem;
	}
	public void setRunMem(Float runMem) {
		this.runMem = runMem;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Computer [id=" + id + ", brand=" + brand + ", system=" + system + ", cpu=" + cpu + ", runMem=" + runMem
				+ ", price=" + price + "]";
	}

}
