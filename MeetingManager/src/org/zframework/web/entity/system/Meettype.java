package org.zframework.web.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ro_meettype")
public class Meettype {
	private Integer id;
	private String meettype;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ro_meettype")
	@SequenceGenerator(name = "seq_ro_meettype", sequenceName = "seq_ro_meettype")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="meettype",nullable=false,length=50)
	public String getMeettype() {
		return meettype;
	}
	public void setMeettype(String meettype) {
		this.meettype = meettype;
	}
	
}
