package org.zframework.web.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="ro_factorydata")
public class Factorydata {
private Integer id;
private String num;
private String name;
private String addr;
private String phone;
@Id
@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ro_factorydata")
@SequenceGenerator(name = "seq_ro_factorydata", sequenceName = "seq_ro_factorydata")
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
@Column(name="num",nullable=false,length=50)
public String getNum() {
	return num;
}
public void setNum(String num) {
	this.num = num;
}
@Column(name="name",nullable=false,length=50)
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Column(name="addr",nullable=false,length=50)
public String getAddr() {
	return addr;
}
public void setAddr(String addr) {
	this.addr = addr;
}
@Column(name="phone",nullable=false,length=50)
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}


}
