package org.zframework.web.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ro_roomdata")
public class Roomdata {
	private Integer id;
	private String num;
	private String name;
	private String addr;
	private String timeperiod;
	private String dateperiod;
	private String roomcapacity;
	private String remark;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ro_roomdata")
	@SequenceGenerator(name = "seq_ro_roomdata", sequenceName = "seq_ro_roomdata")
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
	@Column(name="timeperiod",nullable=false,length=50)
	public String getTimeperiod() {
		return timeperiod;
	}
	public void setTimeperiod(String timeperiod) {
		this.timeperiod = timeperiod;
	}
	@Column(name="dateperiod",nullable=false,length=50)
	public String getDateperiod() {
		return dateperiod;
	}
	public void setDateperiod(String dateperiod) {
		this.dateperiod = dateperiod;
	}
	@Column(name="roomcapacity",nullable=false,length=50)
	public String getRoomcapacity() {
		return roomcapacity;
	}
	public void setRoomcapacity(String roomcapacity) {
		this.roomcapacity = roomcapacity;
	}
	@Column(name="remark",nullable=false,length=50)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
