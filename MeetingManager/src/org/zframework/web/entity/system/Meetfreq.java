package org.zframework.web.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ro_meetfreq")
public class Meetfreq {
	private Integer id;
	private String meetfreq;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ro_meetfreq")
	@SequenceGenerator(name = "seq_ro_meetfreq", sequenceName = "seq_ro_meetfreq")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="meetfreq",nullable=false,length=50)
	public String getMeetfreq() {
		return meetfreq;
	}
	public void setMeetfreq(String meetfreq) {
		this.meetfreq = meetfreq;
	}
	
}
